package com.gec.hrm.Service.impl;

import java.util.List;

import com.gec.hrm.Dao.typeDao;
import com.gec.hrm.Dao.impl.typeDaoImpl;
import com.gec.hrm.Service.TypeService;
import com.gec.hrm.bean.type_inf;

public class TypeServiceImpl implements TypeService {
	typeDao td= new typeDaoImpl();
	@Override
	public type_inf findtype(int id) {
		// TODO Auto-generated method stub
		return td.findtype(id);
	}

	@Override
	public List<type_inf> findtypeAll() {
		// TODO Auto-generated method stub
		return td.findtypeAll();
	}

	@Override
	public boolean addType(type_inf type) {
		// TODO Auto-generated method stub
		return td.addType(type);
	}

}
