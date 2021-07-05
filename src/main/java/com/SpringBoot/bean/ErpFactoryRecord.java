package com.SpringBoot.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 仓库操作记录对象 erp_factory_record
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
@TableName("erp_factory_record")
@Data
public class ErpFactoryRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /** 仓库id */
    private Long cId;

    /** 仓库名称 */
    private String cName;

    /** 类型(出库/入库) */
//    @Excel(name = "类型(出库/入库)")
    private String type;

    /** 出入库商品的单号 */
//    @Excel(name = "入出库id")
    private String code;

    /** 出入库开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "出入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operBeginTime;

    /** 出入库结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "出入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operEndTime;

    /** 状态 */
//    @Excel(name = "状态")
    private String state;

    private String remark;

    /** 删除标记，0未删除，1删除 */
//    @Excel(name = "删除标记，0未删除，1删除")
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;
}
