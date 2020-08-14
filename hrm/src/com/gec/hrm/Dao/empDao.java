package com.gec.hrm.Dao;

import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.employee_inf;

public interface empDao {
	public PageBean<employee_inf> findempAll(int pageNow);
	public boolean addemp(employee_inf emp);
	public PageBean<employee_inf> queryemp(int pageNow,String name,String cardId,int job_id,int sex,String phone,int dept_id);
	public boolean  deleteemp(String[] ids);
	public employee_inf findemp(int id);
	public boolean alteremp(employee_inf emp);
	public boolean findcardId(String cardId);
}
