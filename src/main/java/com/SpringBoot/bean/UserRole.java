package com.SpringBoot.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @title: UserRole
 * @Author HuangYan
 * @Date: 2021/3/2 17:42
 * @Version 1.0
 * @Description: ADD YOUR DESCRIPTION
 */
@Data
@TableName("sys_user_role")
public class UserRole {
    private Long uid;
    private Long rid;
}
