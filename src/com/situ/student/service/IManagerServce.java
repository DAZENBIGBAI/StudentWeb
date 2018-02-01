package com.situ.student.service;

import java.util.List;
import java.util.Map;

import com.situ.student.entity.Course;

import page.PageBean;

public interface IManagerServce {
	//分页显示
	public List<Map<String,Object>> pageManager(int index, int pagesize);

	public PageBean getPageBean(int index, int pageSize);
	////查询班级没有选择的课程
	public List<Course> findNotC(int parseInt);
	//给班级添加课程
	public boolean addCourse(int bid,int cid);
}
