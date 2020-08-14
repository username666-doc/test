package com.gec.hrm.Dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.hrm.Dao.userDao;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.user_inf;
import com.gec.hrm.util.AliUtil;

public class userDaoImpl extends AliUtil<user_inf> implements userDao {

	@Override
	public PageBean<user_inf> findUserAll(int pageNow) {
		PageBean<user_inf> pb = new PageBean<user_inf>();
		pb.setPageNow(pageNow);
		String sql="SELECT * FROM user_inf LIMIT ?,?";
		List<user_inf>  list = query(sql,(pageNow-1)*pb.getPageSize(),pb.getPageSize());
		pb.setList(list);
		pb.setRowCount(queryCount("SELECT count(ID) FROM user_inf"));
		return pb;
	}

	@Override
	public boolean addUser(user_inf user) {
		String sql="INSERT INTO user_inf  VALUES(null,?,?,?,now(),?)";
		List<Object> list = new ArrayList<Object>();
		list.add(user.getLoginname());
		list.add(user.getPassword());
		list.add(user.getStatus());
		list.add(user.getUsername());
		return update(sql, list);
	}

	@Override
	public PageBean<user_inf> queryUser(int pageNow,String loginname, String username, int status) {
		PageBean<user_inf> pb = new PageBean<>();
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		pb.setPageNow(pageNow);
		sql.append("select count(id) from user_inf where 1=1 ");
		if(username!=null||!"".equals(username)) {
			username="%"+username+"%";
			sql.append(" and username like ?");
			list.add(username);
		}
		if(loginname!=null||!"".equals(loginname)) {
			loginname="%"+loginname+"%";
			sql.append(" and loginname like ?");
			list.add(loginname);
		}
		if(status!=0||"".equals(status)) {
			sql.append(" and status=?");
			list.add(status);
		}
		String sqls=sql.toString();
		pb.setRowCount(queryCount(sqls,list.toArray()));
		list = new ArrayList<Object>();
		sql=new StringBuffer();
		sql.append("select * from user_inf where 1=1 ");
		if(username!=null||!"".equals(username)) {
			username="%"+username+"%";
			sql.append(" and username like ?");
			list.add(username);
		}
		if(loginname!=null||!"".equals(loginname)) {
			loginname="%"+loginname+"%";
			sql.append(" and loginname like ?");
			list.add(loginname);
		}
		if(status!=0||"".equals(status)) {
			sql.append(" and status=?");
			list.add(status);
		}
		sql.append(" limit ?,?");
		list.add((pageNow-1)*pb.getPageSize());
		list.add(pb.getPageSize());
		String sqlall=sql.toString();
		pb.setList(query(sqlall,list.toArray()));
		return pb;
	}

	@Override
	public boolean deleteUser(String[] ids) {
		for(int i=0;i<ids.length;i++) {
			int id=Integer.parseInt(ids[i]);
			String sql="delete from user_inf where ID=?";
			List<Object> list = new ArrayList<Object>();
			list.add(id);
			update(sql,list);
		}
		return false;
	}
	
	@Override
	public user_inf findUser(int id) {
		String sql="select * from user_inf where ID=?";
		return query(sql, id).get(0);
	}
	
	@Override
	public boolean alterUser(user_inf user) {
		String sql="update user_inf set password=?,loginname=?,status=?,username=?  where ID=?";
		List<Object> list=new ArrayList<Object>();
		list.add(user.getPassword());
		list.add(user.getLoginname());
		list.add(user.getStatus());
		list.add(user.getUsername());
		list.add(user.getId());
		return update(sql, list);
	}
	
	@Override
	public user_inf getEntity(ResultSet rs) throws Exception {
		user_inf user = new user_inf();
		user.setId(rs.getInt(1));
		user.setLoginname(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setStatus(rs.getInt(4));
		user.setCreateDate(rs.getDate(5));
		user.setUsername(rs.getString(6));
		return user;
	}

	
}
