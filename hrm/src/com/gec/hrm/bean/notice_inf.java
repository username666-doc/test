package com.gec.hrm.bean;

import java.util.Date;

public class notice_inf {
 private int id;
 private String name;
 private Date create_date;
 private type_inf type;
 private String content;
 private int user_id;
 private Date modify_date;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getCreate_date() {
	return create_date;
}
public void setCreate_date(Date create_date) {
	this.create_date = create_date;
}
public type_inf getType() {
	return type;
}
public void setType(type_inf type) {
	this.type = type;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}


public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public Date getModify_date() {
	return modify_date;
}
public void setModify_date(Date modify_date) {
	this.modify_date = modify_date;
}
 
}
