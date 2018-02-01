package com.situ.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import com.situ.student.entity.User;
import com.situ.student.service.IUserService;
import com.situ.student.service.impl.UserServiceImpl;

public class loginServlet extends baseServlet{
	private IUserService userService = new UserServiceImpl();
	
	//得到登陆页面
	public void getLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}
	//登陆	
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	req.setCharacterEncoding("utf-8");
    	String checkCode = req.getParameter("checkCode");
    	String checkCodeSession = (String) req.getSession().getAttribute("checkCodeSession");
    	System.out.println("验证码："+checkCode+"和"+checkCodeSession);
    	if(!checkCode.equals(checkCodeSession)){
    		resp.sendRedirect(req.getContextPath()+"/login?method=getLogin");
    		return;
    	}
		String username =  req.getParameter("user");
		String  password = req.getParameter("password");
		User user = userService.findUser(username, password);
		
		if(user != null){
			System.out.println("登陆进来");
			HttpSession session = req.getSession();
			//用户信息加到session中
			session.setAttribute("user", user);
			//从servletContext中取出空的集合
			List<User> list= (List<User>)getServletContext().getAttribute("onlineList");
			//用户信息加到context中
			list.add(user);
			//统计当前在线人数
			int onlineCount = list.size();
			req.getServletContext().setAttribute("onlineCount", onlineCount);
			
			
			//跳转
			resp.sendRedirect(req.getContextPath()+"/student?method=pageStudent");
			
		}else{
			resp.sendRedirect(req.getContextPath()+"/login?method=getLogin");
		}
	}
//退出
    public void logout(HttpServletRequest req, HttpServletResponse resp){
    	User us = (User) req.getSession().getAttribute("user");
    	req.getSession().removeAttribute("user");
    	List<User> list= (List<User>)req.getServletContext().getAttribute("onlineList");
    	list.remove(us);
    	try {
			resp.sendRedirect(req.getContextPath()+"/login?method=getLogin");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
}
