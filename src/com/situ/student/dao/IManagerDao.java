package com.situ.student.dao;

import java.util.List;
import java.util.Map;

import com.situ.student.entity.Course;

public interface IManagerDao {
	public  List<Map<String, Object>> findAll();

	public List<Map<String, Object>> pageManager(int index, int pagesize);

	public int ManagerCount();
	
	//查询班级没有选择的课程
	public List<Course> findNotC(int banji_id);
	//给班级添加课程
	public boolean addCourse(int bid,int cid);
	
}
