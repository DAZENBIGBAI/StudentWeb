package web;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoImpl;
import com.situ.student.entity.Student;

public class addServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IStudentDao dao = new StudentDaoImpl();
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		Student student = new Student(name,Integer.parseInt(age),gender,address);
		System.out.println(student);
		dao.add(student);
		resp.setContentType("text/html;Charset=utf-8");
		resp.sendRedirect("/StudentWeb/jsp/findStudent.jsp");
	}
	
}


