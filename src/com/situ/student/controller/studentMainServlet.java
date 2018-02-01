package com.situ.student.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Request;

import com.situ.student.dao.IBanjiDao;
import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.BanjiDaoImpl;
import com.situ.student.dao.impl.StudentDaoImpl;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;
import com.situ.student.service.IStudetService;
import com.situ.student.service.impl.studentServiceImpl;

import page.PageBean;



public class studentMainServlet extends baseServlet {
	IStudentDao dao = new StudentDaoImpl(); 
	IStudetService service = new studentServiceImpl();
	IBanjiDao banjiDao = new BanjiDaoImpl();
	//转发到addStudent.jsp
	public void getAddStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Banji> allBanji = banjiDao.findAllBanji();
		req.setAttribute("allBanji", allBanji);	
		req.getRequestDispatcher("/WEB-INF/jsp/addStudent.jsp").forward(req, resp);
		}
	
	
    private void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String[] strId = req.getParameterValues("selectIds");
		  for (String string : strId) {
		      System.out.println(string);
		   }
		dao.deleteAll(strId);
		resp.sendRedirect(req.getContextPath()+"/student?method=pageStudent");
	}
	private void checkName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");
    	boolean res = dao.checkName(name);
    	resp.setContentType("text/html;utf-8");
    	resp.getWriter().write("{\"res\":"+res+"}");
    }

	//分页显示学生
	private void pageStudent(HttpServletRequest req, HttpServletResponse resp) {
		//取数据 index当前页
		System.out.println("分页显示");
		String strIndex = req.getParameter("index");
		int pageSize=5;
		int index = 1;
		if(strIndex !=null && !"".equals(strIndex)){
			index = Integer.parseInt(strIndex);
		}
		List<Student> pageList = dao.getPageList(index,pageSize);
		PageBean pageBean = service.getPageBean(index,pageSize);
		//将查询结果放到request域对象中
		req.setAttribute("pageList", pageList);
		req.setAttribute("pageBean", pageBean);
		//转发
		try {
			req.getRequestDispatcher("/WEB-INF/jsp/findStudent.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	//根据ID查找 
    private void findById (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strId = req.getParameter("id");
		int id = Integer.parseInt(strId);
		System.out.println(id);
    	Student student = dao.findById(id);
		req.setAttribute("student", student);
		System.out.println(student);
		req.getRequestDispatcher("/WEB-INF/jsp/findById.jsp").forward(req, resp);
	}
	//根据id修改表
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	req.setCharacterEncoding("utf-8");
    	String strId = req.getParameter("id");
    	String name = req.getParameter("name");
    	String age =req.getParameter("age");
    	String gender = req.getParameter("gender");
    	String address = req.getParameter("address");
    	Student s =new Student(Integer.parseInt(strId),name,Integer.parseInt(age),gender,address);
		int update = dao.update(s);
		System.out.println("行数："+update);
//		resp.setContentType("text/html;Charset=utf-8");
		resp.sendRedirect(req.getContextPath()+"/student?method=pageStudent");
	}
	//根据id删除
    
	private void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
    	String str = req.getParameter("id");
    	System.out.println(str);
    	int id =Integer.parseInt(str);
		boolean result = dao.delete(id);
		if(result){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		resp.sendRedirect(req.getContextPath()+"/student?method=pageStudent");
	}
    
	//查找所有
	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> findStudent = dao.findAll();
//		System.out.println(findStudent);
		//存放到request域中
		req.setAttribute("all", findStudent);
		//转发到jsp页面展示
		req.getRequestDispatcher("/WEB-INF/jsp/findStudent.jsp").forward(req, resp);
	}
	
	//添加
	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		IStudetService ser = new studentServiceImpl();
		//req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String banji_id = req.getParameter("banji_id");
		Student student = new Student(name,Integer.parseInt(age),gender,address,banji_id);
		System.out.println(student);
		ser.addStudent(student);
		resp.setContentType("text/html;Charset=utf-8");
		resp.sendRedirect(req.getContextPath()+"/student?method=pageStudent");
	}

}
