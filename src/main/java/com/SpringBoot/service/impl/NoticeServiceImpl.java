package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Notice;
import com.SpringBoot.mapper.NoticeMapper;
import com.SpringBoot.service.NoticeService;
import com.SpringBoot.vo.NoticeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

	@Override
	public Page<Notice> selectNoticePage(NoticeVo vo) {
		QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
		queryWrapper.like( StringUtils.isNotBlank(vo.getOperName()), "oper_name", vo.getOperName())
				.like(StringUtils.isNotBlank(vo.getTitle()), "title", vo.getTitle());
		return this.getBaseMapper().selectPage(new Page<>(vo.getPageNum(), vo.getPageSize()),queryWrapper);
	}

	@Override
	public int insertNotice(Notice notice) {
		notice.setCreateTime(new Date());
		return this.getBaseMapper().insert(notice);
	}

	@Override
	public int deleteNoticeById(Long id) {
		return this.getBaseMapper().deleteById(id);
	}

	@Override
	public int batchDeleteNotice(NoticeVo vo) {
		return this.getBaseMapper().deleteBatchIds(vo.getIds());
	}

}
