package com.SpringBoot.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @title: RoleMenu
 * @Author HuangYan
 * @Date: 2021/2/25 15:37
 * @Version 1.0
 * @Description: ADD YOUR DESCRIPTION
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu {
    private Long rid;
    private Long mid;
}
