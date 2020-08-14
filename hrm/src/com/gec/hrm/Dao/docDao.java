package com.gec.hrm.Dao;

import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.document_inf;

public interface docDao {
	public PageBean<document_inf> finddocAll(int pageNow);
	public boolean addDoc(document_inf doc);
	public PageBean<document_inf> querydoc(int pageNow,String title);
	public boolean  deletedoc(String[] ids);
	public document_inf finddoc(int id);
	public boolean alterdoc(document_inf user);
}
