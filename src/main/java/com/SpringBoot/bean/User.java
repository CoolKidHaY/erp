package com.SpringBoot.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
	private Long id;

    private String name;

    private String loginName;

    private String pwd;

    private String address;

    private String phone;

    private Integer sex;

    private String remark;

    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;
    
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date hireDate;

    /**
     * 是否可用，0不可用，1可用
     */
    private Integer available;

    /**
     * 排序码
     */
    private Integer orderNum;

    /**
     * 用户类型[0超级管理员，1管理员，2普通用户]
     */
    private Integer type;

    /**
     * 用户头像地址
     */
    private String imgPath;

    /**
     * 盐
     */
    private String salt;

    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;

    /**
     * 领导名称
     */
    @TableField(exist = false)
    private String leaderName;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptTree_select_input;
 
}
