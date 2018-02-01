package web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.situ.student.entity.Course;
import com.situ.student.service.IManagerServce;
import com.situ.student.service.impl.ManagerServceImpl;
//findBC
public class findAllCourseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IManagerServce ser = new ManagerServceImpl();
		String strId = req.getParameter("banji_id");
		int id = Integer.parseInt(strId);
		List<Course> list = ser.findNotC(id);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write(json);
	}
}


