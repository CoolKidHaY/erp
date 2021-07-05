package com.SpringBoot.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @title: BaseEntity
 * @Author HuangYan
 * @Date: 2021/6/3 11:57
 * @Version 1.0
 * @Description: ADD YOUR DESCRIPTION
 */
@Data
public class BaseEntity {
    @TableField(exist = false)
    private Integer pageNum;
    @TableField(exist = false)
    private Integer pageSize;
}
