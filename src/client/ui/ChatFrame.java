package client.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import client.*;
import client.model.entity.MyCellRenderer;
import client.model.entity.MyFriendsTableModel;
import client.model.entity.OnlineUserListModel;
import client.util.*;
import common.model.entity.*;
import common.util.DataSourceUtils;
import server.model.service.UserService;
import sun.security.util.Length;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mysql.fabric.xmlrpc.Client;
import com.sun.javafx.geom.AreaOp.IntOp;

/** 聊天窗体 */
public class ChatFrame extends JFrame{
	private static final long serialVersionUID = -2310785591507878535L;
	/**聊天对方的信息Label*/
	private JLabel otherInfoLbl;
	/** 当前用户信息Lbl */
	private JLabel currentUserLbl;
	/**聊天信息列表区域*/
	public static JTextArea msgListArea;
	/**要发送的信息区域*/
	public static JTextArea sendArea; 
	/** 在线用户列表 */
	public static JList onlineList;
	/** 在线用户数统计Lbl */
	public static JLabel onlineCountLbl;
	/** 准备发送的文件 */
	public static FileInfo sendFile;
	/** 私聊复选框 */
	public JCheckBox rybqBtn;
	MyFriendsListUI mfl;
	AddUsersUI adui;

	
	public ChatFrame(){
		this.init();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void init(){
		this.setTitle("JQ聊天室");
		this.setSize(550, 500);
		this.setResizable(false);
		long id = DataBuffer.currentUser.getUserid();
		
		//设置默认窗体在屏幕中央
		int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((x - this.getWidth()) / 2, (y-this.getHeight())/ 2);
		
		//左边主面板
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		//右边用户面板
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BorderLayout());
		
		// 创建一个分隔窗格
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				mainPanel, userPanel);
		splitPane.setDividerLocation(380);
		splitPane.setDividerSize(10);
		splitPane.setOneTouchExpandable(true);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		//左上边信息显示面板
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		//右下连发送消息面板
		JPanel sendPanel = new JPanel();
		sendPanel.setLayout(new BorderLayout());
		
