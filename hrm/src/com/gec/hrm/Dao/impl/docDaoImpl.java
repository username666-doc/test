package com.gec.hrm.Dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.hrm.Dao.docDao;
import com.gec.hrm.Dao.userDao;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.document_inf;
import com.gec.hrm.util.AliUtil;


public class docDaoImpl extends AliUtil<document_inf> implements docDao {

	@Override
	public PageBean<document_inf> finddocAll(int pageNow) {
		PageBean<document_inf> pb = new PageBean<document_inf>();
		pb.setPageNow(pageNow);
		String sql="SELECT * FROM document_inf LIMIT ?,?";
		List<document_inf>  list = query(sql,(pageNow-1)*pb.getPageSize(),pb.getPageSize());
		pb.setList(list);
		pb.setRowCount(queryCount("SELECT count(ID) FROM document_inf"));
		return pb;
	}

	@Override
	public boolean addDoc(document_inf doc) {
		String sql="INSERT INTO document_inf  VALUES(null,?,?,?,?,?,now(),?)";
		List<Object> list = new ArrayList<Object>();
		list.add(doc.getTitle());
		list.add(doc.getFilename());
		list.add(doc.getFiletype());
		list.add(doc.getFilebytes());
		list.add(doc.getRemark());
		list.add(doc.getUser().getId());
		return update(sql, list);
	}

	@Override
	public PageBean<document_inf> querydoc(int pageNow, String title) {
		PageBean<document_inf> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		if(title!=null) {
			String sql="select count(id) from document_inf where title like ? ";
			title="%"+title+"%";
			pb.setRowCount(queryCount(sql, title));
			String sqls="select * from document_inf where title like ? limit ?,? ";
			pb.setList(query(sqls, title,(pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		}else {
			String sql="select count(id) from document_inf ";
			pb.setRowCount(queryCount(sql));
			String sqls="select * from document_inf  limit ?,? ";
			pb.setList(query(sqls,(pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		}
		return pb;
	}

	@Override
	public boolean deletedoc(String[] ids) {
		for(int i=0;i<ids.length;i++) {
			int id=Integer.parseInt(ids[i]);
			String sql="delete from document_inf where ID=?";
			List<Object> list = new ArrayList<Object>();
			list.add(id);
			update(sql,list);
		}
		return false;
	}

	@Override
	public document_inf finddoc(int id) {
		String sql="select * from document_inf where ID=?";
		return query(sql, id).get(0);
	}

	@Override
	public boolean alterdoc(document_inf doc) {
		String sql="update document_inf set title=?,filebytes=?,remark=?  where ID=?";
		List<Object> list=new ArrayList<Object>();
		list.add(doc.getTitle());
		list.add(doc.getFilebytes());
		list.add(doc.getRemark());
		list.add(doc.getId());
		return update(sql, list);
	}

	@Override
	public document_inf getEntity(ResultSet rs) throws Exception {
		userDao ud=new userDaoImpl();
		document_inf doc=new document_inf();
		doc.setId(rs.getInt(1));
		doc.setTitle(rs.getString(2));
		doc.setFilename(rs.getString(3));
		doc.setFiletype(rs.getString(4));
		doc.setFilebytes(rs.getBytes(5));
		doc.setRemark(rs.getString(6));
		doc.setCreate_date(rs.getDate(7));
		doc.setUser(ud.findUser(rs.getInt(8)));
		return doc;
	}

}
