package com.gec.hrm.Dao;


import java.util.List;

import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.dept_inf;

public interface deptDao  {
	public boolean alterdept(dept_inf dept);
	public boolean adddept(dept_inf dept);
	public boolean deletedept(String[] ids);
	public PageBean<dept_inf> findAlldept(int pageNow);
	public PageBean<dept_inf> querydept(int pageNow,String deptname);
	public dept_inf finddept(int id);
	public List<dept_inf> findAlldept();
}
