package com.gec.hrm.bean;

import java.util.Arrays;
import java.util.Date;

public class document_inf {
 private int id;
 private String title;
 private String filename;
 private String filetype;
 private byte[] filebytes;
 private String remark;
 private Date create_date;
 private user_inf user;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getFiletype() {
	return filetype;
}
public void setFiletype(String filetype) {
	this.filetype = filetype;
}
public byte[] getFilebytes() {
	return filebytes;
}
public void setFilebytes(byte[] filebytes) {
	this.filebytes = filebytes;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public Date getCreate_date() {
	return create_date;
}
public void setCreate_date(Date create_date) {
	this.create_date = create_date;
}
public user_inf getUser() {
	return user;
}
public void setUser(user_inf user) {
	this.user = user;
}
@Override
public String toString() {
	return "document_inf [id=" + id + ", title=" + title + ", filename=" + filename + ", filetype=" + filetype
			+ ", filebytes=" + Arrays.toString(filebytes) + ", remark=" + remark + ", create_date=" + create_date
			+ ", user=" + user + "]";
}


 
}
