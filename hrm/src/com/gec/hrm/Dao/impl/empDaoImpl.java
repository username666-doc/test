package com.gec.hrm.Dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.hrm.Dao.deptDao;
import com.gec.hrm.Dao.empDao;
import com.gec.hrm.Dao.jobDao;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.employee_inf;
import com.gec.hrm.util.AliUtil;

public class empDaoImpl extends AliUtil<employee_inf> implements empDao {

	@Override
	public PageBean<employee_inf> findempAll(int pageNow) {
		PageBean<employee_inf> pb = new PageBean<employee_inf>();
		pb.setPageNow(pageNow);
		String sql="SELECT * FROM employee_inf LIMIT ?,?";
		List<employee_inf>  list = query(sql,(pageNow-1)*pb.getPageSize(),pb.getPageSize());
		pb.setList(list);
		pb.setRowCount(queryCount("SELECT count(ID) FROM employee_inf"));
		return pb;
	}

	@Override
	public boolean addemp(employee_inf emp) {
		String sql="INSERT INTO employee_inf  VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),0,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(emp.getName());
		list.add(emp.getCardId());
		list.add(emp.getAddress());
		list.add(emp.getPost_code());
		list.add(emp.getTel());
		list.add(emp.getPhone());
		list.add(emp.getQq_num());
		list.add(emp.getEmail());
		list.add(emp.getSex());
		list.add(emp.getParty());
		list.add(emp.getBirthday());
		list.add(emp.getRace());
		list.add(emp.getEducation());
		list.add(emp.getSpeciality());
		list.add(emp.getHobby());
		list.add(emp.getRemark());
		list.add(emp.getDept().getId());
		list.add(emp.getJob().getId());
		return update(sql, list);
	}

	@Override
	public PageBean<employee_inf> queryemp(int pageNow,String name,String cardId,int job_id,int sex,String phone,int dept_id) {
		PageBean<employee_inf> pb = new PageBean<employee_inf>();
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		pb.setPageNow(pageNow);
		sql.append("select count(id) from employee_inf where 1=1 ");
		if(name!=null) {
			name="%"+name+"%";
			sql.append(" and name like ?");
			list.add(name);
		}
		if(cardId!=null) {
			cardId="%"+cardId+"%";
			sql.append(" and card_Id like ?");
			list.add(cardId);
		}
		if(job_id>0) {
			sql.append(" and job_id=?");
			list.add(job_id);
		}
		if(sex>0) {
			sql.append(" and sex=?");
			list.add(sex);
		}
		if(phone!=null) {
			phone="%"+phone+"%";
			sql.append(" and phone like ?");
			list.add(phone);
		}
		
		if(dept_id>0) {
			sql.append(" and dept_id=?");
			list.add(dept_id);
		}
		String sqls=sql.toString();
		pb.setRowCount(queryCount(sqls,list.toArray()));
		list = new ArrayList<Object>();
		sql=new StringBuffer();
		sql.append("select * from employee_inf where 1=1 ");
		if(name!=null) {
			name="%"+name+"%";
			sql.append(" and name like ?");
			list.add(name);
		}
		if(cardId!=null) {
			cardId="%"+cardId+"%";
			sql.append(" and card_Id like ?");
			list.add(cardId);
		}
		if(job_id>0) {
			sql.append(" and job_id=?");
			list.add(job_id);
		}
		if(sex>0) {
			sql.append(" and sex=?");
			list.add(sex);
		}
		if(phone!=null) {
			phone="%"+phone+"%";
			sql.append(" and phone like ?");
			list.add(phone);
		}
		
		if(dept_id>0) {
			sql.append(" and dept_id=?");
			list.add(dept_id);
		}
		sql.append(" limit ?,?");
		list.add((pageNow-1)*pb.getPageSize());
		list.add(pb.getPageSize());
		String sqlall=sql.toString();
		pb.setList(query(sqlall,list.toArray()));
		return pb;
	}

	@Override
	public boolean deleteemp(String[] ids) {
		for(int i=0;i<ids.length;i++) {
			int id=Integer.parseInt(ids[i]);
			String sql="delete from employee_inf where ID=?";
			List<Object> list = new ArrayList<Object>();
			list.add(id);
			update(sql,list);
		}
		return false;
	}

	@Override
	public employee_inf findemp(int id) {
		String sql="select * from employee_inf where ID=?";
		return query(sql, id).get(0);
	}

	@Override
	public boolean alteremp(employee_inf emp) {
		String sql="update employee_inf set name=?,card_Id=?,address=?,post_code=?,tel=?,phone=?,qq_num=?,email=?,sex=?,party=?,birthday=?,race=?,education=?,speciality=?,hobby=?,remark=?,dept_id=?,job_id=?  where ID=?";
		List<Object> list=new ArrayList<Object>();
		list.add(emp.getName());
		list.add(emp.getCardId());
		list.add(emp.getAddress());
		list.add(emp.getPost_code());
		list.add(emp.getTel());
		list.add(emp.getPhone());
		list.add(emp.getQq_num());
		list.add(emp.getEmail());
		list.add(emp.getSex());
		list.add(emp.getParty());
		list.add(emp.getBirthday());
		list.add(emp.getRace());
		list.add(emp.getEducation());
		list.add(emp.getSpeciality());
		list.add(emp.getHobby());
		list.add(emp.getRemark());
		list.add(emp.getDept().getId());
		list.add(emp.getJob().getId());
		list.add(emp.getId());
		return update(sql, list);
	}

	
	@Override
	public boolean findcardId(String cardId) {
		String sql="SELECT * FROM employee_inf WHERE CARD_ID=?";
		List<employee_inf> list=query(sql, cardId);
		if(list.size()>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public employee_inf getEntity(ResultSet rs) throws Exception {
		deptDao dept = new deptDaoImpl();
		jobDao job=new jobDaoImpl();
		employee_inf emp=new employee_inf();
		emp.setId(rs.getInt(1));
		emp.setName(rs.getString(2));
		emp.setCardId(rs.getString(3));
		emp.setAddress(rs.getString(4));
		emp.setPost_code(rs.getString(5));
		emp.setTel(rs.getString(6));
		emp.setPhone(rs.getString(7));
		emp.setQq_num(rs.getString(8));
		emp.setEmail(rs.getString(9));
		emp.setSex(rs.getInt(10));
		emp.setParty(rs.getString(11));
		emp.setBirthday(rs.getDate(12));
		emp.setRace(rs.getString(13));
		emp.setEducation(rs.getString(14));
		emp.setSpeciality(rs.getString(15));
		emp.setHobby(rs.getString(16));
		emp.setRemark(rs.getString(17));
		emp.setCreateDate(rs.getDate(18));
		emp.setState(rs.getInt(19));
		emp.setDept(dept.finddept(rs.getInt(20)));
		emp.setJob(job.findjob(rs.getInt(21)));
		return emp;
	}

	

}
