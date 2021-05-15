package com.SpringBoot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("erp_purchase_record")
public class ErpPurchaseRecord
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /** 采购编号 */
//    @Excel(name = "采购编号")
    @JsonProperty("pCode")
    private String pCode;

    /** 材料编号 */
//    @Excel(name = "材料编号")
    @JsonProperty("mCode")
    private String mCode;

    @JsonProperty("mName")
    private String mName;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 采购数量 */
//    @Excel(name = "采购数量")

    private Long num;

    /** 总价 */
//    @Excel(name = "总价")
    private BigDecimal totalPrice;

    /** 币种 */
//    @Excel(name = "币种")
    private String unit;

    /** 供应商id */
//    @Excel(name = "供应商id")
    @JsonProperty("pId")
    private Long pId;

    /** 供应商名称 */
//    @Excel(name = "供应商名称")
    @JsonProperty("pName")
    private String pName;
}