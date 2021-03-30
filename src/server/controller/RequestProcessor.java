package server.controller;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import common.model.entity.*;
import common.util.DataSourceUtils;
import server.*;
import server.model.service.UserService;

/** 服务器端请求处理器 */
public class RequestProcessor implements Runnable{
	private Socket currentClientSocket;  //当前正在请求服务器的客户端Socket
	
	public RequestProcessor(Socket currentClientSocket){
		this.currentClientSocket = currentClientSocket;
	}
	private static RequestProcessor instance;
    private RequestProcessor(){

    }
    public static synchronized RequestProcessor getInstance(){
        if(instance==null){
            instance=new RequestProcessor();
        }
        return instance;
    }
	public void run() {
		boolean flag = true; //是否不间断监听
		try{
			OnlineClientIOCache currentClientIOCache = new OnlineClientIOCache(
					new ObjectInputStream(currentClientSocket.getInputStream()), 
					new ObjectOutputStream(currentClientSocket.getOutputStream()));
			while(flag){ //不停地读取客户端发过来的请求对象
				//从请求输入流中读取到客户端提交的请求对象
				Request request = (Request)currentClientIOCache.getOis().readObject();
				System.out.println("Server读取了客户端的请求:" + request.getAction());

				String actionName = request.getAction();   //获取请求中的动作
				if(actionName.equals("userRegiste")){      //用户注册
					registe(currentClientIOCache, request);
				}else if(actionName.equals("userLogin")){  //用户登录
					login(currentClientIOCache, request);
				}else if("exit".equals(actionName)){       //请求断开连接
					flag = logout(currentClientIOCache, request);
				}else if("chat".equals(actionName)){       //聊天
					chat(request);
				}else if("shake".equals(actionName)){      //振动
					shake(request);
				}else if("toSendFile".equals(actionName)){ //准备发送文件
					toSendFile(request);
				}else if("agreeReceiveFile".equals(actionName)){ //同意接收文件
					agreeReceiveFile(request);
				}else if("refuseReceiveFile".equals(actionName)){ //拒绝接收文件
					refuseReceiveFile(request);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/** 拒绝接收文件 */
	private void refuseReceiveFile(Request request) throws IOException {
		FileInfo sendFile = (FileInfo)request.getAttribute("sendFile");
		Response response = new Response();  //创建一个响应对象
		response.setType(ResponseType.REFUSERECEIVEFILE);
		response.setData("sendFile", sendFile);
		response.setStatus(ResponseStatus.OK);
		//向请求方的输出流输出响应
		OnlineClientIOCache ocic = DataBuffer.onlineUserIOCacheMap.get(sendFile.getFromUser().getUserid());
		this.sendResponse(ocic, response);
	}

	/** 同意接收文件 */
	private void agreeReceiveFile(Request request) throws IOException {
		FileInfo sendFile = (FileInfo)request.getAttribute("sendFile");
		//向请求方(发送方)的输出流输出响应
		Response response = new Response();  //创建一个响应对象
		response.setType(ResponseType.AGREERECEIVEFILE);
		response.setData("sendFile", sendFile);
		response.setStatus(ResponseStatus.OK);
		OnlineClientIOCache sendIO = DataBuffer.onlineUserIOCacheMap.get(sendFile.getFromUser().getUserid());
		this.sendResponse(sendIO, response);
		
		//向接收方发出接收文件的响应
		Response response2 = new Response();  //创建一个响应对象
		response2.setType(ResponseType.RECEIVEFILE);
		response2.setData("sendFile", sendFile);
		response2.setStatus(ResponseStatus.OK);
		OnlineClientIOCache receiveIO = DataBuffer.onlineUserIOCacheMap.get(sendFile.getToUser().getUserid());
		this.sendResponse(receiveIO, response2);
	}
	
	/** 客户端退出 */
	public boolean logout(OnlineClientIOCache oio, Request request) throws IOException{
		System.out.println(currentClientSocket.getInetAddress().getHostAddress()
				+ ":" + currentClientSocket.getPort() + "走了");

		User user = (User)request.getAttribute("user");
		//把当前上线客户端的IO从Map中删除
		DataBuffer.onlineUserIOCacheMap.remove(user.getUserid());
		//从在线用户缓存Map中删除当前用户
		DataBuffer.onlineUsersMap.remove(user.getUserid());
			
		Response response = new Response();  //创建一个响应对象
		response.setType(ResponseType.LOGOUT);
		response.setData("logoutUser", user);
		oio.getOos().writeObject(response);  //把响应对象往客户端写
		oio.getOos().flush();
		currentClientSocket.close();  //关闭这个客户端Socket
		
		DataBuffer.onlineUserTableModel.remove(user.getUserid()); //把当前下线用户从在线用户表Model中删除
		iteratorResponse(response);//通知所有其它在线客户端
		
		return false;  //断开监听
	}
	
	public boolean logout_user(User user) throws IOException{
		OnlineClientIOCache io = DataBuffer.onlineUserIOCacheMap.get(user.getUserid());
		Response response = new Response();
		response.setStatus(ResponseStatus.OK);
		response.setType(ResponseType.LOGYOU);
		response.setData("txtMsg", "admin_logout");
		io.getOos().writeObject(response);  //把响应对象往客户端写
		io.getOos().flush();
		//把当前上线客户端的IO从Map中删除
		DataBuffer.onlineUserIOCacheMap.remove(user.getUserid());
		//从在线用户缓存Map中删除当前用户
		DataBuffer.onlineUsersMap.remove(user.getUserid());
		DataBuffer.onlineUserTableModel.remove(user.getUserid()); //把当前下线用户从在线用户表Model中删除
		response.setType(ResponseType.LOGOUT);
		response.setData("logoutUser", user);
		iteratorResponse(response);//通知所有其它在线客户端
		return false;  //断开监听
	}
	/** 注册 */
	public void registe(OnlineClientIOCache oio, Request request) throws IOException {
		User user = (User)request.getAttribute("user");
		UserService userService = new UserService();
		Boolean res = userService.addUser(user);
		if (res) {
			List<User> list = null;
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		    String sql = "select * from imusers";
		    try {
				list = (List<User>) qr.query(sql, new BeanListHandler<>(User.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    int count =0;
		    
		    for (User user2 : list) {
		    	count++;
				if (count==list.size()) {
					user = user2;
				}
			}
		    System.out.println(user.getUserid());
			Response response = new Response();  //创建一个响应对象
			response.setStatus(ResponseStatus.OK);
			response.setData("user", user);
			
			oio.getOos().writeObject(response);  //把响应对象往客户端写
			oio.getOos().flush();
		}else {
			Response response = new Response();  //创建一个响应对象
			response.setStatus(ResponseStatus.SERVER_ERROR);
			response.setData("user", user);
			
			oio.getOos().writeObject(response);  //把响应对象往客户端写
			oio.getOos().flush();
		}
		
		
		//把新注册用户添加到RegistedUserTableModel中
		DataBuffer.registedUserTableModel.add(new String[]{
			String.valueOf(user.getUserid()),
			user.getUserpwd(),
			user.getUsername(),
			String.valueOf(user.getSex())
		});
	}
	
	/** 登录 */
	public void login(OnlineClientIOCache currentClientIO, Request request) throws IOException {
		String username = (String)request.getAttribute("username");
		String password = (String) request.getAttribute("password");
		UserService userService = new UserService();
		User user = userService.login(username, password);
		
		Response response = new Response();  //创建一个响应对象
		if(null != user){
			if(DataBuffer.onlineUsersMap.containsKey(user.getUserid())){ //用户已经登录了
				response.setStatus(ResponseStatus.OK);
				response.setData("msg", "该 用户已经在别处上线了！");
				currentClientIO.getOos().writeObject(response);  //把响应对象往客户端写
				currentClientIO.getOos().flush();
			}else { //正确登录
				DataBuffer.onlineUsersMap.put((long) user.getUserid(), user); //添加到在线用户
				//设置在线用户
				response.setData("onlineUsers", 
						new CopyOnWriteArrayList<User>(DataBuffer.onlineUsersMap.values()));
				
				response.setStatus(ResponseStatus.OK);
				response.setData("user", user);
				currentClientIO.getOos().writeObject(response);  //把响应对象往客户端写
				currentClientIO.getOos().flush();
				
				//通知其它用户有人上线了
				Response response2 = new Response();
				response2.setType(ResponseType.LOGIN);
				response2.setData("loginUser", user);
				iteratorResponse(response2);
				
				//把当前上线的用户IO添加到缓存Map中
				DataBuffer.onlineUserIOCacheMap.put((long) user.getUserid(),currentClientIO);
				
				//把当前上线用户添加到OnlineUserTableModel中
				DataBuffer.onlineUserTableModel.add(
						new String[]{String.valueOf(user.getUserid()), 
										user.getUsername(), 
										String.valueOf(user.getSex())});
			}
		}else{ //登录失败
			response.setStatus(ResponseStatus.OK);
			response.setData("msg", "账号或密码不正确！");
			currentClientIO.getOos().writeObject(response);
			currentClientIO.getOos().flush();
		}
	}
	
	/** 聊天 */
	public void chat(Request request) throws IOException {
		Message msg = (Message)request.getAttribute("msg");
		Response response = new Response();
		response.setStatus(ResponseStatus.OK);
		response.setType(ResponseType.CHAT);
		response.setData("txtMsg", msg);
		
		//消息记录放到数据库
		/*QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?);";	*/
		
		
		
		if(msg.getToUser() != null){ //私聊:只给私聊的对象返回响应
			OnlineClientIOCache io = DataBuffer.onlineUserIOCacheMap.get(msg.getToUser().getUserid());
			sendResponse(io, response);
		}else{  //群聊:给除了发消息的所有客户端都返回响应
			for(long id : DataBuffer.onlineUserIOCacheMap.keySet()){
				if(msg.getFromUser().getUserid() == id ){	continue; }
				sendResponse(DataBuffer.onlineUserIOCacheMap.get(id), response);
			}
		}
	}
	
	/** 发送振动 */
	public void shake(Request request)throws IOException {
		Message msg = (Message)request.getAttribute("msg");
		
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append(" ").append(msg.getFromUser().getUsername())
			.append("(").append(msg.getFromUser().getUserid()).append(") ")
			.append(df.format(msg.getSendTime())).append("\n  给您发送了一个窗口抖动\n");
		msg.setMessage(sb.toString());
		
		Response response = new Response();
		response.setStatus(ResponseStatus.OK);
		response.setType(ResponseType.SHAKE);
		response.setData("ShakeMsg", msg);
		
		OnlineClientIOCache io = DataBuffer.onlineUserIOCacheMap.get(msg.getToUser().getUserid());
		sendResponse(io, response);
	}
	
	/** 准备发送文件 */
	public void toSendFile(Request request)throws IOException{
		Response response = new Response();
		response.setStatus(ResponseStatus.OK);
		response.setType(ResponseType.TOSENDFILE);
		FileInfo sendFile = (FileInfo)request.getAttribute("file");
		response.setData("sendFile", sendFile);
		//给文件接收方转发文件发送方的请求
		OnlineClientIOCache ioCache = DataBuffer.onlineUserIOCacheMap.get(sendFile.getToUser().getUserid());
		sendResponse(ioCache, response);
	}
	
	/** 给所有在线客户都发送响应 */
	private void iteratorResponse(Response response) throws IOException {
		for(OnlineClientIOCache onlineUserIO : DataBuffer.onlineUserIOCacheMap.values()){
			ObjectOutputStream oos = onlineUserIO.getOos();
			oos.writeObject(response);
			oos.flush();
		}
	}
	
	/** 向指定客户端IO的输出流中输出指定响应 */
	private void sendResponse(OnlineClientIOCache onlineUserIO, Response response)throws IOException {
		ObjectOutputStream oos = onlineUserIO.getOos();
		oos.writeObject(response);
		oos.flush();
	}
	public Boolean logout_all() {
		
		Response response = new Response();
		response.setStatus(ResponseStatus.OK);
		response.setType(ResponseType.LOGYOU);
		response.setData("txtMsg", "admin_logout");
		
		try {
			iteratorResponse(response);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}//通知所有其它在线客户端
		return false;
	}
}