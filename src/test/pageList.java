package test;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.situ.student.controller.studentMainServlet;
import com.situ.student.entity.Student;
import com.situ.student.util.C3P0Util;

public class pageList {
	private Integer index;
	private Integer size;
	public static List<Student> getPageList(Integer index,Integer size) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(C3P0Util.gd());
		String sql = "select * from student limit ?,?";
		Object[] param = {index,size};
		List<Student> list= queryRunner.query(sql,  new BeanListHandler<Student>(Student.class),param);
		return list;
	}
	public static void main(String[] args) throws SQLException {
		List<Student> pageList = getPageList(2,3);
		System.out.println(pageList);
	}		
}



