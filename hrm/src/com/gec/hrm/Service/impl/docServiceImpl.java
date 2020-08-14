package com.gec.hrm.Service.impl;

import com.gec.hrm.Dao.docDao;
import com.gec.hrm.Dao.impl.docDaoImpl;
import com.gec.hrm.Service.docService;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.document_inf;

public class docServiceImpl implements docService{
	docDao dd= new docDaoImpl();
	@Override
	public PageBean<document_inf> finddocAll(int pageNow) {
		// TODO Auto-generated method stub
		return dd.finddocAll(pageNow);
	}

	@Override
	public boolean addDoc(document_inf doc) {
		// TODO Auto-generated method stub
		return dd.addDoc(doc);
	}

	@Override
	public PageBean<document_inf> querydoc(int pageNow, String title) {
		// TODO Auto-generated method stub
		return dd.querydoc(pageNow, title);
	}

	@Override
	public boolean deletedoc(String[] ids) {
		// TODO Auto-generated method stub
		return dd.deletedoc(ids);
	}

	@Override
	public document_inf finddoc(int id) {
		// TODO Auto-generated method stub
		return dd.finddoc(id);
	}

	@Override
	public boolean alterdoc(document_inf user) {
		// TODO Auto-generated method stub
		return dd.alterdoc(user);
	}

}
