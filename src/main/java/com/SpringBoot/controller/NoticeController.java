package com.SpringBoot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.SpringBoot.service.impl.NoticeServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.bean.Notice;
import com.SpringBoot.bean.User;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.NoticeService;
import com.SpringBoot.vo.NoticeVo;
import com.fasterxml.jackson.annotation.JsonFormat;


@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;
    
    @Autowired
	private LayuiJson layuiJson;
    
    
    /**
     * 公告的查询
     * @param vo
     * @return
     */
    @RequestMapping("loadAllNotice")
    public LayuiJson<Notice> loadAllNotice(NoticeVo vo){
        Page<Notice> page = noticeService.selectNoticePage(vo);
		return new LayuiJson<>(page.getRecords(), page.getTotal(), 0);
    }

    /**
     * 添加公告
     * @param notice
     * @return
     */
    @RequestMapping("addNotice")
    public LayuiJson addNotice(Notice notice){
    	
    	try {
    		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
    		Session session = subject.getSession();
    		String  operName = session.getAttribute("username").toString();
    		notice.setOperName(operName);
    		int row = noticeService.insertNotice(notice);
    		return LayuiJson.toAjax(row);
    	}catch (Exception e) {
            e.printStackTrace();
            return LayuiJson.error("添加失败，请联系管理员");
        }
        	
          
    }

    /**
     * 修改公告
     * @param noticeVo
     * @return
     */
//    @RequestMapping("updateNotice")
//    public void updateNotice(Integer id,String title,String content){
//
//        	noticeService.update(id, content);
//           
//    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @RequestMapping("deleteNotice")
    public LayuiJson deleteNotice(Long id){
        try {
            int row = noticeService.deleteNoticeById(id);
            return LayuiJson.toAjax(row);
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiJson.error("操作失败，请联系管理员");
        }
    }

    /**
     * 批量删除公告
     * @param vo
     * @return
     */
    @RequestMapping("batchDeleteNotice")
    public LayuiJson batchDeleteNotice(NoticeVo vo){
        try {
        	int row = noticeService.batchDeleteNotice(vo);
            return LayuiJson.toAjax(row);
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiJson.error("操作失败，请联系管理员");
        }
    }


}

