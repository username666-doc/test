package com.gec.hrm.bean;


import java.util.Date;

public class user_inf {
 private int id;
 private String loginname;
 private String password;
 private int status;
 private Date createDate;
 private String username;
 
public user_inf() {
	super();
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getLoginname() {
	return loginname;
}
public void setLoginname(String loginname) {
	this.loginname = loginname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}


public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

@Override
public String toString() {
	return "user_inf [id=" + id + ", loginname=" + loginname + ", password=" + password + ", status=" + status
			+ ", createDate=" + createDate + ", username=" + username + "]";
}
  
}
