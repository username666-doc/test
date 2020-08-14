package com.gec.hrm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.hrm.Service.TypeService;
import com.gec.hrm.Service.noticeService;
import com.gec.hrm.Service.impl.TypeServiceImpl;
import com.gec.hrm.Service.impl.noticeServiceImpl;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.notice_inf;
import com.gec.hrm.bean.type_inf;
import com.gec.hrm.bean.user_inf;

@WebServlet({
	"/notice/selectNotice","/notice/addNotice","/noticesaveOrUpdate.action","/viewNotice.action","/noticelist.action","/noticedel.action"
	,"/notice/addType","/typesaveOrUpdate.action"
})
public class noticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		noticeService ns = new noticeServiceImpl();
		TypeService ts = new TypeServiceImpl();
		notice_inf not=null;
		int pageNow=1;
		//获取到请求路径
		String uri = req.getRequestURI();
		//截取请求名
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("selectNotice".equals(action)) {
			if(req.getParameter("pageIndex")!=null) {
				pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			PageBean<notice_inf> pb=ns.findNoticeAll(pageNow);
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/notice/noticelist.jsp").forward(req, resp);
		}else if("addNotice".equals(action)) {
			List<type_inf> types=ts.findtypeAll();
			req.setAttribute("types", types);
			req.getRequestDispatcher("/WEB-INF/jsp/notice/notice_save_update.jsp").forward(req, resp);
		}else if("noticesaveOrUpdate.action".equals(action)) {
			if("".equals(req.getParameter("id"))) {
				HttpSession session=req.getSession();
				user_inf user=(user_inf)session.getAttribute("user_session");
				String name=req.getParameter("name");
				int type_id=Integer.parseInt(req.getParameter("type_id"));
				String text=req.getParameter("text");
				not=new notice_inf();
				not.setName(name);
				not.setType(ts.findtype(type_id));
				not.setContent(text);
				not.setUser_id(user.getId());
				ns.addNotice(not);
				req.getRequestDispatcher("/notice/selectNotice").forward(req, resp);
			}else {
				int id=Integer.parseInt(req.getParameter("id"));
				String name=req.getParameter("name");
				int type_id=Integer.parseInt(req.getParameter("type_id"));
				String text=req.getParameter("text");
				not=new notice_inf();
				not.setName(name);
				not.setType(ts.findtype(type_id));
				not.setContent(text);
				not.setId(id);
				ns.alterNotice(not);
				req.getRequestDispatcher("/notice/selectNotice").forward(req, resp);
			}
					
		}else if("noticelist.action".equals(action)) {
			String name=req.getParameter("name");
			if(req.getParameter("pageIndex")!=null) {
				pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			PageBean<notice_inf> pb=ns.queryNotice(pageNow, name);
			req.setAttribute("PageBean",pb);
			req.getRequestDispatcher("/WEB-INF/jsp/notice/noticelist.jsp").forward(req, resp);
		}else if("noticedel.action".equals(action)) {
			String[] ids =req.getParameterValues("noticeIds");
			ns.deleteNoticer(ids);
			req.getRequestDispatcher("/notice/selectNotice").forward(req, resp);
		}else if("viewNotice.action".equals(action)) {
			int id=Integer.parseInt(req.getParameter("id"));
			 not=ns.findNotice(id);
			 List<type_inf> types=ts.findtypeAll();
			req.setAttribute("types", types);
			req.setAttribute("notice", not);
			req.getRequestDispatcher("/WEB-INF/jsp/notice/notice_save_update.jsp").forward(req, resp);
		}else if("addType".equals(action)) {
			req.getRequestDispatcher("/WEB-INF/jsp/notice/type_save_update.jsp").forward(req, resp);
		}else if("typesaveOrUpdate.action".equals(action)) {
			if("".equals(req.getParameter("id"))) {
				HttpSession session=req.getSession();
				user_inf user=(user_inf)session.getAttribute("user_session");
				String name=req.getParameter("name");
				type_inf type =new type_inf();
				type.setName(name);
				type.setUser_id(user.getId());
				ts.addType(type);
				req.getRequestDispatcher("/notice/selectNotice").forward(req, resp);
			}else {
				
			}
		}
	}
}
