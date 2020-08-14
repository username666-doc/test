package com.gec.hrm.Dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.hrm.Dao.noticeDao;
import com.gec.hrm.Dao.typeDao;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.notice_inf;
import com.gec.hrm.util.AliUtil;

public class noticeDaoImpl extends AliUtil<notice_inf> implements noticeDao{

	@Override
	public PageBean<notice_inf> findNoticeAll(int pageNow) {
		PageBean<notice_inf> pb = new PageBean<notice_inf>();
		pb.setPageNow(pageNow);
		String sql="SELECT * FROM notice_inf LIMIT ?,?";
		List<notice_inf>  list = query(sql,(pageNow-1)*pb.getPageSize(),pb.getPageSize());
		pb.setList(list);
		pb.setRowCount(queryCount("SELECT count(ID) FROM notice_inf"));
		return pb;
	}

	@Override
	public boolean addNotice(notice_inf notice) {
		String sql="INSERT INTO notice_inf  VALUES(null,?,now(),?,?,?,now())";
		List<Object> list = new ArrayList<Object>();
		list.add(notice.getName());
		list.add(notice.getType().getId());
		list.add(notice.getContent());
		list.add(notice.getUser_id());
		return update(sql, list);
	}

	@Override
	public PageBean<notice_inf> queryNotice(int pageNow, String name) {
		PageBean<notice_inf> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		if(name!=null) {
			String sql="select count(id) from notice_inf where name like ? ";
			name="%"+name+"%";
			pb.setRowCount(queryCount(sql, name));
			String sqls="select * from notice_inf where name like ? limit ?,? ";
			pb.setList(query(sqls, name,(pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		}else {
			String sql="select count(id) from notice_inf ";
			pb.setRowCount(queryCount(sql));
			String sqls="select * from notice_inf  limit ?,? ";
			pb.setList(query(sqls,(pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		}
		return pb;
	}

	@Override
	public boolean deleteNoticer(String[] ids) {
		for(int i=0;i<ids.length;i++) {
			int id=Integer.parseInt(ids[i]);
			String sql="delete from notice_inf where ID=?";
			List<Object> list = new ArrayList<Object>();
			list.add(id);
			update(sql,list);
		}
		return false;
	}

	@Override
	public notice_inf findNotice(int id) {
		String sql="select * from notice_inf where ID=?";
		return query(sql, id).get(0);
	}
	@Override
	public boolean alterNotice(notice_inf notice) {
		String sql="update notice_inf set name=?,type_id=?,content=?,modify_date=now()  where ID=?";
		List<Object> list=new ArrayList<Object>();
		list.add(notice.getName());
		list.add(notice.getType().getId());
		list.add(notice.getContent());
		list.add(notice.getId());
		return update(sql, list);
	}

	@Override
	public notice_inf getEntity(ResultSet rs) throws Exception {
		typeDao td=new typeDaoImpl();
		notice_inf not =new notice_inf();
		not.setId(rs.getInt(1));
		not.setName(rs.getString(2));
		not.setCreate_date(rs.getDate(3));
		not.setType(td.findtype(rs.getInt(4)));
		not.setContent(rs.getString(5));
		not.setUser_id(rs.getInt(6));
		not.setModify_date(rs.getDate(7));
		return not;
	}

}
