package com.SpringBoot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import com.SpringBoot.bean.LogInfo;

import java.util.Date;

/**
 * @Author: 落亦-
 * @Date: 2019/11/23 18:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInfoVo extends LogInfo {

    private Long pageNum;
    private Long pageSize;

    //接收多个id
    private Long[] ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
