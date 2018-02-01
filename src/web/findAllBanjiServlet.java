package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.situ.student.dao.IBanjiDao;
import com.situ.student.dao.impl.BanjiDaoImpl;
import com.situ.student.entity.Banji;

public class findAllBanjiServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IBanjiDao dao = new BanjiDaoImpl();
		List<Banji> findAllBanji = dao.findAllBanji();
		Gson gson = new Gson();
		String json = gson.toJson(findAllBanji);
		System.out.println("所有班级"+json);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write(json);
	}
}
