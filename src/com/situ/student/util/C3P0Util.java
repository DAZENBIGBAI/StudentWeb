package com.situ.student.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Statement;

public class C3P0Util {
	private static DataSource dat = new ComboPooledDataSource();
	
	public static DataSource gd(){
		return dat;
	}
	
	public static Connection gc (){
		Connection connection = null;
		try {
			connection = dat.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
	public static void release(Connection connection, Statement statement, ResultSet resultSet){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	
}


