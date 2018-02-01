package com.situ.student.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class baseServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 1.得到get请求中method对应的值即要调用的方法
		String methodName = req.getParameter("method");
		System.out.println("方法名字："+methodName);
		// 2.获得当前被访问对象的字节码对象
		// this.getClass() ：StudentMainServlet.class  BanjiMainServlet.class
		Class clazz = this.getClass();
		// 3.获取当前字节码对象中指定的方法
		//clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		try {
			Method method = clazz.getDeclaredMethod(methodName, new Class[]{HttpServletRequest.class,HttpServletResponse.class});
			method.setAccessible(true);
			try {
				// 4.调用要执行的方法
				//method.invoke(this, req, resp);
				method.invoke(this, new Object[]{req,resp});
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}
}







