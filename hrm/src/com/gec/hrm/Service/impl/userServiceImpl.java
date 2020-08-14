package com.gec.hrm.Service.impl;


import com.gec.hrm.Dao.userDao;
import com.gec.hrm.Dao.impl.userDaoImpl;
import com.gec.hrm.Service.userService;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.user_inf;

public class userServiceImpl implements userService {
	userDao ud = new userDaoImpl();

	@Override
	public boolean addUser(user_inf user) {
		// TODO Auto-generated method stub
		return ud.addUser(user);
	}

	@Override
	public PageBean<user_inf> findUserAll(int pageNow) {
		// TODO Auto-generated method stub
		return ud.findUserAll(pageNow);
	}

	@Override
	public PageBean<user_inf> queryUser(int pageNow,String loginname, String username, int status) {
		// TODO Auto-generated method stub
		return ud.queryUser(pageNow,loginname, username, status);
	}

	@Override
	public boolean deleteUser(String[] ids) {
		// TODO Auto-generated method stub
		return ud.deleteUser(ids);
	}

	@Override
	public user_inf findUser(int id) {
		// TODO Auto-generated method stub
		return ud.findUser(id);
	}

	@Override
	public boolean alterUser(user_inf user) {
		// TODO Auto-generated method stub
		return ud.alterUser(user);
	}

}
