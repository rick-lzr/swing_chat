package server;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

import common.model.entity.User;
import server.model.entity.*;

/** 服务器端数据缓存 */
public class DataBuffer {
	/**服务器端套节字 */
	public static ServerSocket serverSocket;
	/** 在线用户的IO Map */
	public static Map<Long, OnlineClientIOCache> onlineUserIOCacheMap;
	/** 在线用户Map */
	public static Map<Long, User> onlineUsersMap;
	/** 服务器配置参数属性集 */
	public static Properties configProp;
	/** 已注册用户表的Model */
	public static RegistedUserTableModel registedUserTableModel;
	/** 当前在线用户表的Model */
	public static OnlineUserTableModel onlineUserTableModel;
	/** 当前服务器所在系统的屏幕尺寸 */
	public static Dimension screenSize;
	
	static{
		// 锟斤拷始锟斤拷
		onlineUserIOCacheMap = new ConcurrentSkipListMap<Long,OnlineClientIOCache>();
		onlineUsersMap = new ConcurrentSkipListMap<Long, User>();
		configProp = new Properties();
		registedUserTableModel = new RegistedUserTableModel();
		onlineUserTableModel = new OnlineUserTableModel();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// 锟斤拷锟截凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥硷拷
		try {
			configProp.load(Thread.currentThread()
								  .getContextClassLoader()
								  .getResourceAsStream("serverconfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
