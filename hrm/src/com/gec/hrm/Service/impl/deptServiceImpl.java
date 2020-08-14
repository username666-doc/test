package com.gec.hrm.Service.impl;

import java.util.List;

import com.gec.hrm.Dao.deptDao;
import com.gec.hrm.Dao.impl.deptDaoImpl;
import com.gec.hrm.Service.deptService;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.dept_inf;

public class deptServiceImpl implements deptService {
	deptDao db=new deptDaoImpl();
	@Override
	public boolean alterdept(dept_inf dept) {
		// TODO Auto-generated method stub
		return db.alterdept(dept);
	}

	@Override
	public boolean adddept(dept_inf dept) {
		// TODO Auto-generated method stub
		return db.adddept(dept);
	}

	@Override
	public boolean deletedept(String[] ids) {
		// TODO Auto-generated method stub
		return db.deletedept(ids);
	}

	@Override
	public PageBean<dept_inf> findAlldept(int pageNow) {
		// TODO Auto-generated method stub
		return db.findAlldept(pageNow);
	}

	@Override
	public PageBean<dept_inf> querydept(int pageNow, String deptname) {
		// TODO Auto-generated method stub
		return db.querydept(pageNow, deptname);
	}

	@Override
	public dept_inf finddept(int id) {
		// TODO Auto-generated method stub
		return db.finddept(id);
	}

	@Override
	public List<dept_inf> findAlldept() {
		// TODO Auto-generated method stub
		return db.findAlldept();
	}
}
