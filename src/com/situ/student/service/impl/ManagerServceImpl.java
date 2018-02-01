package com.situ.student.service.impl;


import java.util.List;
import java.util.Map;

import com.situ.student.dao.IManagerDao;
import com.situ.student.dao.impl.managerDaoImpl;
import com.situ.student.entity.Course;
import com.situ.student.service.IManagerServce;

import page.PageBean;

public class ManagerServceImpl implements IManagerServce {
	IManagerDao dao = new managerDaoImpl();
	@Override
	public List<Map<String, Object>> pageManager(int index, int pagesize) {
		List<Map<String, Object>> list = dao.pageManager(index,pagesize);
		return list;
	}
	@Override
	public PageBean getPageBean(int index, int pageSize) {
		PageBean pBean = new PageBean();
		//总页数=总人数/pageSize,向上取整
		int totalCount = dao.ManagerCount();
		int totalPage = (int)Math.ceil((double)totalCount/pageSize);
		System.out.println("总页数："+totalPage);
		pBean.setTotalPage(totalPage);
		//当前页
		pBean.setPageIndex(index);
		
		return pBean;
	}
	@Override
	public List<Course> findNotC(int id) {
		List<Course> list = dao.findNotC(id);
		return list;
	}
	@Override
	public boolean addCourse(int bid, int cid) {
		dao.addCourse(bid, cid);
		return true;
	}

}
