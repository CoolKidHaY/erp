package com.SpringBoot.bean;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 库存对象 erp_factory_material
 * 
 * @author ruoyi
 * @date 2021-04-24
 */
@Data
@TableName("erp_factory_material")
public class ErpFactoryMaterial
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /** 存储物品编号(材料/产品) */
    private String code;

    /** 存储商品类型 */
    private Integer type;

    /** 存储商品名称 */
    private String name;

    /** 仓库id */
    private Long fId;

    /** 总量 */
    private Long total;

    /** 状态(充足。正常。短缺。无货) */
    private String state;

    /** 单价 */
    private BigDecimal unitPrice;

    private String remark;

    /** 删除标识 */
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;

}
