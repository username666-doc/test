package com.gec.hrm.Dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.hrm.Dao.deptDao;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.dept_inf;
import com.gec.hrm.util.AliUtil;

public class deptDaoImpl extends AliUtil<dept_inf> implements deptDao {

	@Override
	public boolean alterdept(dept_inf dept) {
		String sql="update dept_inf set name=?,remark=?  where ID=?";
		List<Object> list=new ArrayList<Object>();
		list.add(dept.getName());
		list.add(dept.getRemark());
		list.add(dept.getId());
		return update(sql, list);
	}

	@Override
	public boolean adddept(dept_inf dept) {
		String sql="INSERT INTO dept_inf VALUES(null,?,?,0)";
		List<Object> list=new ArrayList<Object>();
		list.add(dept.getName());
		list.add(dept.getRemark());
		return update(sql, list);
	}

	@Override
	public boolean deletedept(String[] ids) {
		for(int i=0;i<ids.length;i++) {
			int id=Integer.parseInt(ids[i]);
			String sql="delete from dept_inf where ID=?";
			List<Object> list = new ArrayList<Object>();
			list.add(id);
			update(sql,list);
		}
		return false;
	}

	@Override
	public PageBean<dept_inf> findAlldept(int pageNow) {
		PageBean<dept_inf> pb =new PageBean<dept_inf>();
		String sql="SELECT * FROM dept_inf LIMIT ?,?";
		pb.setList(query(sql,(pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		pb.setRowCount(queryCount("SELECT count(ID) FROM dept_inf"));
		pb.setPageNow(pageNow);	
		return pb;
	}

	@Override
	public List<dept_inf> findAlldept() {
		List<dept_inf> list = new ArrayList<dept_inf>();
		String sql="SELECT * FROM dept_inf";
		list=query(sql);
		return list;
	}
	
	@Override
	public PageBean<dept_inf> querydept(int pageNow,String deptname) {
		PageBean<dept_inf> pb = new PageBean<>();
		StringBuffer sql=new StringBuffer();
		pb.setPageNow(pageNow);
		sql.append("select count(id) from dept_inf where 1=1 ");
		if(deptname!=null) {
			deptname="%"+deptname+"%";
			sql.append(" and name like ?");
			String sqls=sql.toString();
			pb.setRowCount(queryCount(sqls,deptname));
		}
		
		sql=new StringBuffer();
		sql.append("select * from dept_inf where 1=1 ");
		if(deptname!=null) {
			deptname="%"+deptname+"%";
			sql.append(" and name like ?");
			sql.append(" limit ?,?");
			String sqlall=sql.toString();
			pb.setList(query(sqlall,deptname,(pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		}	
		return pb;
	}

	@Override
	public dept_inf finddept(int id) {
		return query("SELECT * FROM dept_inf WHERE ID=?", id).get(0);
	}

	@Override
	public dept_inf getEntity(ResultSet rs) throws Exception {
		dept_inf dept = new dept_inf();
		dept.setId(rs.getInt(1));
		dept.setName(rs.getString(2));
		dept.setRemark(rs.getString(3));
		dept.setState(rs.getInt(4));
		return dept;
	}

	

}
