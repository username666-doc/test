package com.gec.hrm.Dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.gec.hrm.Dao.loginDao;
import com.gec.hrm.bean.user_inf;
import com.gec.hrm.util.AliUtil;

public class loginDaoImpl extends AliUtil<user_inf> implements loginDao {

	@Override
	public user_inf login(String loginname,String password) {
		String sql="SELECT * FROM user_inf WHERE loginname=? AND PASSWORD=? ";
		List<user_inf> list = query(sql, loginname,password);
		if(list.size()==0){
			return null;
		}else {
			return list.get(0);
		}
		
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
