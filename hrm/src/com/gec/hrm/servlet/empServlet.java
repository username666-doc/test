package com.gec.hrm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gec.hrm.Dao.deptDao;
import com.gec.hrm.Dao.jobDao;
import com.gec.hrm.Dao.impl.deptDaoImpl;
import com.gec.hrm.Dao.impl.jobDaoImpl;
import com.gec.hrm.Service.empService;
import com.gec.hrm.Service.impl.empServiceImpl;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.dept_inf;
import com.gec.hrm.bean.employee_inf;
import com.gec.hrm.bean.job_inf;
@WebServlet({"/employeelist.action","/employeeadd.action","/employeedel.action","/updateEmployee","/getcardid.action",
"/employee/addEmployee","/employee/updateEmployee","/queryemp.action"})
public class empServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jobDao jd=new jobDaoImpl();
		empService es = new empServiceImpl();
		deptDao db=new deptDaoImpl();
		employee_inf emp=null;
		int pageNow=1;
		//获取到请求路径
		String uri = req.getRequestURI();
		//截取请求名
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("employeelist.action".equals(action)) {
			if(req.getParameter("pageIndex")!=null) {
				pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			PageBean<employee_inf> pb =es.findempAll(pageNow);
			List<dept_inf> list=db.findAlldept();
			List<job_inf> list1=jd.findJobAll();
			req.setAttribute("deptList", list);
			req.setAttribute("jobList", list1);
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/employee/employeelist.jsp").forward(req, resp);
		}else if("employeeadd.action".equals(action)) {
			List<job_inf> list1=jd.findJobAll();
			List<dept_inf> list=db.findAlldept();
			req.setAttribute("jobList",list1);
			req.setAttribute("deptList",list);
			req.getRequestDispatcher("/WEB-INF/jsp/employee/employeeadd.jsp").forward(req, resp);
		}else if("addEmployee".equals(action)) {
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			deptDao dept=new deptDaoImpl();
			jobDao job=new jobDaoImpl();
			String name=req.getParameter("name");
			String cardId=req.getParameter("cardId");
			String address=req.getParameter("address");
			String postCode=req.getParameter("postCode");
			String tel=req.getParameter("tel");
			String phone=req.getParameter("phone");
			String qqNum=req.getParameter("qqNum");
			String email=req.getParameter("email");
			int sex=Integer.parseInt(req.getParameter("sex"));
			String party=req.getParameter("party");
			Date birthday = null;
			try {
				birthday = sd.parse(req.getParameter("birthday"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String race=req.getParameter("race");
			String education=req.getParameter("education");
			String speciality=req.getParameter("speciality");
			String hobby=req.getParameter("hobby");
			String remark=req.getParameter("remark");
			int dept_id=Integer.parseInt(req.getParameter("dept_id"));
			int job_id=Integer.parseInt(req.getParameter("job_id"));
			emp=new employee_inf();
			emp.setName(name);
			emp.setCardId(cardId);
			emp.setAddress(address);
			emp.setPost_code(postCode);
			emp.setTel(tel);
			emp.setPhone(phone);
			emp.setQq_num(qqNum);
			emp.setEmail(email);
			emp.setSex(sex);
			emp.setParty(party);
			emp.setBirthday(birthday);
			emp.setRace(race);
			emp.setEducation(education);
			emp.setSpeciality(speciality);
			emp.setHobby(hobby);
			emp.setRemark(remark);
			emp.setDept(dept.finddept(dept_id));
			emp.setJob(job.findjob(job_id));
			es.addemp(emp);
			req.getRequestDispatcher("/employeelist.action").forward(req, resp);
		}else if("queryemp.action".equals(action)) {
			if(req.getParameter("pageIndex")!=null) {
				pageNow=Integer.parseInt(req.getParameter("pageIndex"));
			}
			String name=req.getParameter("name");
			String cardId=req.getParameter("cardId");
			int job_id=0;
			if(!req.getParameter("job_id").equals("")) {
				job_id=Integer.parseInt(req.getParameter("job_id"));
			}
			int sex=0;
			if(!req.getParameter("sex").equals("")) {
				sex=Integer.parseInt(req.getParameter("sex"));
			}
			String phone=req.getParameter("phone");
			int dept_id=0;
			if(!req.getParameter("dept_id").equals("")) {
				dept_id=Integer.parseInt(req.getParameter("dept_id"));
			}
			PageBean<employee_inf> pb =es.queryemp(pageNow,name,cardId,job_id,sex,phone,dept_id);
			List<dept_inf> list=db.findAlldept();
			List<job_inf> list1=jd.findJobAll();
			req.setAttribute("deptList", list);
			req.setAttribute("jobList", list1);
			req.setAttribute("PageBean", pb);
			req.getRequestDispatcher("/WEB-INF/jsp/employee/employeelist.jsp").forward(req, resp);
		}else if("employeedel.action".equals(action)) {
			String[] ids =req.getParameterValues("empIds");
			es.deleteemp(ids);
			req.getRequestDispatcher("/employeelist.action").forward(req, resp);
		}else if("updateEmployee".equals(action)) {
			if(req.getParameter("flag").equals("1")) {
				List<job_inf> list1=jd.findJobAll();
				List<dept_inf> list=db.findAlldept();
				int id=Integer.parseInt(req.getParameter("id"));
				emp=es.findemp(id);
				req.setAttribute("jobs",list1);
				req.setAttribute("depts",list);
				req.setAttribute("employee",emp);
				req.getRequestDispatcher("/WEB-INF/jsp/employee/employeeedit.jsp").forward(req, resp);	
			}else {
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				deptDao dept=new deptDaoImpl();
				jobDao job=new jobDaoImpl();
				int id=Integer.parseInt(req.getParameter("id"));
				String name=req.getParameter("name");
				String cardId=req.getParameter("cardId");
				String address=req.getParameter("address");
				String postCode=req.getParameter("postCode");
				String tel=req.getParameter("tel");
				String phone=req.getParameter("phone");
				String qqNum=req.getParameter("qqNum");
				String email=req.getParameter("email");
				int sex=Integer.parseInt(req.getParameter("sex"));
				String party=req.getParameter("party");
				Date birthday = null;
				try {
					birthday = sd.parse(req.getParameter("birthday"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String race=req.getParameter("race");
				String education=req.getParameter("education");
				String speciality=req.getParameter("speciality");
				String hobby=req.getParameter("hobby");
				String remark=req.getParameter("remark");
				int dept_id=Integer.parseInt(req.getParameter("dept_id"));
				int job_id=Integer.parseInt(req.getParameter("job_id"));
				emp=new employee_inf();
				emp.setName(name);
				emp.setCardId(cardId);
				emp.setAddress(address);
				emp.setPost_code(postCode);
				emp.setTel(tel);
				emp.setPhone(phone);
				emp.setQq_num(qqNum);
				emp.setEmail(email);
				emp.setSex(sex);
				emp.setParty(party);
				emp.setBirthday(birthday);
				emp.setRace(race);
				emp.setEducation(education);
				emp.setSpeciality(speciality);
				emp.setHobby(hobby);
				emp.setRemark(remark);
				emp.setJob(job.findjob(job_id));
				emp.setDept(dept.finddept(dept_id));
				emp.setId(id);
				es.alteremp(emp);
				req.getRequestDispatcher("/employeelist.action").forward(req, resp);
			}
		}else if("getcardid.action".equals(action)) {
			String cardId=req.getParameter("cardId");
			System.out.println("cardId:"+cardId);
			Map<String,String[]> map = req.getParameterMap();
			System.out.println("map:"+map);
			map.forEach((k,v)->System.out.println(k+" = "+Arrays.toString(v)));
			ObjectMapper mapper= new ObjectMapper();
			PrintWriter out = resp.getWriter();
			if(es.findcardId(cardId)) {
				String json=mapper.writeValueAsString("1");
				out.write(json);
				out.flush();
				out.close();
			}
		}
	}
}