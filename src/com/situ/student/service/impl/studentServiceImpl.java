package com.situ.student.service.impl;

import java.io.DataOutput;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoImpl;
import com.situ.student.entity.Student;
import com.situ.student.entity.User;
import com.situ.student.service.IStudetService;

import page.PageBean;

public class studentServiceImpl implements IStudetService {
	IStudentDao dao = new StudentDaoImpl();
	@Override
	public boolean addStudent(Student student) {
		boolean bol = dao.checkName(student.getName());
		if(bol){//重名
			System.out.println("添加失败");
			return false;
		}
		dao.add(student);
		System.out.println("添加成功");
		return true;
	}
	@Override
	public PageBean getPageBean(int index,int pageSize) {
		PageBean pBean = new PageBean();
		//总页数=总人数/pageSize,向上取整
		int totalCount = dao.studentCount();
		int totalPage = (int)Math.ceil(totalCount/pageSize);
		System.out.println("总页数："+totalPage);
		pBean.setTotalPage(totalPage);
		//当前页
		pBean.setPageIndex(index);
		
		return pBean;
	}
	

	

}
