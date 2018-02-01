package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.situ.student.entity.Student;
import com.situ.student.util.C3P0Util;
import com.situ.student.util.dateBase;

public class C3P0Test {
	@Test
	public void findAll(){
	//创建C3P0数据库连接池
		DataSource dat = new ComboPooledDataSource();
		try {
			Connection connection = dat.getConnection();
			String sql = "select * from student";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			Map<Integer,Student> list = new HashMap<Integer,Student>();
			while(result.next()){
				int id = result.getInt("id");
				String name = result.getString("NAME");
				int age = result.getInt("age");
				String gender = result.getString("gender");
				String adress = result.getString("adress");
				Student s = new Student(id,name,age,gender,adress);
				list.put(id, s);
			}
			   System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void t3(){
		// 1.创建核心类QueryRunner
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
//		Connection gc = C3P0Util.gc();
		 // 2.编写sql
		String sql = "select * from student";
		 // 3.执行查询操作
		try {
			List<Student>list = queryRunner.query(sql, new BeanListHandler<Student>(Student.class));
			for (Student student : list) {
				System.out.println(student);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
