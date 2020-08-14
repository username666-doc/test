package com.gec.hrm.Dao;

import java.util.List;

import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.job_inf;

public interface jobDao {
	public PageBean<job_inf> findJobAll(int pageNow);
	public boolean addJob(job_inf job);
	public PageBean<job_inf> queryjob(int pageNow,String name);
	public boolean  deletejob(String[] ids);
	public job_inf findjob(int id);
	public boolean alterjob(job_inf job);
	public List<job_inf> findJobAll();
}
