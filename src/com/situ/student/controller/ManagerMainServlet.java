package com.situ.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.dao.IManagerDao;
import com.situ.student.dao.impl.managerDaoImpl;
import com.situ.student.service.IManagerServce;
import com.situ.student.service.impl.ManagerServceImpl;

import page.PageBean;

public class ManagerMainServlet extends baseServlet {
	IManagerDao dao = new managerDaoImpl();
	IManagerServce ser = new ManagerServceImpl();
	
	//分页显示
	public void pageManager(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String strIndex = request.getParameter("index");
		//默认第一页
		int index = 1;
		if(strIndex!=null && !"".equals(strIndex)){
			index= Integer.parseInt(strIndex);
		}
		//固定一页5条数据
		int pageSize = 5;
		List<Map<String, Object>> list = ser.pageManager(index, pageSize);
		request.setAttribute("list", list);
		PageBean pageBean = ser.getPageBean(index,pageSize);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/WEB-INF/jsp/manager_list.jsp").forward(request, response);
		
	}
	//获得所有信息
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Map<String, Object>> list = dao.findAll();
		request.setAttribute("managerList", list);
		request.getRequestDispatcher("/WEB-INF/jsp/manager_list.jsp").forward(request, response);
	}
	//获得转发选课页面
	public void getSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/jsp/select.jsp").forward(request, response);
		
	}
	//选课，添加课程给班级
	public void addcourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String sbid = request.getParameter("bid");
		int bid = Integer.parseInt(sbid);
		String scid = request.getParameter("cid");
		int cid = Integer.parseInt(scid);
		ser.addCourse(bid, cid);
		response.sendRedirect(request.getContextPath()+"/manager?method=pageManager");
	}
}






