package com.situ.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mchange.v2.codegen.bean.InnerBeanPropertyBeanGenerator;
import com.situ.student.dao.IBanjiDao;
import com.situ.student.dao.impl.BanjiDaoImpl;
import com.situ.student.entity.Banji;
import com.situ.student.entity.Student;
import com.situ.student.service.IBanjiService;
import com.situ.student.service.impl.BanjiServiceImpl;
public class banjiMainServlet extends baseServlet {
	IBanjiDao bDao = new BanjiDaoImpl();
	IBanjiService ser = new BanjiServiceImpl();
	//查找所有班级
	private void findAllBanji(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Banji> list = ser.findAllBanji();
		List<Integer> count = ser.count();
		for(int id=0;id<count.size();id++){
			Integer num = count.get(id);
			list.get(id).setCount(num);
		}
		req.setAttribute("all", list);
		req.getRequestDispatcher("/WEB-INF/jsp/findAllBanji.jsp").forward(req, resp);
	}
	
	//转发到addbanji.jsp
	public void getAddBanji(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/jsp/addBanji.jsp").forward(req, resp);
	}
	
	//添加班级
    private void addBanji(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	req.setCharacterEncoding("utf-8");
    	String name= req.getParameter("name");
    	Banji banji = new Banji(name);
		bDao.addBanji(banji);
		resp.sendRedirect(req.getContextPath()+"/banji?method=findAllBanji");
	}
    
  //根据id删除班级
    private void deleteBanji(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	String strId= req.getParameter("id");
    	int intId = Integer.parseInt(strId);
		bDao.deleteBanji(intId);
		resp.sendRedirect(req.getContextPath()+"/banji?method=findAllBanji");
	}
	
	///////////////////////////////根据id查找班级
	private void findBanjiById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strId = req.getParameter("id");
		int parseInt = Integer.parseInt(strId);
		Banji findBanjiById = bDao.findBanjiById(parseInt);
		req.setAttribute("banji", findBanjiById);
		req.getRequestDispatcher("/WEB-INF/jsp/findBanjiById.jsp").forward(req, resp);
	
	}
	//修改班级信息
    private void updateBanji(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	req.setCharacterEncoding("utf-8");
    	String banjiName = req.getParameter("name");
    	String parameter = req.getParameter("id");
    	int id = Integer.parseInt(parameter);
    	Banji banji = new Banji(id,banjiName);
		int num = bDao.updateBanji(banji);
		System.out.println("影响行数："+num);
		resp.sendRedirect(req.getContextPath()+"/banji?method=findAllBanji");
	}
	//根据班级查找学生
    private void findStudentByBanji(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	String strId = req.getParameter("id");
    	int banjiId = Integer.parseInt(strId);
    	List<Student> studentList = ser.findStudentByBanji(banjiId);
    	System.out.println("kankankan::"+studentList);
    	req.setAttribute("studentList", studentList);
    	req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/jsp/findBanjiById.jsp").forward(req, resp);
    }
    
	
	
	
	
	
	
	
	
	
	
	
	
}




