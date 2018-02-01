package com.situ.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.situ.student.dao.IBanjiDao;
import com.situ.student.entity.Banji;
import com.situ.student.util.C3P0Util;
import com.situ.student.util.JDBCUtil;

public class BanjiDaoImpl implements IBanjiDao {
	
	@Override//查找所有班级
	public List<Banji> findAllBanji() {
		
		List<Banji> list= new ArrayList();
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "SELECT *FROM banji ";
		try {
			list = queryRunner.query(sql, new BeanListHandler<Banji>(Banji.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		/*try {
			coo = JDBCUtil.coo();
			String sql = "SELECT *FROM banji";
			PreparedStatement pre = coo.prepareStatement(sql);
			ResultSet result = pre.executeQuery();
			while(result.next()){
				int int1 = result.getInt("id");
				String string = result.getString("name");
				Banji banji = new Banji(int1,string);
				list.add(banji);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				coo.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;*/
	}

	@Override//添加班级
	public int addBanji(Banji b) {
		int result=0;
		Connection con=null;
		try {
			con = JDBCUtil.coo();
			String sql = "INSERT INTO banji (NAME) VALUES (?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, b.getName());
			result = pre.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}

	@Override//根据id删除
	public int deleteBanji(int id) {
		Connection connection =null;
		int result = 0;
		try {
			connection = JDBCUtil.coo();
			String sql = "DELETE FROM banji WHERE id=?";
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			result = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}

	

	@Override//根据id查找班级
	public Banji findBanjiById(int id) {
		Connection con = null;
		Banji banji = null;
		try {
			con = JDBCUtil.coo();
			String sql = "select *from banji where id=?";
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet res = prepareStatement.executeQuery();
			if(res.next()){
				String name = res.getString("name");
				int int1 = res.getInt("id");
				banji = new Banji(int1,name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return banji;
		
	}

	@Override//修改班级信息
	public int updateBanji(Banji banji) {
		Connection connection = null;
		int res = 0;
		try {
			connection =JDBCUtil.coo();
			String sql ="UPDATE banji SET NAME=? WHERE id=?"; 
			PreparedStatement pre = connection.prepareStatement(sql);
			System.out.println("进来语句为"+sql);
			pre.setString(1, banji.getName());
			pre.setInt(2, banji.getId());
			System.out.println("结束，语句为："+sql);
			res = pre.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
@Test
public void t2(){
	int n = updateBanji(new Banji(8,"hehe"));
	System.out.println(n);
}

@Override//统计班级人数
public List<Integer> countStudent()  {
	Connection coo = null;
	List<Integer> list = new ArrayList<Integer>();
	try {
		coo = JDBCUtil.coo();
		String sql = "SELECT COUNT(*) FROM student GROUP BY banji_id";
		PreparedStatement prepareStatement = coo.prepareStatement(sql);
		ResultSet res = prepareStatement.executeQuery();
		while(res.next()){
			int num = res.getInt(1);
			list.add(num);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			coo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	System.out.println("班级人数："+list);
	return list;
	/*QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
	String sql = "SELECT COUNT(*) FROM student GROUP BY banji_id";
	List list=null;
	
	try {
		Object[] query = queryRunner.query(sql, new ArrayHandler() );
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println("打印人数列表："+list);
	return list;*/
	
	}





}
