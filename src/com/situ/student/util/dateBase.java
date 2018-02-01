package com.situ.student.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;
/*
 * 自定义数据库连接池
 * */

import org.apache.tomcat.jni.Pool;
import org.junit.Test;

import com.situ.student.entity.Student;
public class dateBase implements DataSource {
	//1、创建容器，用来存放Connection对象
	static LinkedList<Connection> pool = new LinkedList<Connection>();
	//2、创建5个Connection放到容器里面
	static{
		for(int i = 0;i < 5;i ++ ){
			try {
				Connection coo = JDBCUtil.coo();
				pool.add(coo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		//使用前先判断
		if(pool.isEmpty()){
			for(int i=0;i<5;i++){
				try {
					pool.add(JDBCUtil.coo());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		 //从池子里面获取一个Connection
		return pool.removeFirst();
	}
	 /**
     * 使用完后不是close这个Connection，而是归还给连接池
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
           pool.add(connection);//使用完后归还到连接池
        }
    }
    
    @Test
    public void testDataSource() throws SQLException{
    	dateBase base = new dateBase();
    	Connection con = null;
    	try {
			 con = base.getConnection();
			String sql = "select * from student";
			PreparedStatement pre = con.prepareStatement(sql);
			ResultSet resultSet = pre.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("adress");
				Student student = new Student(id,name,age,gender,address);
				System.out.println(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			base.releaseConnection(con);
		}

    }

    
    
    

	
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
