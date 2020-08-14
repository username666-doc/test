package com.gec.hrm.Service.impl;

import java.util.List;

import com.gec.hrm.Dao.jobDao;
import com.gec.hrm.Dao.impl.jobDaoImpl;
import com.gec.hrm.Service.jobService;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.job_inf;

public class jobServiceImpl implements jobService {
	jobDao jd=new jobDaoImpl();
	@Override
	public PageBean<job_inf> findJobAll(int pageNow) {
		// TODO Auto-generated method stub
		return jd.findJobAll(pageNow);
	}

	@Override
	public boolean addJob(job_inf job) {
		// TODO Auto-generated method stub
		return jd.addJob(job);
	}

	@Override
	public PageBean<job_inf> queryjob(int pageNow, String name) {
		// TODO Auto-generated method stub
		return jd.queryjob(pageNow,name);
	}

	@Override
	public boolean deletejob(String[] ids) {
		// TODO Auto-generated method stub
		return jd.deletejob(ids);
	}

	@Override
	public job_inf findjob(int id) {
		// TODO Auto-generated method stub
		return jd.findjob(id);
	}

	@Override
	public boolean alterjob(job_inf job) {
		// TODO Auto-generated method stub
		return jd.alterjob(job);
	}

	@Override
	public List<job_inf> findJobAll() {
		// TODO Auto-generated method stub
		return jd.findJobAll();
	}

}
