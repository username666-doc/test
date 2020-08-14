package com.gec.hrm.bean;

import java.util.Date;

public class employee_inf {
 private int id;//
 private String name;
 private String cardId;
 private String address;
 private String post_code;
 private String tel;
 private String phone;
 private String qq_num;
 private String email;
 private int sex;
 private String party;
 private Date birthday;
 private String race;
 private String education;
 private String speciality;
 private String hobby;
 private String remark;
 private Date createDate;//
 private int state;//
 private dept_inf dept;
 private job_inf job;
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

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPost_code() {
	return post_code;
}
public void setPost_code(String post_code) {
	this.post_code = post_code;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getQq_num() {
	return qq_num;
}
public void setQq_num(String qq_num) {
	this.qq_num = qq_num;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getParty() {
	return party;
}
public void setParty(String party) {
	this.party = party;
}

public String getRace() {
	return race;
}
public void setRace(String race) {
	this.race = race;
}
public String getEducation() {
	return education;
}
public void setEducation(String education) {
	this.education = education;
}
public String getSpeciality() {
	return speciality;
}
public void setSpeciality(String speciality) {
	this.speciality = speciality;
}
public String getHobby() {
	return hobby;
}
public void setHobby(String hobby) {
	this.hobby = hobby;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public int getSex() {
	return sex;
}
public void setSex(int sex) {
	this.sex = sex;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}

public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public dept_inf getDept() {
	return dept;
}
public void setDept(dept_inf dept) {
	this.dept = dept;
}
public job_inf getJob() {
	return job;
}
public void setJob(job_inf job) {
	this.job = job;
}
public String getCardId() {
	return cardId;
}
public void setCardId(String cardId) {
	this.cardId = cardId;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}


}
