package com.gec.hrm.Service.impl;

import com.gec.hrm.Dao.noticeDao;
import com.gec.hrm.Dao.impl.noticeDaoImpl;
import com.gec.hrm.Service.noticeService;
import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.notice_inf;

public class noticeServiceImpl implements noticeService {
	noticeDao nd = new noticeDaoImpl();
	@Override
	public PageBean<notice_inf> findNoticeAll(int pageNow) {
		// TODO Auto-generated method stub
		return nd.findNoticeAll(pageNow);
	}

	@Override
	public boolean addNotice(notice_inf notice) {
		// TODO Auto-generated method stub
		return nd.addNotice(notice);
	}

	@Override
	public PageBean<notice_inf> queryNotice(int pageNow, String name) {
		// TODO Auto-generated method stub
		return nd.queryNotice(pageNow,name);
	}

	@Override
	public boolean deleteNoticer(String[] ids) {
		// TODO Auto-generated method stub
		return nd.deleteNoticer(ids);
	}

	@Override
	public notice_inf findNotice(int id) {
		// TODO Auto-generated method stub
		return nd.findNotice(id);
	}

	@Override
	public boolean alterNotice(notice_inf notice) {
		// TODO Auto-generated method stub
		return nd.alterNotice(notice);
	}

}
