package com.gec.hrm.Service.impl;

import com.gec.hrm.Dao.empDao;
import com.gec.hrm.Dao.impl.empDaoImpl;
import com.gec.hrm.Service.empService;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.employee_inf;

public class empServiceImpl implements empService {
	empDao ed= new empDaoImpl();
	@Override
	public PageBean<employee_inf> findempAll(int pageNow) {
		// TODO Auto-generated method stub
		return ed.findempAll(pageNow);
	}

	@Override
	public boolean addemp(employee_inf emp) {
		// TODO Auto-generated method stub
		return ed.addemp(emp);
	}

	@Override
	public PageBean<employee_inf> queryemp(int pageNow,String name,String cardId,int job_id,int sex,String phone,int dept_id) {
		// TODO Auto-generated method stub
		return ed.queryemp(pageNow,name,cardId,job_id,sex,phone,dept_id);
	}

	@Override
	public boolean deleteemp(String[] ids) {
		// TODO Auto-generated method stub
		return ed.deleteemp(ids);
	}

	@Override
	public employee_inf findemp(int id) {
		// TODO Auto-generated method stub
		return ed.findemp(id);
	}

	@Override
	public boolean alteremp(employee_inf emp) {
		// TODO Auto-generated method stub
		return ed.alteremp(emp);
	}

	@Override
	public boolean findcardId(String cardId) {
		// TODO Auto-generated method stub
		return ed.findcardId(cardId);
	}

}
