package com.SpringBoot.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商对象 erp_provider
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
@Data
@TableName("erp_provider")
public class ErpProvider
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private BigDecimal money;

    /** 供应商名称 */
//    @Excel(name = "供应商名称")
    private String name;

    /** 联系人 */
//    @Excel(name = "联系人")
    private String contacts;

    /** 联系电话 */
//    @Excel(name = "联系电话")
    private String phoneNum;

    /** 电子邮箱 */
//    @Excel(name = "电子邮箱")
    private String email;

    /** 类型 */
//    @Excel(name = "类型")
    private Integer type;

    /** 传真 */
//    @Excel(name = "传真")
    private String fax;

    /** 手机 */
//    @Excel(name = "手机")
    private String telephone;

    /** 地址 */
//    @Excel(name = "地址")
    private String address;

    private String remark;

    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 删除标记，0未删除，1删除 */
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;
}
