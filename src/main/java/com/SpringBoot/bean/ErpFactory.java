package com.SpringBoot.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 仓库对象 erp_factory
 * 
 * @author ruoyi
 * @date 2021-04-24
 */
@Data
@TableName("erp_factory")
public class ErpFactory
{

    /** 主键 */
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /** 仓库名称 */
    private String name;

    /** 仓库地址 */
    private String address;

    /** 排序 */
    private String sort;

    /** 负责人 */
    private Long principal;

    /** 备注 */
    private String principalName;

    private Long totalCapacity;
    private Long usedCapacity;
    private Integer flag;

    /** 备注 */
    private String remark;

    /** 删除标记，0未删除，1删除 */
    @TableLogic(value = "0", delval = "1")
    private String deleteFlag;

}
