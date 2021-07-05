package com.SpringBoot.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
/**
 * 生产记录对象 erp_sales_record
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Data
public class ErpSalesRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /** 销售编号 */
//    @Excel(name = "销售编号")
    private String code;

    /** 产品编号 */
//    @Excel(name = "产品编号")
    @JsonProperty("pCode")
    private String pCode;

    /** 产品名称 */
//    @Excel(name = "产品名称")
    @JsonProperty("pName")
    private String pName;

    /** 数量 */
//    @Excel(name = "数量")
    private Long num;

    /** 单价 */
//    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 总价 */
//    @Excel(name = "总价")
    private BigDecimal totalPrice;

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

    /** 状态 */
//    @Excel(name = "状态")
    private String state;

    /** 审核人 */
//    @Excel(name = "审核人")
    private String auditBy;

    /** 审核备注/未通过原因 */
//    @Excel(name = "审核备注/未通过原因")
    private String reason;

    /** 删除标识 */
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;
}
