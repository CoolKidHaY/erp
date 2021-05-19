package com.SpringBoot.service;

import com.SpringBoot.vo.NoticeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.SpringBoot.bean.Notice;

import java.util.List;

public interface NoticeService extends IService<Notice> {

    Page<Notice> selectNoticePage(NoticeVo vo);

    int insertNotice(Notice notice);

    int deleteNoticeById(Long id);

    int batchDeleteNotice(NoticeVo vo);
}
