package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Notice;
import com.SpringBoot.mapper.NoticeMapper;
import com.SpringBoot.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	

	public List<Notice> select(String title, String opername,Integer index,Integer limit) {
		List<Notice> notice = noticeMapper.select(title, opername, index, limit);
		return notice;
	}


	public void delect(Integer id) {
		noticeMapper.delect(id);
	}


	public Notice selectById(Integer id) {
		Notice notice = noticeMapper.selectById(id);
		return notice;
	}


	public void update(Integer id, String content) {
		// TODO 自动生成的方法存根
		noticeMapper.update(id, content);
	}


	public void insert(String title, String content, Date createtime, String opername) {
		// TODO 自动生成的方法存根
		noticeMapper.insert(title, content, createtime, opername);
	}

	

}
