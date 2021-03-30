package server.model.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.xpath.internal.operations.Bool;

import common.model.entity.Friend;
import common.model.entity.User;
import common.util.*;

/** 用户操作相关的业务逻辑类 */
public class UserService {
	//private static int idCount = 3; //id
	
	
	/** 新增用户 */
	public Boolean addUser(User user){
		//user.setUserid(++idCount);
		/*List<User> users = loadAllUser();
		users.add(user);
		saveAllUser(users);*/
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into imusers(userName,password,Sex,head,nikename,phoneNumber,type,age) values(?,?,?,?,?,?,?,?);";
		try {
			qr.update(sql,user.getUsername(),user.getUserpwd(),user.getSex(),
					user.getHead(),user.getNikename(),user.getPhoneNumber(),user.getType(),user.getAge());
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("注册失败");
			return false;
		}
	}
	
	/** 用户登录 */
	public User login(String username, String password){
		User result = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		System.out.println(username+"and"+password);
		String ssql = "select * from imusers where userName = ? and password = ?";
		try {
			User user = (User) qr.query(ssql, new BeanHandler(User.class),username,password);
			if (user==null) {
				System.out.println("登陆失败");
				return user;
			}else {
				result = user;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//System.out.println(df.format(new Date()).toString());// new Date()为获取当前系统时间
		
		
		String sql = "insert into imuserlog(logtime,loguserid,loguser,logip,logcontent) values(?,?,?,?,?);";
		String date = df.format(new Date()).toString();
		System.out.println(result);
		try {
			qr.update(sql,date,
					result.getUserid(),
					result.getUsername().toString(),
					"127.0.0.1",
					result.getUsername()+"上线了");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("登陆失败");
		}
		System.out.println(result);
		return result;
	}
	
	
	/** 根据ID加载用户 */
	public User loadUser(long id){
		User result = null;
		List<User> users = loadAllUser();
		for (User user : users) {
			if(id == user.getUserid()){
				result = user;
				break;
			}
		}
		return result;
	}
	
	
	/** 加载所有用户 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> loadAllUser() {
		List<User> list = null;

		/*try {
			ois = new ObjectInputStream(
					new FileInputStream(
							DataBuffer.configProp.getProperty("dbpath")));
			
			list = (List<User>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtil.close(ois);
		}*/
		
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	    String sql = "select * from imusers";
	   
	    try {
			list = (List<User>) qr.query(sql, new BeanListHandler(User.class));
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private void saveAllUser(List<User> users) {
		ObjectOutputStream oos = null;
		/*try {
			oos = new ObjectOutputStream(
						new FileOutputStream(
								DataBuffer.configProp.getProperty("dbpath")));
			//写回用户信息
			oos.writeObject(users);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtil.close(oos);
		}*/
		for (User user : users) {
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "insert into imusers values(?,?,?,?,?)";
			try {
				qr.update(sql, user.getUserid(), user.getUsername(), user.getUserpwd(),
				         user.getSex(),
				        user.getHead());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("注册失败");
			}finally{
				IOUtil.close(oos);
			}
		}
		
	}
	
	public List<User> FindFriends(long id) {
		
		List<User> list = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from imusers a inner join imfriends b on a.userid = b.touser where b.fromuser = ?;";
		//System.out.println(qr.query(sql, new BeanListHandler<>(User.class),4));
		try {
			list = (List<User>) qr.query(sql, new BeanListHandler<>(User.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(" ");
			e.printStackTrace();
		}
		return list;
		
	}
	public int addFriends(long fromid,long toid) {
		List<Friend> friends = null;
		QueryRunner fqr = new QueryRunner(DataSourceUtils.getDataSource());
		String fsql = "select * from imfriends";
		try {
			friends = (List<Friend>) fqr.query(fsql, new BeanListHandler<Friend>(Friend.class));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int count=1;
		for(Friend friend:friends) {
			count++;
			if (toid==friend.getTouser()) {
				return 2;
			}
		}
		int flag = 0;
		if (toid!=fromid) {
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "insert into imfriends values(?,?,?,?)";
			//System.out.println(qr.query(sql, new BeanListHandler<>(User.class),4));
			try {
				qr.update(sql,count,fromid,toid,0);
				flag = 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				flag = 0;
				System.out.println(" ");
				e.printStackTrace();
			}
		}else {
			flag=3;
		}
		
		return flag;
	}
	
	/** 初始化几个测试用户 */
/*	public void initUser(){
		User user = new User("123", "Admin", 'm', 0);
		user.setId(1);
		
		User user2 = new User("123", "yong", 'm', 1);
		user2.setId(2);
		
		User user3 = new User("123", "anni", 'f', 2);
		user3.setId(3);
		
		List<User> users = new CopyOnWriteArrayList<User>();
		users.add(user);
		users.add(user2);
		users.add(user3);
		
		this.saveAllUser(users);
	}*/
	
	public static void main(String[] args){
		//new UserService().initUser();
		List<User> users = new UserService().loadAllUser();
		for (User user : users) {
			System.out.println(user);
		}
	}
}
