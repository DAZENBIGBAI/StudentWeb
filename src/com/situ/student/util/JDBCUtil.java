package com.situ.student.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class JDBCUtil  {
	
	 private static String driver;
	 private static String url;
	 private static String user;
	 private static String password;
	
//	public ServletContext getServletContext() {};
	 /*public static void init(ServletContext ser) {
		 Properties pro = new Properties();
		 InputStream fil;
		 
		 try {
			 fil = ser.getResourceAsStream("/WEB-INF/classes/db.properties");
			 pro.load(fil);
			 
			 //初始化链接参数
			 driver = pro.getProperty("driver");
			 url = pro.getProperty("url");
			 user = pro.getProperty("user");
			 password = pro.getProperty("password");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 
	 }*/

	static{
		//初始化静态属性
		//1、利用properties读取文件
		//2、从配置文件中查找相应的参数值
	//加载驱动
		Properties pro = new Properties();
		 InputStream fil;
		 
		 try {
			 fil = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			 pro.load(fil);
			 
			 //初始化链接参数
			 driver = pro.getProperty("driver");
			 url = pro.getProperty("url");
			 user = pro.getProperty("user");
			 password = pro.getProperty("password");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	
}
public static Connection coo() throws Exception{
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
		
	}
}
		
	

	
