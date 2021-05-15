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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 材料对象 erp_material,材料与商品共用一张表?
 * 
 * @author ruoyi
 * @date 2021-04-21
 */

@Data
@TableName("erp_material")
public class ErpMaterial implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /** 材料类别 */
//    @Excel(name = "材料类别")
    private Long cId;

    /** 材料编号 */
//    @Excel(name = "材料编号")
    private String code;

    /** 材料名称 */
//    @Excel(name = "材料名称")
    private String name;

    /** 材料总数 */
//    @Excel(name = "材料总数")
    private Long total;

    /** 产品规格 */
//    @Excel(name = "产品规格")
    private String standard;

    /** 产品型号 */
//    @Excel(name = "产品型号")
    private String model;

    /** 材料单位 */
//    @Excel(name = "材料单位")
    private String unit;

    /** 单个成本 */
//    @Excel(name = "单个成本")
    private BigDecimal cost;

    /** 单价 */
//    @Excel(name = "单价")
    private BigDecimal unitPrice;

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

    /** 备注 */
    private String remark;

    /** $column.columnComment */
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;
}
