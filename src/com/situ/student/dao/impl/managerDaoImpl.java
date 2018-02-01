package com.situ.student.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.situ.student.dao.IManagerDao;
import com.situ.student.entity.Course;
import com.situ.student.util.C3P0Util;

public class managerDaoImpl implements IManagerDao{

	@Override
	public List<Map<String, Object>> findAll() {
		
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "SELECT s.name AS s_name ,age ,gender ,adress ,b.name AS b_name,c.name AS c_name,credit FROM student AS s INNER JOIN banji AS b ON s.banji_id=b.id "
				+"INNER JOIN bc  ON bc.bid=b.id "
				+"INNER JOIN course AS c ON bc.cid=c.id ";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = queryRunner.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> pageManager(int index, int pageSize) {
		int offset = (index-1)*pageSize;
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "SELECT s.name AS s_name ,age ,gender ,adress ,b.name AS b_name,c.name AS c_name,credit FROM student AS s "
				+ "INNER JOIN banji AS b ON s.banji_id=b.id "
				+ "INNER JOIN bc ON b.id=bc.bid " 
				+" INNER JOIN course AS c ON bc.cid=c.id LIMIT ?,? ";
		Object[] params = {offset,pageSize};
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = queryRunner.query(sql, new MapListHandler(),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//统计总数
	@Override
	public int ManagerCount() {
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "SELECT COUNT(*) FROM student AS s "
				+ "INNER JOIN banji AS b ON s.banji_id=b.id "
				+" INNER JOIN bc ON b.id=bc.bid " 
				+" INNER JOIN course AS c ON bc.cid=c.id ";
		long query = 0;
		try {
			query = (long) queryRunner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int res= (int)query;
		return res;
	}

	@Override//查询班级没有选择的课程
	public List<Course> findNotC(int banji_id) {
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "SELECT id,name FROM course AS c LEFT JOIN bc ON c.id=bc.cid "
				+ " WHERE NAME NOT IN "
				+ "(SELECT NAME FROM course AS c INNER JOIN bc ON c.id=bc.cid WHERE bc.bid=?)GROUP BY NAME ";
		Object [] params= {banji_id};
		List<Course> list=null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Course>(Course.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean addCourse(int bid, int cid) {
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "INSERT INTO bc VALUES (?,?) ";
		Object[] params = {bid,cid};
		try {
			int res = queryRunner.update(sql,params);
			if (res > 0) {
	               System.out.println("添加成功");
	           } else {
	               System.out.println("添加失败");
	           }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	
	
	
	
	
	

}
