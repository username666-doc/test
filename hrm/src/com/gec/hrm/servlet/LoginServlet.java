package com.gec.hrm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.hrm.Service.loginService;
import com.gec.hrm.Service.impl.loginServiceImpl;
import com.gec.hrm.bean.user_inf;

@WebServlet({ "/loginForm.action", "/login.action"  ,"/logout.action"  ,"/home.action"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loginService log = new loginServiceImpl();
		user_inf user=null;
		//获取到请求路径
		String uri = request.getRequestURI();
		//截取请求名
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if(action.equals("login.action")){
			//获取用户的操作
			String name = request.getParameter("loginname");
			String pwd = request.getParameter("password");
			user=log.login(name, pwd);
			if(user!=null){
				HttpSession session = request.getSession();
				session.setAttribute("user_session",user );
				//跳转到主界面
				request.getRequestDispatcher("/main.action").forward(request, response);
			}else{
				request.setAttribute("message", "用户名或密码错误");
				request.getRequestDispatcher("/loginForm.action").forward(request, response);
			}
		}else{
			//请求登录界面
			request.getSession().removeAttribute("user_session");
//			request.getSession().invalidate();
			request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
		}
	}	

}
