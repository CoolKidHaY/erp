package com.SpringBoot.bean;

import com.SpringBoot.utils.TreeEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 材料类别对象 erp_material_category
 * 
 * @author ruoyi
 * @date 2021-04-23
 */

@Data
@TableName("erp_material_category")
public class ErpMaterialCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 父id */
    private Long parentId;

    private String name;

    /** 排序 */
    private Long sort;

    private String remark;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 创建者 */
    private String updateBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 删除标识 */
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;
}
