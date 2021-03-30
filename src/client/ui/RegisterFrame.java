package client.ui;

import java.awt.event.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import client.DataBuffer;
import client.util.ClientUtil;
import common.model.entity.*;

/* 注册窗体 */
public class RegisterFrame extends JFrame {
	private static final long serialVersionUID = -768631070458723803L;
	private JPasswordField pwdFld;
	private JPasswordField pwd2Fld;
	private JTextField nickname;
	private JTextField PhoneNum;
	private JTextField age;
	private JTextField username;
	private JTextField usertype;
	private JComboBox head;
	private JRadioButton sex0;
	private JRadioButton sex1;
	private JButton ok;
	private JButton reset;
	private JButton cancel;
	
	public RegisterFrame(){
		this.init();
		setVisible(true);
	}
	
	public void init(){
		this.setTitle("注册JQ新账号");//设置标题
		setBounds((DataBuffer.screenSize.width - 430)/2, 
				(DataBuffer.screenSize.height - 500)/2, 
				430, 350);
		getContentPane().setLayout(null);
		setResizable(false);

		JLabel label = new JLabel("昵称:"); //label显示
		label.setBounds(24, 212, 59, 17);
		getContentPane().add(label);
		
		nickname = new JTextField(); //昵称
		nickname.setBounds(90, 212, 110, 22);
		getContentPane().add(nickname);
		
		JLabel label5 = new JLabel("密码:*");
		label5.setBounds(24, 72, 50, 17);
		getContentPane().add(label5);
		
		JLabel label3 = new JLabel("确认密码:*");
		label3.setBounds(24, 107, 65, 17);
		getContentPane().add(label3);
		
		pwdFld = new JPasswordField();//密码框
		pwdFld.setBounds(90, 70, 110, 22);
		getContentPane().add(pwdFld);

		pwd2Fld = new JPasswordField();
		pwd2Fld.setBounds(90, 105, 110, 22);
		getContentPane().add(pwd2Fld);
		
		JLabel label7 = new JLabel("手机号*");
		label7.setBounds(24, 142, 65, 17);
		getContentPane().add(label7);
		
		JLabel label8 = new JLabel("年龄*");
		label8.setBounds(24, 177, 65, 17);
		getContentPane().add(label8);
		
		PhoneNum = new JTextField(); //手机号
		PhoneNum.setBounds(90, 142, 110, 22);
		getContentPane().add(PhoneNum);
		
		age = new JTextField(); //年龄
		age.setBounds(90, 177, 110, 22);
		getContentPane().add(age);
		
		JLabel labe9 = new JLabel("账号*:"); //label显示24, 36
		labe9.setBounds(24, 36, 59, 17);
		getContentPane().add(labe9);
		
		username = new JTextField(); //账号90, 34, 110, 22
		username.setBounds(90, 34, 110, 22);
		getContentPane().add(username);

		JLabel label4 = new JLabel("性别:");
		label4.setBounds(230, 36, 50, 17);
		getContentPane().add(label4);
		
		sex1 = new JRadioButton("男",true);
		sex1.setBounds (290, 36, 60, 25);
		getContentPane().add(sex1);
		sex0 = new JRadioButton("女");
		sex0.setBounds(360, 36, 60, 25);
		getContentPane().add(sex0);
		ButtonGroup buttonGroup = new ButtonGroup();//单选按钮组
		buttonGroup.add(sex0);
		buttonGroup.add(sex1);
		
		JLabel label6 = new JLabel("头像:");
		label6.setBounds(230, 72, 45, 17);
		getContentPane().add(label6);
		
		head = new JComboBox();//下拉列表图标
		head.setBounds(290, 70, 65, 45);
		head.setMaximumRowCount(5);
		for (int i = 0; i < 11; i++) {
			head.addItem(new ImageIcon("images/" + i + ".png"));
			//通过循环添加图片，注意图片名字要取成1,2,3,4,5,等	
		}
		head.setSelectedIndex(0);
		getContentPane().add(head);
		
		JLabel labe10 = new JLabel("[用户类型]*:"); //label显示
		labe10.setBounds(230, 150, 80, 45);
		getContentPane().add(labe10);
		
		usertype = new JTextField(); //昵称
		usertype.setBounds(230, 185, 110, 22);
		getContentPane().add(usertype);
		usertype.setText("user");
		
		//按钮
		ok = new JButton("确认");
		ok.setBounds(27, 260, 60, 28);
		getContentPane().add(ok);

		reset = new JButton("重填");
		reset.setBounds(123, 260, 60, 28);
		getContentPane().add(reset);

		cancel = new JButton("取消");
		cancel.setBounds(268, 260, 60, 28);
		getContentPane().add(cancel);
		
		//////////////////////注册事件监听器////////////////////////
		//取消按钮监听事件处理
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				RegisterFrame.this.dispose();
			}
		});
		//关闭窗口
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				RegisterFrame.this.dispose();
			}
		});
		
		// 重置按钮监听事件处理
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				nickname.setText("");
				pwdFld.setText("");
				pwd2Fld.setText("");
				username.setText("");
				age.setText("");
				PhoneNum.setText("");
				usertype.setText("user");
				nickname.requestFocusInWindow();//用户名获得焦点
			}
		});
		
		//确认按钮监听事件处理
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
				String regex_num_A_Z = "^[0-9a-zA-Z]{1,}$";
				String phoneNum = PhoneNum.getText();
				String password = new String(pwdFld.getPassword());
				if (pwdFld.getPassword().length==0 || pwd2Fld.getPassword().length==0 
						|| PhoneNum.getText().equals("") || age.getText().equals("") || username.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "带 “ * ” 为必填内容!");
					return;
				//判断用户名和密码是否为空
				}else if(phoneNum.length() != 11){
	                System.out.println("手机号应为11位数");
	                JOptionPane.showMessageDialog(RegisterFrame.this, "手机号应为11位数");
	                return;
	            } else{
		            Pattern p = Pattern.compile(regex);
		            Matcher m = p.matcher(phoneNum);
		            boolean isMatch = m.matches();
		            System.out.println(phoneNum.length());
		            if(isMatch){
		               System.out.println("您的手机号" + phoneNum + "是正确格式@——@");
		            } else {
		               JOptionPane.showMessageDialog(RegisterFrame.this, "手机号格式不正确");
		               return;
		            }
	            }
				//验证密码
				Pattern p = Pattern.compile(regex_num_A_Z);
	            Matcher m = p.matcher(password);
	            boolean isMatch = m.matches();
	            if(isMatch){
	               System.out.println("您的密码" + password + "是正确格式@——@");
	            } else {
	               JOptionPane.showMessageDialog(RegisterFrame.this, "您的密码格式不正确。仅由数字和字母组成");
	               return;
	            }
	            if (password.length()>16) {
	            	JOptionPane.showMessageDialog(RegisterFrame.this, "您的密码格式不正确。16位以内");
	            	return;
				}
	            if (Integer.valueOf(age.getText()) > 150) {
	            	JOptionPane.showMessageDialog(RegisterFrame.this, "年龄太大");
	            	return;
				}
	            if (username.getText().length()>16) {
	            	JOptionPane.showMessageDialog(RegisterFrame.this, "账号16位以内");
	            	return;
				}
	            if (!new String(pwdFld.getPassword()).equals(new String(pwd2Fld.getPassword()))) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "两次输入密码不一致!");
					pwdFld.setText("");
					pwd2Fld.setText("");
					pwdFld.requestFocusInWindow();
					//判断两次密码是否一致
					return;
				}
	            System.out.println(usertype.getText().equals("user") || usertype.getText().equals("admin"));
	            System.out.println(usertype.getText().equals("user"));
	            System.out.println(usertype.getText().equals("admin"));
	            if (!(usertype.getText().equals("user") || usertype.getText().equals("admin"))) {
					System.out.println(usertype.getText());
					JOptionPane.showMessageDialog(RegisterFrame.this, "usertype必须是admin或者user");
					return;
				}else{
					User user = new User();
					String type = usertype.getText();
					user.setUserpwd(pwdFld.getText());
					if (nickname.equals("") || nickname == null) {
						user.setNikename("无名");
					} else {
				 		user.setNikename(nickname.getText());
					}
					user.setUsername(username.getText());
					char c = sex0.isSelected()?'女':'男';
					user.setSex(String.valueOf(c));
					user.setHead(head.getSelectedIndex());
					user.setType(type);
					user.setAge(Integer.valueOf(age.getText()));
					System.out.println(phoneNum);
					Long b = new Long(phoneNum);
					user.setPhoneNumber(b);
					try {
						RegisterFrame.this.registe(user);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}
	//注册方法
	private void registe(User user) throws IOException, ClassNotFoundException{
		Request request = new Request();
		request.setAction("userRegiste");
		request.setAttribute("user", user);
		//获取响应
		Response response = ClientUtil.sendTextRequest(request);
		
		ResponseStatus status = response.getStatus();
		switch(status){
		case OK:
			User user2 = (User)response.getData("user");
			JOptionPane.showMessageDialog(RegisterFrame.this, 
					"恭喜您，您的Q号:"+ user2.getUserid() + ",请牢记!!!",
					"注册成功",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			break;
		default:
			JOptionPane.showMessageDialog(RegisterFrame.this, 
					"注册失败，请稍后再试！！！","服务器内部错误！",JOptionPane.ERROR_MESSAGE);
		}
	}
}