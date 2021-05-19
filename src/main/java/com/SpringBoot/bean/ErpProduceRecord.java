package com.SpringBoot.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 生产记录对象 erp_produce_record
 * 
 * @author ruoyi
 * @date 2021-05-15
 */
@Data
public class ErpProduceRecord
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 产品编号 */
//    @Excel(name = "产品编号")
    @JsonProperty("pCode")
    private String pCode;

    @JsonProperty("pName")
    private String pName;
    /** 生产编号 */
//    @Excel(name = "生产编号")
    private String code;

    /** 数量 */
//    @Excel(name = "数量")
    private Long num;

    /** 预计生产花费时间 */
//    @Excel(name = "预计生产花费时间")
    private Integer costDay;

    /** 真实花费时间 */
//    @Excel(name = "真实花费时间")
    private Integer realCostDay;

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
    private String reason;

    /** 删除标识 */
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;
}
