package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jvnet.substance.skin.*;
import client.ui.LoginFrame;

/** 客户端入口类 */
public class ClientMain {

	public static void main(String[] args) {
		connection(); //连接到服务器
		
		//设置外观感觉
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true); 
		try {
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
			UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		new LoginFrame();  //启动登录窗体
	}

	/** 连接到服务器 */
	public static void connection() {
		String ip = DataBuffer.configProp.getProperty("ip");
		int port = Integer.parseInt(DataBuffer.configProp.getProperty("port"));
		try {
			DataBuffer.clientSeocket = new Socket(ip, port);
			DataBuffer.oos = new ObjectOutputStream(DataBuffer.clientSeocket.getOutputStream());
			DataBuffer.ois = new ObjectInputStream(DataBuffer.clientSeocket.getInputStream());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), 
					"连接服务器失败,请检查!","服务器未连上", JOptionPane.ERROR_MESSAGE);//否则连接失败
			System.exit(0);
		}
	}
}
