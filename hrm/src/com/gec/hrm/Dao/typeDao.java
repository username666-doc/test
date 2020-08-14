package com.gec.hrm.Dao;

import java.util.List;

import com.gec.hrm.bean.type_inf;

public interface typeDao {
	public type_inf findtype(int id);
	public List<type_inf> findtypeAll();
	public boolean addType(type_inf type);
}
