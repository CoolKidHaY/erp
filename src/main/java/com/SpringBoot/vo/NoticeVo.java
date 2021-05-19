package com.SpringBoot.vo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import com.SpringBoot.bean.Notice;

import lombok.Data;

/**
 * @Author: 落亦-
 * @Date: 2019/11/25 8:51
 */
@Data
public class NoticeVo extends Notice {

    //接受多个ID
    private List<Long> ids;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Long pageNum;

    private Long pageSize;

}
