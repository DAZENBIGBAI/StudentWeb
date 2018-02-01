package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.entity.User;

/**
 * Servlet Filter implementation class LoginFilter
 */

public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//有请求不需要过滤，排除它们
		String [] paths = new String[]{
				"/login","/getLogin","/checkImage",
				"/lib/style.css","/lib/modernizr-1.7.min.js",
				"/img/user.png","/img/pass.png"
				
		};
		//当前路径
		String servletPath = req.getServletPath();
		System.out.println("当前路径："+servletPath+"继续执行");
		for(String p:paths){
		
			if(p.equals(servletPath)){
				//不需过滤，请求继续执行
				chain.doFilter(req, resp);
				return;
			}
		}
		//从session中尝试获取账号
		User user = (User) req.getSession().getAttribute("user");
		System.out.println("账号："+user);
		//根据账号判断用户是否登陆
		if(user == null){
			//未登录重定向到登录页
			resp.sendRedirect(req.getContextPath()+"/login?method=getLogin");
		}else{
			//已登录，请求继续执行,参数声明的是父类调用的是子类
			req.setCharacterEncoding("utf-8");
			chain.doFilter(request, response);
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
