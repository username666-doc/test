package com.gec.hrm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.hrm.Service.jobService;
import com.gec.hrm.Service.impl.jobServiceImpl;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.job_inf;
@WebServlet({"/selectJob" ,"/addJob" ,"/jobaddsave.action" ,"/jobedit.action" ,"/joblist.action","/jobdel.action","/viewJob.action" })
public class jobServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 jobService js = new jobServiceImpl();
		job_inf job=null;
		int pageNow=1;
		//获取到请求路径
		String uri = req.getRequestURI();
		//截取请求名
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("selectJob".equals(action)) {
			if(req.getParameter("pageIndex")!=null) {
				pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			PageBean<job_inf> pb =js.findJobAll(pageNow);
			pb.setPageCount();
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/job/joblist.jsp").forward(req, resp);
		}else if("addJob".equals(action)) {
			req.getRequestDispatcher("/WEB-INF/jsp/job/jobadd.jsp").forward(req, resp);
		}else if("jobaddsave.action".equals(action)) {
			String remark=req.getParameter("remark");
			String name=req.getParameter("name");
			job=new job_inf();
			job.setRemark(remark);;
			job.setName(name);;
			js.addJob(job);
			req.getRequestDispatcher("/selectJob").forward(req, resp);
		}else if("joblist.action".equals(action)) {
			if(req.getParameter("pageIndex")!=null) {
				pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			String name=req.getParameter("name");
			PageBean<job_inf> pb =js.queryjob(pageNow, name);
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/job/joblist.jsp").forward(req, resp);
		}else if("jobdel.action".equals(action)) {
			String[] ids =req.getParameterValues("jobIds");
			js.deletejob(ids);
			req.getRequestDispatcher("/selectJob").forward(req, resp);
		}else if("viewJob.action".equals(action)) {
			int id=Integer.parseInt(req.getParameter("id"));
			job=js.findjob(id);
			req.setAttribute("job",job);
			req.getRequestDispatcher("/WEB-INF/jsp/job/jobedit.jsp").forward(req, resp);
		}else if("jobedit.action".equals(action)) {
			job=new job_inf();
			int id=Integer.parseInt(req.getParameter("id"));
			String name=req.getParameter("name");
			String remark=req.getParameter("remark");
			job.setId(id);
			job.setName(name);
			job.setRemark(remark);
			js.alterjob(job);
			req.getRequestDispatcher("/selectJob").forward(req, resp);
		}
	}
}