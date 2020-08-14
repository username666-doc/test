package com.gec.hrm.Dao;



import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.user_inf;

public interface userDao {
	public PageBean<user_inf> findUserAll(int pageNow);
	public boolean addUser(user_inf user);
	public PageBean<user_inf> queryUser(int pageNow,String loginname,String username,int status);
	public boolean  deleteUser(String[] ids);
	public user_inf findUser(int id);
	public boolean alterUser(user_inf user);
}
