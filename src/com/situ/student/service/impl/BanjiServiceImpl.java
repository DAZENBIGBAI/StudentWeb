package com.situ.student.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.situ.student.dao.IBanjiDao;
import com.situ.student.dao.IBanjiDao2;
import com.situ.student.dao.impl.BanjiDaoImpl;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;
import com.situ.student.service.IBanjiService;
import com.situ.student.util.MydatbtisUtils;

public class BanjiServiceImpl implements IBanjiService {
	IBanjiDao dao = new BanjiDaoImpl();
	@Override
	public List<Banji> findAllBanji() {
		List<Banji> list = dao.findAllBanji();
		return list;
	}
	@Override
	public List<Integer> count() {
		List countStudent = dao.countStudent();
		return countStudent;
	}
	@Override
	public List<Student> findStudentByBanji(int id) {
		SqlSession sqlSession = MydatbtisUtils.getSqlSession();
		IBanjiDao2 dao2 = sqlSession.getMapper(IBanjiDao2.class);
		System.out.println("进来  BanjiServiceImpl.findStudentByBanji()");
		List<Student> studentList = dao2.findStudentByBanji(id);
		return studentList;
	}
	@Test
	public void t1(){
		SqlSession sqlSession = MydatbtisUtils.getSqlSession();
		IBanjiDao2 dao2 = sqlSession.getMapper(IBanjiDao2.class);
		int id = 1;
		List<Student> studentList = dao2.findStudentByBanji(id);
		System.out.println(studentList);
	}
  
}
