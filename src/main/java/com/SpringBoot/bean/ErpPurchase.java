package com.SpringBoot.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 采购对象 erp_purchase
 * 
 * @author ruoyi
 * @date 2021-04-28
 */
@Data
public class ErpPurchase
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /** 采购单号 */
//    @Excel(name = "采购单号")
    private String orderNo;

    /** 采购金额 */
//    @Excel(name = "采购金额")
    private BigDecimal money;

    /** 采购人id */
    private Long purUserId;

    /** 采购人姓名 */
//    @Excel(name = "采购人姓名")
    private String purUserName;

    /** 审核不通过原因 */
    private String reason;

    /** 采购开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "采购开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purBeginTime;

    /** 采购结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "采购结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purEndTime;

    /** 状态NEW 创建完成CONFIRM 确认CHECKING 审核中CHECK_SUCCESS 审核通过CHECK_FAIL 审核不通过PURCHASING 采购中PURCHASING_SUCCESS 采购完成PUTINT 入库中PUTING_SUCCESS 入库完成 */
//    @Excel(name = "状态NEW 创建完成CONFIRM 确认CHECKING 审核中CHECK_SUCCESS 审核通过CHECK_FAIL 审核不通过PURCHASING 采购中PURCHASING_SUCCESS 采购完成PUTINT 入库中PUTING_SUCCESS 入库完成")
    private String state;

    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    @JsonProperty("itemList")
    private List<ErpPurchaseRecord> itemList;

    /** 删除标识 */
    private Integer deleteFlag;

}
