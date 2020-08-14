package com.gec.hrm.Dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.hrm.Dao.typeDao;
import com.gec.hrm.bean.type_inf;
import com.gec.hrm.util.AliUtil;

public class typeDaoImpl extends AliUtil<type_inf> implements typeDao {

	@Override
	public type_inf findtype(int id) {
		String sql="SELECT * FROM type_inf WHERE id=?";
		return query(sql, id).get(0);
	}

	@Override
	public type_inf getEntity(ResultSet rs) throws Exception {
		type_inf type =new type_inf();
		type.setId(rs.getInt(1));
		type.setName(rs.getString(2));
		type.setCreate_date(rs.getDate(3));
		type.setState(rs.getInt(4));
		type.setUser_id(rs.getInt(5));
		type.setModify_date(rs.getDate(6));
		return type;
	}

	@Override
	public List<type_inf> findtypeAll() {
		String sql="SELECT * FROM type_inf";
		return query(sql);
	}

	@Override
	public boolean addType(type_inf type) {
		String sql="INSERT INTO type_inf  VALUES(null,?,now(),0,?,now())";
		List<Object> list = new ArrayList<Object>();
		list.add(type.getName());
		list.add(type.getUser_id());
		return update(sql,list);
	}

}
