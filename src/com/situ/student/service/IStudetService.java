package com.situ.student.service;

import com.situ.student.entity.Student;
import com.situ.student.entity.User;

import page.PageBean;

public interface IStudetService {
	//处理业务
	public boolean addStudent(Student student);
	//得到page参数
	public PageBean getPageBean(int index,int pageSize);
	
}
