package com.SpringBoot.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 材料对象 erp_material
 * 
 * @author ruoyi
 * @date 2021-04-21
 */

@Data
@TableName("erp_material")
public class ErpMaterial
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 材料类别 */
    private Long cId;

    /** 材料编号 */
    private String code;

    /** 材料名称 */
    private String name;

    /** 材料总数 */
    private Long total;

    /** 产品规格 */
    private String standard;

    /** 产品型号 */
    private String model;

    /** 材料单位 */
    private String unit;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 备注 */
    private String remark;

    /** $column.columnComment */
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;
}