		// 创建一个分隔窗格
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				infoPanel, sendPanel);
		splitPane2.setDividerLocation(300);
		splitPane2.setDividerSize(1);
		mainPanel.add(splitPane2, BorderLayout.CENTER);
		
		otherInfoLbl = new JLabel("当前状态：群聊中...");
		infoPanel.add(otherInfoLbl, BorderLayout.NORTH);
		
		msgListArea = new JTextArea();
		msgListArea.setLineWrap(true);
		infoPanel.add(new JScrollPane(msgListArea, 
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BorderLayout());
		sendPanel.add(tempPanel, BorderLayout.NORTH);
		
		// 聊天按钮面板
		JPanel btnPanel = new JPanel();
		tempPanel.add(btnPanel, BorderLayout.CENTER);
		
		/*//字体按钮
		JButton fontBtn = new JButton(new ImageIcon("images/font.png"));
		fontBtn.setMargin(new Insets(0,0,0,0));
		fontBtn.setToolTipText("设置字体和格式");
		
		//表情按钮
		JButton faceBtn = new JButton(new ImageIcon("images/sendFace.png"));
		faceBtn.setMargin(new Insets(0,0,0,0));
		faceBtn.setToolTipText("选择表情");*/
		
		//发送文件按钮
		JButton shakeBtn = new JButton(new ImageIcon("images/shake.png"));
		shakeBtn.setMargin(new Insets(0,0,0,0));
		shakeBtn.setToolTipText("向对方发送窗口振动");
		
		//发送文件按钮
		JButton sendFileBtn = new JButton(new ImageIcon("images/sendPic.png"));
		sendFileBtn.setMargin(new Insets(0,0,0,0));
		sendFileBtn.setToolTipText("向对方发送文件");
		
		JLabel label = new JLabel("");
		
		MyFriend_Label = new JLabel("\u6211\u7684\u597D\u53CB");
		MyFriend_Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyFriend_LabelmouseClicked(e);
			}
		});
		MyFriend_Label.setFont(new Font("宋体", Font.BOLD, 14));
		
		AddFriend_Label = new JLabel("\u6DFB\u52A0\u597D\u53CB");
		AddFriend_Label.setFont(new Font("宋体", Font.BOLD, 14));
		AddFriend_Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFriend_LabelmouseClicked(e);
			}
		});
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					/*.addGap(5)
					.addComponent(fontBtn)
					.addGap(5)
					.addComponent(faceBtn)*/
					.addGap(5)
					.addComponent(shakeBtn)
					.addGap(5)
					.addComponent(sendFileBtn)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_btnPanel.createSequentialGroup()
							.addGap(113)
							.addComponent(label)
							.addContainerGap(175, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_btnPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(MyFriend_Label)
							.addGap(18)
							.addComponent(AddFriend_Label)
							.addGap(43))))
		);
		gl_btnPanel.setVerticalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(MyFriend_Label)
							.addComponent(AddFriend_Label))
						.addComponent(label)/*
						.addComponent(fontBtn)
						.addComponent(faceBtn)*/
						.addComponent(shakeBtn)
						.addComponent(sendFileBtn))
					.addContainerGap())
		);
		btnPanel.setLayout(gl_btnPanel);
		
		//私聊按钮
		rybqBtn = new JCheckBox("私聊");
		tempPanel.add(rybqBtn, BorderLayout.EAST);
		
		//要发送的信息的区域
		sendArea = new JTextArea();
		sendArea.setLineWrap(true);
		sendPanel.add(new JScrollPane(sendArea, 
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		
		// 聊天按钮面板
		JPanel btn2Panel = new JPanel();
		btn2Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(btn2Panel, BorderLayout.SOUTH);
		JButton closeBtn = new JButton("关闭");
		closeBtn.setToolTipText("退出整个程序");
		btn2Panel.add(closeBtn);
		JButton submitBtn = new JButton("发送");
		submitBtn.setToolTipText("按Enter键发送消息");
		btn2Panel.add(submitBtn);
		//sendPanel.add(btn2Panel, BorderLayout.SOUTH);
		
		//在线用户列表面板
		JPanel onlineListPane = new JPanel();
		onlineListPane.setLayout(new BorderLayout());
		onlineCountLbl = new JLabel("在线用户列表(1)"); 
		onlineListPane.add(onlineCountLbl, BorderLayout.NORTH);
		
		//当前用户面板
		JPanel currentUserPane = new JPanel();
		currentUserPane.setLayout(new BorderLayout());
		currentUserPane.add(new JLabel("当前用户"), BorderLayout.NORTH);
		
		// 右边用户列表创建一个分隔窗格
		JSplitPane splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				onlineListPane, currentUserPane);
		splitPane3.setDividerLocation(340);
		splitPane3.setDividerSize(1);
		userPanel.add(splitPane3, BorderLayout.CENTER);
		
		
		
		//获取在线用户并缓存
		DataBuffer.onlineUserListModel = new OnlineUserListModel(DataBuffer.onlineUsers);
		//在线用户列表 
		onlineList = new JList(DataBuffer.onlineUserListModel);
		onlineList.setCellRenderer(new MyCellRenderer());
		//设置为单选模式
		onlineList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		onlineListPane.add(new JScrollPane(onlineList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		
		//获取好友列表
		List<User> users = new UserService().FindFriends(id);
		//把好友放到右边列表里面
		/*for (User user : users) {
			DataBuffer.myFriendsTableModel.add(
					new String[]{String.valueOf(user.getUserid()), 
									user.getUsername(), 
									String.valueOf(user.getUsersex())});
			DataBuffer.myFriendsTableModel = (List<User>) new MyFriendsTableModel(user);
		}*/
		
		
		
		//当前用户信息Label
		currentUserLbl = new JLabel();
		currentUserPane.add(currentUserLbl);
		
		
		///////////////////////注册事件监听器/////////////////////////
		//关闭窗口
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				logout();
			}
		});
		
		//关闭按钮的事件
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				logout();
			}
		});
		
		//选择某个用户私聊
		rybqBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(rybqBtn.isSelected()){
					User selectedUser = (User)onlineList.getSelectedValue();
					if(null == selectedUser){
						otherInfoLbl.setText("当前状态：私聊(选中在线用户列表中某个用户进行私聊)...");
					}else if(DataBuffer.currentUser.getUserid() == selectedUser.getUserid()){
						otherInfoLbl.setText("当前状态：想自言自语?...系统不允许");
					}else{
						otherInfoLbl.setText("当前状态：与 "+ selectedUser.getUsername()
								+"(" + selectedUser.getUserid() + ") 私聊中...");
					}
				}else{
					otherInfoLbl.setText("当前状态：群聊...");
				}
			}
		});
		//选择某个用户
		onlineList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				User selectedUser = (User)onlineList.getSelectedValue();
				if(rybqBtn.isSelected()){
					if(DataBuffer.currentUser.getUserid() == selectedUser.getUserid()){
						otherInfoLbl.setText("当前状态：想自言自语?...系统不允许");
					}else{
						otherInfoLbl.setText("当前状态：与 "+ selectedUser.getUsername()
								+"(" + selectedUser.getUserid() + ") 私聊中...");
					}
				}
			}
		});
		
		//发送文本消息
		sendArea.addKeyListener(new KeyAdapter(){   
			public void keyPressed(KeyEvent e){   
				if(e.getKeyCode() == Event.ENTER){   
					sendTxtMsg();
				}
			}   
		});
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sendTxtMsg();
			}
		});
		
		//发送振动
		shakeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sendShakeMsg();
			}
		});
		
		//发送文件
		sendFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sendFile();
			}
		});
		
		this.loadData();  //加载初始数据
		
		mfl = new MyFriendsListUI(id);
		mfl.setVisible(false);
		adui = new AddUsersUI(id);
		adui.setVisible(false);
	}
	
						/**  设置事件 */
	
	
			//	添加好友标签的签的事件
	private void MyFriend_LabelmouseClicked(MouseEvent e) 
	{
		if (e.getButton()==MouseEvent.BUTTON1)
		{
			mfl.setVisible(true);
		}
	}
	
	private void AddFriend_LabelmouseClicked(MouseEvent e) 
	{
		if (e.getButton()==MouseEvent.BUTTON1)
		{
			adui.setVisible(true);
		}
	}

	/**  加载数据 */
	public void loadData(){
		//加载当前用户数据
		if(null != DataBuffer.currentUser){ 
			currentUserLbl.setIcon(
					new ImageIcon("images/" + DataBuffer.currentUser.getHead() + ".png"));
			currentUserLbl.setText(DataBuffer.currentUser.getUsername()
					+ "(昵称：" + DataBuffer.currentUser.getNikename() + ")");
		}
		//设置在线用户列表
		onlineCountLbl.setText("在线用户列表("+ DataBuffer.onlineUserListModel.getSize() +")");
		
		
		//获取所有朋友
		List<User> users = new UserService().FindFriends(DataBuffer.currentUser.getUserid());
		
		
		//启动监听服务器消息的线程
		new ClientThread(this).start();
	}
	
	/** 发送振动 */
	public void sendShakeMsg(){
		User selectedUser = (User)onlineList.getSelectedValue();
		if(null != selectedUser){
			if(DataBuffer.currentUser.getUserid() == selectedUser.getUserid()){
				JOptionPane.showMessageDialog(ChatFrame.this, "不能给自己发送振动!",
					"不能发送", JOptionPane.ERROR_MESSAGE);
			}else{
				Message msg = new Message();
				msg.setFromUser(DataBuffer.currentUser);
				msg.setToUser(selectedUser);
				msg.setSendTime(new Date());
				
				DateFormat df = new SimpleDateFormat("HH:mm:ss");
				StringBuffer sb = new StringBuffer();
				sb.append(" ").append(msg.getFromUser().getUsername())
					.append("(").append(msg.getFromUser().getUserid()).append(") ")
					.append(df.format(msg.getSendTime()))
					.append("\n  给").append(msg.getToUser().getUsername())
					.append("(").append(msg.getToUser().getUserid()).append(") ")
					.append("发送了一个窗口抖动\n");
				msg.setMessage(sb.toString());
				
				Request request = new Request();
				request.setAction("shake");
				request.setAttribute("msg", msg);
				try {
					ClientUtil.sendTextRequest2(request);
				} catch (IOException e) {
					e.printStackTrace();
				}
				ClientUtil.appendTxt2MsgListArea(msg.getMessage());
				new JFrameShaker(ChatFrame.this).startShake();
			}
		}else{
			JOptionPane.showMessageDialog(ChatFrame.this, "不能群发送振动!",
					"不能发送", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/** 发送文本消息 */
	public void sendTxtMsg(){
		String content = sendArea.getText();
		
		if ("".equals(content)) { //无内容
			JOptionPane.showMessageDialog(ChatFrame.this, "不能发送空消息!",
					"不能发送", JOptionPane.ERROR_MESSAGE);
		} else { //发送
			User selectedUser = (User)onlineList.getSelectedValue();
			if(null != selectedUser && 
					DataBuffer.currentUser.getUserid() == selectedUser.getUserid()){
				JOptionPane.showMessageDialog(ChatFrame.this, "不能给自己发送消息!",
						"不能发送", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//如果设置了ToUser表示私聊，否则群聊
			Message msg = new Message();
			if(rybqBtn.isSelected()){  //私聊
				if(null == selectedUser){
					JOptionPane.showMessageDialog(ChatFrame.this, "没有选择私聊对象!",
							"不能发送", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					msg.setToUser(selectedUser);
				}
			}
			msg.setFromUser(DataBuffer.currentUser);
			msg.setSendTime(new Date());
				
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			StringBuffer sb = new StringBuffer();
			sb.append(" ").append(df.format(msg.getSendTime())).append(" ")
				.append(msg.getFromUser().getUsername())
				.append("(").append(msg.getFromUser().getUserid()).append(") ");
			if(!this.rybqBtn.isSelected()){//群聊
				if(null == selectedUser){
					sb.append("对大家说");
				}else{
					sb.append("对").append(selectedUser.getUsername())
						.append("(").append(selectedUser.getUserid()).append(")")
						.append("说");
				}
			}
			sb.append("\n  ").append(content).append("\n");
			msg.setMessage(sb.toString());
			
			Request request = new Request();
			request.setAction("chat");
			request.setAttribute("msg", msg);
			try {
				ClientUtil.sendTextRequest2(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//消息记录放到数据库
			
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from imchatlog";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String ssql = "insert into imchatlog values(?,?,?,?,?,?);";
			List<ImChatLog> ImChatLogs = null;
			try {
				ImChatLogs = (List<ImChatLog>) qr.query(sql, new BeanListHandler(ImChatLog.class));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int count=1;
			for(ImChatLog imChatLog:ImChatLogs) {
				count++;
			}
			if(null == selectedUser){
				try {
					qr.update(ssql,count,DataBuffer.currentUser.getUserid(),
							0,sdf.format(new Date()).toString(),sb.toString(),0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				Long touser = ((User) onlineList.getSelectedValue()).getUserid();
				try {
					qr.update(ssql,count,DataBuffer.currentUser.getUserid(),
							touser,sdf.format(new Date()).toString(),sb.toString(),1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			
			
			//JTextArea中按“Enter”时，清空内容并回到首行
			InputMap inputMap = sendArea.getInputMap();
			ActionMap actionMap = sendArea.getActionMap();
			Object transferTextActionKey = "TRANSFER_TEXT";
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),transferTextActionKey);
			actionMap.put(transferTextActionKey,new AbstractAction() {
				private static final long serialVersionUID = 7041841945830590229L;
				public void actionPerformed(ActionEvent e) {
					sendArea.setText("");
					sendArea.requestFocus();
				}
			});
			sendArea.setText("");
			ClientUtil.appendTxt2MsgListArea(msg.getMessage());
		}
	}
	
	/** 发送文件 */
	private void sendFile() {
		User selectedUser = (User)onlineList.getSelectedValue();
		if(null != selectedUser){
			if(DataBuffer.currentUser.getUserid() == selectedUser.getUserid()){
				JOptionPane.showMessageDialog(ChatFrame.this, "不能给自己发送文件!",
					"不能发送", JOptionPane.ERROR_MESSAGE);
			}else{
				JFileChooser jfc = new JFileChooser();
				if (jfc.showOpenDialog(ChatFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					sendFile = new FileInfo();
					sendFile.setFromUser(DataBuffer.currentUser);
					sendFile.setToUser(selectedUser);
					try {
						sendFile.setSrcName(file.getCanonicalPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					sendFile.setSendTime(new Date());
					
					Request request = new Request();
					request.setAction("toSendFile");
					request.setAttribute("file", sendFile);
					try {
						ClientUtil.sendTextRequest2(request);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					ClientUtil.appendTxt2MsgListArea("【文件消息】向 "
								+ selectedUser.getUsername() + "("
								+ selectedUser.getUserid() + ") 发送文件 ["
								+ file.getName() + "]，等待对方接收...\n");
				}
			}
		}else{
			JOptionPane.showMessageDialog(ChatFrame.this, "不能给所有在线用户发送文件!",
					"不能发送", JOptionPane.ERROR_MESSAGE);
		}
	}

	/** 关闭客户端 */
	private void logout() {
		int select = JOptionPane.showConfirmDialog(ChatFrame.this,
				"确定退出吗？\n\n退出程序将中断与服务器的连接!", "退出聊天室",
				JOptionPane.YES_NO_OPTION);
		if (select == JOptionPane.YES_OPTION) {
			Request req = new Request();
			req.setAction("exit");
			req.setAttribute("user", DataBuffer.currentUser);
			try {
				ClientUtil.sendTextRequest(req);
			} catch (IOException ex) {
				ex.printStackTrace();
			}finally{
				System.exit(0);
			}
		}else{
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}
	}
	public void LogOutMe(){  
		JOptionPane.showMessageDialog(null,"您被管理员退出群聊!");
		
		ChatFrame.this.dispose();
		/*Request req = new Request();
		req.setAction("exit");
		req.setAttribute("user", DataBuffer.currentUser);
		try {
			ClientUtil.sendTextRequest(req);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			//System.exit(0);
			ChatFrame.this.dispose();
		}*/
    }  
	private JLabel MyFriend_Label;
	private JLabel AddFriend_Label;
}