package com.gec.hrm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.hrm.bean.user_inf;


@WebFilter("/*")
public class LoginFilter implements Filter {
	String[] urls={"loginForm.action","login.action"};

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//获取到请求中的路径
		String path = req.getRequestURI();
		boolean flag = false;
		for (String url : urls) {
			if(path.indexOf(url)!=-1){
				flag = true;
				break;
			}
		}
		if(flag) {
			chain.doFilter(req, resp);
		}else {
			HttpSession session = req.getSession();
			user_inf user = (user_inf) session.getAttribute("user_session");
			if(user!=null){
				chain.doFilter(req, resp);
			}else{
				request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
			}
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
