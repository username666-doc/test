package com.gec.hrm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.hrm.Service.userService;
import com.gec.hrm.Service.impl.userServiceImpl;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.user_inf;

@WebServlet({"/userlist.action" ,"/useradd.action" ,"/useraddsave.action" ,"/queryUser.action" ,"/userdel.action","/viewUser.action" ,"/useredit.action" })
public class userServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session= req.getSession();
		user_inf loginUser=(user_inf)session.getAttribute("user_session");
		userService us = new userServiceImpl();
		user_inf user=null;
		int pageNow=1;
		//获取到请求路径
		String uri = req.getRequestURI();
		//截取请求名
		String action = uri.substring(uri.lastIndexOf("/")+1);
	if(loginUser.getStatus()==2) {
		if("userlist.action".equals(action)) {
			String loginname=req.getParameter("loginname");
			String username=req.getParameter("username");
			String status=req.getParameter("status");
			if(loginname==null&&username==null&&status==null) {
				if(req.getParameter("pageIndex")!=null) {
					pageNow=Integer.parseInt(req.getParameter("pageIndex"));
				}
				PageBean<user_inf> pb =us.findUserAll(pageNow);
				req.setAttribute("PageBean", pb);
				req.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(req, resp);
			}else {
				req.getRequestDispatcher("/queryUser.action").forward(req, resp);
			}
		}else if("useradd.action".equals(action)) {
			req.getRequestDispatcher("/WEB-INF/jsp/user/useradd.jsp").forward(req, resp);
		}else if("useraddsave.action".equals(action)) {
			String username=req.getParameter("username");
			int status=Integer.parseInt(req.getParameter("status"));
			String loginname=req.getParameter("loginname");
			String password=req.getParameter("password");
			user=new user_inf();
			user.setLoginname(loginname);
			user.setPassword(password);
			user.setStatus(status);
			user.setUsername(username);
			us.addUser(user);
			PageBean<user_inf> pb =us.findUserAll(pageNow);
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(req, resp);
		}else if("queryUser.action".equals(action)) {
			if(req.getParameter("pageIndex")!=null) {
				pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			String loginname=req.getParameter("loginname");
			String username=req.getParameter("username");
			int status=0;
			if(!"".equals(req.getParameter("status"))) {
				status=Integer.parseInt(req.getParameter("status"));
			}
			PageBean<user_inf> pb =us.queryUser(pageNow,loginname, username, status);
			if(pb.getList().size()==0) {
				req.setAttribute("flag",0);
			}else {
				req.setAttribute("flag",1);
			}
			user=new user_inf();
			user.setLoginname(loginname);
			user.setUsername(username);
			user.setStatus(status);
			req.setAttribute("user", user);
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(req, resp);
		}else if("userdel.action".equals(action)) {
			String[] ids =req.getParameterValues("userIds");
			us.deleteUser(ids);
			req.getRequestDispatcher("/userlist.action").forward(req, resp);
		}else if("viewUser.action".equals(action)) {
			int id=Integer.parseInt(req.getParameter("id"));
			user=us.findUser(id);
			req.setAttribute("user",user);
			req.getRequestDispatcher("/WEB-INF/jsp/user/useredit.jsp").forward(req, resp);
		}else if("useredit.action".equals(action)) {
			user=new user_inf();
			int id=Integer.parseInt(req.getParameter("id"));
			String username=req.getParameter("username");
			int status=Integer.parseInt(req.getParameter("status"));
			String password=req.getParameter("password");
			String loginname=req.getParameter("loginname");
			user.setId(id);
			user.setLoginname(loginname);
			user.setPassword(password);
			user.setStatus(status);
			user.setUsername(username);
			us.alterUser(user);
			PageBean<user_inf> pb =us.findUserAll(pageNow);
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(req, resp);
			}
		}else {
			if("userlist.action".equals(action)) {
				if(req.getParameter("pageIndex")!=null) {
					pageNow=Integer.parseInt(req.getParameter("pageIndex"));
				}
				PageBean<user_inf> pb =us.findUserAll(pageNow);
				req.setAttribute("PageBean", pb);
				req.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(req, resp);
			}else {
				resp.getWriter().write("您没有权限！！！");
			}
		}
	}
}
