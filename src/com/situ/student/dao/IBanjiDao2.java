package com.situ.student.dao;

import java.util.List;

import com.situ.student.entity.Student;

public interface IBanjiDao2 {
	//查找每个班级的学生
	public List<Student> findStudentByBanji(int id);
}
