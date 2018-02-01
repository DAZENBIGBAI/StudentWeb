package Listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.situ.student.entity.User;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("监听器");
		List<User> onlineList = new ArrayList<User>();
		 //放到ServletContext域对象中。
		sce.getServletContext().setAttribute("onlineList", onlineList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
