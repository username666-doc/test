package com.gec.hrm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.hrm.Service.deptService;
import com.gec.hrm.Service.impl.deptServiceImpl;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.dept_inf;
@WebServlet({"/dept/selectDept" ,"/deptlist.action" ,"/viewDept.action","/saveOrUpdate.action","/querydept.action" ,"/deptdel.action","/addDept" ,"/save.action"})
public class deptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		deptService ds = new deptServiceImpl();
		dept_inf dept=null;
		int pageNow=1;
		//获取到请求路径
		String uri = req.getRequestURI();
		//截取请求名
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("deptlist.action".equals(action)) {
			if(req.getParameter("pageIndex")!=null) {
				 pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			PageBean<dept_inf> pb=ds.findAlldept(pageNow);
			
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/dept/deptlist.jsp").forward(req, resp);
		}else if("viewDept.action".equals(action)) {
			int id=Integer.parseInt(req.getParameter("id"));
			dept=ds.finddept(id);
			req.setAttribute("dept",dept);
			req.getRequestDispatcher("/WEB-INF/jsp/dept/deptedit.jsp").forward(req, resp);
		}else if("saveOrUpdate.action".equals(action)) {
			String name=req.getParameter("name");
			String remark=req.getParameter("remark");
			int id=Integer.parseInt(req.getParameter("id"));
			dept=new dept_inf();
			dept.setId(id);
			dept.setName(name);
			dept.setRemark(remark);
			ds.alterdept(dept);
			req.getRequestDispatcher("/deptlist.action").forward(req, resp);
		}else if("querydept.action".equals(action)) {
			if(req.getParameter("pageIndex")!=null) {
				pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			
			String deptname=req.getParameter("name");
			PageBean<dept_inf> pb =ds.querydept(pageNow, deptname);
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/dept/deptlist.jsp").forward(req, resp);
		}else if("deptdel.action".equals(action)) {
			String[] ids =req.getParameterValues("deptIds");
			ds.deletedept(ids);
			req.getRequestDispatcher("/deptlist.action").forward(req, resp);
			}else if("addDept".equals(action)) {
				req.getRequestDispatcher("/WEB-INF/jsp/dept/deptadd.jsp").forward(req, resp);
			}else if("save.action".equals(action)) {
				String name=req.getParameter("name");
				String remark=req.getParameter("remark");
				dept=new dept_inf();
				dept.setName(name);
				dept.setRemark(remark);
				ds.adddept(dept);
				req.getRequestDispatcher("/deptlist.action").forward(req, resp);
			}
	}
}
