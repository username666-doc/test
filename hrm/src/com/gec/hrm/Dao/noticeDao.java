package com.gec.hrm.Dao;

import com.gec.hrm.bean.PageBean;
import com.gec.hrm.bean.notice_inf;

public interface noticeDao {
	public PageBean<notice_inf> findNoticeAll(int pageNow);
	public boolean addNotice(notice_inf notice);
	public PageBean<notice_inf> queryNotice(int pageNow,String name);
	public boolean  deleteNoticer(String[] ids);
	public notice_inf findNotice(int id);
	public boolean alterNotice(notice_inf notice);
}
