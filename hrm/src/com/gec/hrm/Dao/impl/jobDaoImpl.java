package com.gec.hrm.Dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.hrm.Dao.jobDao;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.job_inf;
import com.gec.hrm.util.AliUtil;

public class jobDaoImpl extends AliUtil<job_inf> implements jobDao {

	@Override
	public PageBean<job_inf> findJobAll(int pageNow) {
		PageBean<job_inf> pb = new PageBean<job_inf>();
		pb.setPageNow(pageNow);
		String sql="SELECT * FROM job_inf LIMIT ?,?";
		List<job_inf> list = query(sql,(pageNow-1)*pb.getPageSize(),pb.getPageSize());
		pb.setList(list);
		pb.setRowCount(queryCount("SELECT count(ID) FROM job_inf"));
		return pb;
	}
	
	@Override
	public List<job_inf> findJobAll() {
		String sql="SELECT * FROM job_inf";
		List<job_inf> list = query(sql);
		return list;
	}

	@Override
	public boolean addJob(job_inf job) {
		String sql="INSERT INTO Job_inf  VALUES(null,?,?,0)";
		List<Object> list = new ArrayList<Object>();
		list.add(job.getName());
		list.add(job.getRemark());
		return update(sql, list);
	}

	@Override
	public PageBean<job_inf> queryjob(int pageNow, String name) {
		PageBean<job_inf> pb = new PageBean<>();
		StringBuffer sql=new StringBuffer();
		pb.setPageNow(pageNow);
		sql.append("select count(id) from job_inf where 1=1 ");
		if(name!=null) {
			name="%"+name+"%";
			sql.append(" and name like ?");
			String sqls=sql.toString();
			pb.setRowCount(queryCount(sqls,name));
		}
		
		sql=new StringBuffer();
		sql.append("select * from job_inf where 1=1 ");
		if(name!=null) {
			name="%"+name+"%";
			sql.append(" and name like ?");
			sql.append(" limit ?,?");
			String sqlall=sql.toString();
			pb.setList(query(sqlall,name,(pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		}	
		return pb;
	}

	@Override
	public boolean deletejob(String[] ids) {
		for(int i=0;i<ids.length;i++) {
			int id=Integer.parseInt(ids[i]);
			String sql="delete from job_inf where ID=?";
			List<Object> list = new ArrayList<Object>();
			list.add(id);
			update(sql,list);
		}
		return false;
	}

	@Override
	public job_inf findjob(int id) {
		String sql="select * from job_inf where ID=?";
		return query(sql, id).get(0);
	}

	@Override
	public boolean alterjob(job_inf job) {
		String sql="update job_inf set NAME=?,remark=?  where ID=?";
		List<Object> list=new ArrayList<Object>();
		list.add(job.getName());
		list.add(job.getRemark());
		list.add(job.getId());
		return update(sql,list);
	}

	@Override
	public job_inf getEntity(ResultSet rs) throws Exception {
		job_inf job=new job_inf();
		job.setId(rs.getInt(1));
		job.setName(rs.getString(2));
		job.setRemark(rs.getString(3));
		job.setState(rs.getInt(4));
		return job;
	}

	

}
