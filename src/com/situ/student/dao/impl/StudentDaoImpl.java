package com.situ.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.situ.student.dao.IStudentDao;
import com.situ.student.entity.Student;
import com.situ.student.util.C3P0Util;
import com.situ.student.util.JDBCUtil;

import page.PageBean;


public class StudentDaoImpl implements IStudentDao{

	@Override//添加add
	public boolean add(Student s) {
		Connection con = null;
		try {
		    con = JDBCUtil.coo();
			String sql ="INSERT INTO student(NAME,age,gender,adress,banji_id)VALUES(?,?,?,?,?);";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, s.getName());
			pre.setInt(2, s.getAge());
			pre.setString(3, s.getGender());
			pre.setString(4, s.getAdress());
			pre.setInt(5, Integer.parseInt(s.getBanji_id()));
			int result = pre.executeUpdate();
			if(result>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override//删除
	public boolean delete(int id) {
		Connection con = null;
		try {
			 con = JDBCUtil.coo();
			 String sql = "DELETE FROM student WHERE id=?;";
			 PreparedStatement pre= con.prepareStatement(sql);
			 pre.setInt(1, id);
			 int result = pre.executeUpdate();
			 if(result>0){
				 return true;
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override//修改
	public int update(Student s) {
		Connection coo=null;
		int res=0;
		try {
			coo = JDBCUtil.coo();
			String sql = "UPDATE student SET NAME=?,age=?,gender=?,adress=? WHERE id=?";
			PreparedStatement pre= coo.prepareStatement(sql);
			pre.setString(1, s.getName());
			pre.setInt(2, s.getAge());
			pre.setString(3, s.getGender());
			pre.setString(4, s.getAdress());
			pre.setInt(5, s.getId());
			res = pre.executeUpdate();
			System.out.println("修改语句："+pre);
			System.out.println("修改的行数："+res);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				coo.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
		
	}

	@Override//根据id查找
	public Student findById(int id) {
		Connection con = null;
		Student s = null;
		try {
			con = JDBCUtil.coo();
			String sql = "SELECT *FROM student WHERE id=?;";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet result = pre.executeQuery();
			while(result.next()){
				int num = result.getInt("id");
				String name = result.getString("NAME");
				int age = result.getInt("age");
				String gender = result.getString("gender");
				String adress = result.getString("adress");
				s = new Student(num,name,age,gender,adress);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
		
	}

	@Override
	public List<Student> findAll() {
		Connection con = null;
		Statement sta = null;
		String sql = "SELECT id,NAME,age,gender,adress FROM student;";
		List<Student> list = new ArrayList<Student>();
		try {
			con =JDBCUtil.coo();
			sta = con.createStatement();
			ResultSet result = sta.executeQuery(sql);
			while(result.next()){
				int id = result.getInt("id");
				String name = result.getString("NAME");
				int age = result.getInt("age");
				String gender = result.getString("gender");
				String adress = result.getString("adress");
				Student s = new Student(id,name,age,gender,adress);
				list.add(s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean checkName (String name){
		QueryRunner q = new QueryRunner(C3P0Util.gd());
		String sql = "select count(*)from student where name=?";
		Object[] param = {name};
		try {
			 long count = (long)q.query(sql, new ScalarHandler(),param);
			 if(count>0){
				 return true;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		List<Student> all = findAll();
//		for (int i = 0; i < all.size(); i++) {
//			if(name.equals(all.get(i).getName())){
//				all.remove(i);
//			}
//		}
		return false;
	}

	

	@Override//分页显示
	public List<Student> getPageList(Integer index,int pageSize) {
		int offset = (index-1)*pageSize;
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "select * from student limit ?,?";
		Object[] param = {offset,pageSize};
		List<Student> list=null;
		try {
			list = queryRunner.query(sql,  new BeanListHandler<Student>(Student.class),param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	//获得总人数
	@Override
	public int studentCount() {
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "SELECT COUNT(*)FROM student";
		long res = 0;
		try {
			res = (long)queryRunner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int) res;
	}
	
	@Override//批量删除
	public  boolean deleteAll(String[] strId) {
		/*QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "delete from student where id=?";
		Object[] para = {};*/
		Connection coo = null;
		try {
			coo = JDBCUtil.coo();
			String sql = "delete from student where id=?"; 
			PreparedStatement pre = coo.prepareStatement(sql);
			for (String str : strId) {
				pre.setInt(1, Integer.parseInt(str));
				pre.addBatch();
			}
			int[] executeBatch = pre.executeBatch();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				coo.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}














