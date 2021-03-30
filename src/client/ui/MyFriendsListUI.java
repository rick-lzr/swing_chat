package client.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import client.DataBuffer;
import common.model.entity.User;
import server.model.service.UserService;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;


public class MyFriendsListUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 我的好友*/
	
	private JPanel contentPane;
	private JTable tab;
	private JScrollPane scp;
	private JButton button;
	long id;
	int row=0,col=0; 
	/**
	 * Launch the application.
	 * @param id 
	 */
	
	public MyFriendsListUI(long id){
		this.init(id);
		this.id = id;
		loadData(id);
		setVisible(true);
	}
	/**
	 * Create the frame.
	 * @param id 
	 */
	public void init(long id) {
		setTitle("我的好友");
		setLocationRelativeTo(null);
		setBounds(100, 100, 343, 692);
		
		this.scp = new JScrollPane();
		this.scp.setBounds(0, 0, 343, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		button = new JButton("\u5237\u65B0\u597D\u53CB");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				buttonMouseClicked(me);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(104)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(109, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(609, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
		getContentPane().add(this.scp);
		
		List<User> users = new UserService().FindFriends(id);
		int count = 0;
		Object[][] friendsInfo = new Object[users.size()][2];
		for(User user : users){
			friendsInfo[count][0] = user.getUserid();
			friendsInfo[count][1] = user.getUsername();
			count++;
		}
		String[] title = {"id","姓名"};
		this.tab = new JTable(friendsInfo,title);
		this.tab.getTableHeader();
		this.scp.setViewportView(tab);
		tab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				tabMouseClicked(me);
			}
		});
		
		
		
	}
	
	public void buttonMouseClicked(MouseEvent me) 
	{
		
		if (button.getActionCommand()=="刷新好友") {
			this.init(id);
		}
		
	}
	public void tabMouseClicked(MouseEvent me)
	{
		if (me.getClickCount()==2) 
		{
			row = tab.getSelectedRow();
			col = tab.getSelectedColumn();
			
			System.out.println(row+""+col);
		}
		
	}
	
	public void loadData(long id){
		//加载当前用户数据
		/*List<User> users = new UserService().FindFriends(id);
		int count = 0;
		Object[][] friendsInfo = new Object[count][users.size()];
		for(User user : users){
			friendsInfo[count][0] = user.getUserid();
			friendsInfo[count][1] = user.getUsername();
			count++;
		}
		String[] title = {"id","姓名"};*/
		
		/*for (User user : users) {
			DataBuffer.myFriendsTableModel.add(new String[]{
				String.valueOf(user.getUserid()),
				user.getUserpwd(),
				user.getUsername(),
				String.valueOf(user.getUsersex())
			});
		}*/
	}

}
