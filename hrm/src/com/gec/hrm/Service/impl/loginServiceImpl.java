package com.gec.hrm.Service.impl;

import com.gec.hrm.Dao.loginDao;
import com.gec.hrm.Dao.impl.loginDaoImpl;
import com.gec.hrm.Service.loginService;
import com.gec.hrm.bean.user_inf;

public class loginServiceImpl implements loginService {
	loginDao log = new loginDaoImpl();
	@Override
	public user_inf login(String loginname, String password) {
		return log.login(loginname, password);
	}

}
