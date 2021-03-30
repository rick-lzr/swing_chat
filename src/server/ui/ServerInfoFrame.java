package server.ui;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import client.ui.ChatFrame;
import client.util.ClientUtil;
import common.model.entity.Request;
import common.model.entity.User;
import server.DataBuffer;
import server.controller.RequestProcessor;
import server.model.entity.MyJTextField;
import server.model.service.UserService;
/** 服务器信息窗体 */
public class ServerInfoFrame extends JFrame {
	private static final long serialVersionUID = 6274443611957724780L;
	public ServerInfoFrame() {
		init();
		loadData();
		setVisible(true);
	}
	RequestProcessor requestProcessor = RequestProcessor.getInstance();
	
	public void init(){  //初始化
		this.setTitle("chatroom服务器端");//设置服务器启动标题
		this.setBounds((DataBuffer.screenSize.width - 500)/2, 
				(DataBuffer.screenSize.height - 375)/2, 500, 375);
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		panel.setBorder(BorderFactory.createTitledBorder(border, 
				"服务器监控", TitledBorder.LEFT,TitledBorder.TOP));
		this.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("服务器启动于: " 
				+ DataBuffer.serverSocket.getLocalPort() + " 端口  ");
		panel.add(label);
		JButton exitBtn = new JButton("退出按钮");//关闭关闭服务器按钮
		panel.add(exitBtn);
		
		//ʹ将数据注入TableModel
		final JTable onlineUserTable = new JTable(DataBuffer.onlineUserTableModel);
		final JTable registedUserTable = new JTable(DataBuffer.registedUserTableModel);
		
	    
		onlineUserTable.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
			    //得到选中的行列的索引值
				if (e.getButton() == MouseEvent.BUTTON3){
					int r= onlineUserTable.getSelectedRow();
					int c= onlineUserTable.getSelectedColumn();
					//得到选中的单元格的值，表格中都是字符串
					Object value= onlineUserTable.getValueAt(r, c);
					Integer userid= Integer.valueOf((String) onlineUserTable.getValueAt(r, 0));
					String info=r+"行"+c+"列值 : "+value.toString();
					JPopupMenu m_popupMenu = new JPopupMenu();
		            JMenuItem delMenItem = new JMenuItem();
		            delMenItem.setText("  踢出  ");
		            delMenItem.addActionListener(new java.awt.event.ActionListener() {
		                public void actionPerformed(java.awt.event.ActionEvent evt) {
		                    logout_user(evt,userid);
		                }
		            });
		            m_popupMenu.add(delMenItem);
		            m_popupMenu.show(onlineUserTable, e.getX(), e.getY());
				}
	       }
        }); 
		
        
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("在线用户", new JScrollPane(onlineUserTable));
		tabbedPane.addTab("已注册用户", new JScrollPane(registedUserTable));
		tabbedPane.setTabComponentAt(0, new JLabel("在线用户┗|｀O′|┛ 嗷~~"));
		this.add(tabbedPane, BorderLayout.CENTER);
		 
		final JLabel stateBar = new JLabel("", SwingConstants.RIGHT);
		stateBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//用定时任务来显示当前时间
		new java.util.Timer().scheduleAtFixedRate(
				new TimerTask(){
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					public void run() {
						stateBar.setText("时间是" + df.format(new Date()) + "  ");
					}
				}, 0, 1000);
		this.add(stateBar, BorderLayout.SOUTH); //把状态栏添加到窗体的南边
		
		//关闭窗口
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				logout();
			}
		});
		
		/* 添加关闭服务器按钮事件处理方法 */
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				logout();
			}
		});
		
	}
	private void logout_user(java.awt.event.ActionEvent e,Integer userid) {
		int select = JOptionPane.showConfirmDialog(ServerInfoFrame.this,
				"确定退出吗？\n\n退出程序将中断与服务器的连接!", "退出聊天室",
				JOptionPane.YES_NO_OPTION);
		if (select == JOptionPane.YES_OPTION) {
			UserService userService = new UserService();
			User user = userService.loadUser(userid);
			System.out.println(user);
			
			try {
				requestProcessor.logout_user(user);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("踢人失败");
				e1.printStackTrace();
			}
		}else{
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}
	}

	/** 把所有已注册的用户信息加载到RegistedUserTableModel中 */
	private void loadData(){
		List<User> users = new UserService().loadAllUser();
		
		for (User user : users) {
			
			DataBuffer.registedUserTableModel.add(new String[]{
				String.valueOf(user.getUserid()),
				String.valueOf(user.getPhoneNumber()),
				user.getNikename(),
				String.valueOf(user.getSex())
			});
		}
	}

	/** 关闭服务器 */
	private void logout() {
		int select = JOptionPane.showConfirmDialog(ServerInfoFrame.this, 
				"确定关闭吗？\n\n关闭服务器将中断与所有客户端的连接!", 
				"关闭服务器", 
				JOptionPane.YES_NO_OPTION);
		requestProcessor.logout_all();
		//如果用户点击的是关闭服务器按钮时会提示是否确认关闭。
		if (select == JOptionPane.YES_OPTION) {
			System.exit(0);//退出系统
		}else{
			//覆盖默认的窗口关闭事件动作
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		}
	}
}