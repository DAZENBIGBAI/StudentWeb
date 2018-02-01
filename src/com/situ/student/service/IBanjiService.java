package com.situ.student.service;

import java.util.List;

import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;

import web.findAllBanjiServlet;

public interface IBanjiService {
	//查找班级
	public List<Banji> findAllBanji();
	//统计每个班人数
	public List<Integer> count();
	//查找每个班级的学生
	public List<Student> findStudentByBanji(int id);
}
