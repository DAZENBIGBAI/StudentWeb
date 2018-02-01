package web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.situ.student.util.JDBCUtil;

public class initServlet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext ser = getServletContext(); 
//		JDBCUtil.init(ser);
	}
	
}
