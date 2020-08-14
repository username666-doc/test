package com.gec.hrm.Dao;

import com.gec.hrm.bean.user_inf;

public interface loginDao {
	public user_inf login(String loginname,String password);
}
