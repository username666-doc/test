package com.gec.hrm.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gec.hrm.Service.docService;
import com.gec.hrm.Service.impl.docServiceImpl;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.document_inf;
import com.gec.hrm.bean.user_inf;
@WebServlet({
	"/documentlist.action","/documentadd.action","/documentaddsave.action","/document/removeDocument","/document/updateDocument"
	,"/documentdownload.action"
})
public class DocServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		docService ds = new docServiceImpl();
		document_inf doc=null;
		int pageNow=1;
		//获取到请求路径
		String uri = req.getRequestURI();
		//截取请求名
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("documentlist.action".equals(action)) {//文档查询
			if(req.getParameter("title")==null) {
				if(req.getParameter("pageIndex")!=null) {
					pageNow=Integer.parseInt(req.getParameter("pageIndex"));
				}
				PageBean<document_inf> pb =ds.finddocAll(pageNow);
				req.setAttribute("PageBean", pb);
				req.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(req, resp);
			}else {
				String title=req.getParameter("title");
				if(req.getParameter("pageIndex")!=null) {
					pageNow=Integer.parseInt(req.getParameter("pageIndex"));
				}
				PageBean<document_inf> pb=ds.querydoc(pageNow, title);
				req.setAttribute("PageBean", pb);
				req.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(req, resp);
			}
		}else if("documentadd.action".equals(action)) {//跳转到上传文档
			req.getRequestDispatcher("/WEB-INF/jsp/document/documentadd.jsp").forward(req, resp);
		}else if("documentaddsave.action".equals(action)) {
			String fileName = null;//文件名
			//判断上传的是否为二进制提交方式
			boolean isMultipart=ServletFileUpload.isMultipartContent(req);
			doc=new document_inf();
			boolean flag=false;
			if(isMultipart) {
				//创建FileItemFactory工厂对象
				FileItemFactory factory = new DiskFileItemFactory();
				//将工作对象放进ServletFileUpload中，用于保存数据
				ServletFileUpload upload= new ServletFileUpload(factory);
				try {
					//通过工厂解析request中所有的数据,存储到FileItem集合中
					List<FileItem> list = upload.parseRequest(req);
					if(list!=null) {
					for (FileItem fi : list) {
						if(fi.isFormField()) {
							System.out.println(fi.toString());
							
							if("title".equals(fi.getFieldName())) {
								doc.setTitle(fi.getString("utf-8"));//标题
								System.out.println(doc.getTitle());
							}
							if("remark".equals(fi.getFieldName())) {
								doc.setRemark(fi.getString("utf-8"));//描述
								System.out.println(doc.getRemark());
							}
						}else {
							String path="G:\\uploading";
							File file = new File(path);
							if(!file.exists()){//判断文件存不存在，如果不存在就创建
								file.mkdirs();
							}
							 fileName = fi.getName();
							 doc.setFilename(fileName);//文件名字
							 doc.setFiletype(fileName.split("\\.")[1]);//文件类型
							 HttpSession session =req.getSession();
							 doc.setUser((user_inf)session.getAttribute("user_session"));//用户id
//							 File newFile = new File(file, fileName);
							 doc.setFilebytes(fi.get());
//							 fi.write(newFile);
						}
					}
					flag = true;
				}else {
						HttpSession session = req.getSession();
						session.setAttribute("message", "用户传输数据失败");
						req.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(req, resp);
					}
					if(flag) {
						ds.addDoc(doc);
//						HttpSession session = req.getSession();
//						session.setAttribute("fileName", fileName);
						resp.sendRedirect("documentlist.action");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if("documentdownload.action".equals(action)) {
			int id =Integer.parseInt(req.getParameter("id")) ;
			doc=ds.finddoc(id);
			String fileName=doc.getFilename();
			byte[] file= doc.getFilebytes();
//			resp.setHeader("Content-Disposition", "attachment;filename="+fileName);
			resp.setHeader("Content-Disposition","attachment;filename="+java.net.URLEncoder.encode(fileName,"UTF-8"));
			ServletOutputStream out = resp.getOutputStream();
				out.write(file, 0, file.length);
			//关闭流
			out.flush();
			out.close();
		}else if("removeDocument".equals(action)) {
			String[] ids =req.getParameterValues("ids");
			ds.deletedoc(ids);
			req.getRequestDispatcher("/documentlist.action").forward(req, resp);
		}else if("updateDocument".equals(action)) {
			if("1".equals(req.getParameter("flag"))) {
				int id=Integer.parseInt(req.getParameter("id"));
				doc=ds.finddoc(id);
				req.setAttribute("document",doc);
				req.getRequestDispatcher("/WEB-INF/jsp/document/showUpdateDocument.jsp").forward(req, resp);
			}else {
				String fileName = null;//文件名
				//判断上传的是否为二进制提交方式
				boolean isMultipart=ServletFileUpload.isMultipartContent(req);
				doc=new document_inf();
				boolean flag=false;
				if(isMultipart) {
					//创建FileItemFactory工厂对象
					FileItemFactory factory = new DiskFileItemFactory();
					//将工作对象放进ServletFileUpload中，用于保存数据
					ServletFileUpload upload= new ServletFileUpload(factory);
					try {
						//通过工厂解析request中所有的数据,存储到FileItem集合中
						List<FileItem> list = upload.parseRequest(req);
						if(list!=null) {
						for (FileItem fi : list) {
							if(fi.isFormField()) {
								System.out.println(fi.toString());
								
								if("title".equals(fi.getFieldName())) {
									doc.setTitle(fi.getString("utf-8"));//标题
								}
								if("remark".equals(fi.getFieldName())) {
									doc.setRemark(fi.getString("utf-8"));//描述
								}
								if("id".equals(fi.getFieldName())) {
									doc.setId(Integer.parseInt(fi.getString("utf-8")));//id
								}
							}else {
								 fileName = fi.getName();
								 doc.setFilename(fileName);//文件名字
								 doc.setFiletype(fileName.split("\\.")[1]);//文件类型
								 HttpSession session =req.getSession();
								 doc.setUser((user_inf)session.getAttribute("user_session"));//用户id
								 doc.setFilebytes(fi.get());//文件数据
							}
						}
						flag = true;
					}else {
							HttpSession session = req.getSession();
							session.setAttribute("message", "用户传输数据失败");
							req.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(req, resp);
						}
						if(flag) {
							ds.alterdoc(doc);
							req.getRequestDispatcher("/documentlist.action").forward(req, resp);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}
}
