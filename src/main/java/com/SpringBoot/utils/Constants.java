package com.SpringBoot.utils;

/**
 * @title: Constants
 * @Author HuangYan
 * @Date: 2021/2/24 15:56
 * @Version 1.0
 * @Description: ADD YOUR DESCRIPTION
 */
public class Constants {

    // 超级管理员这个角色的id，用于新增菜单时，赋予此菜单到超级管理员
    public static final Long ADMIN_ROLE_ID = 1L;

    // 用户类型(admin、管理员、普通用户)
    public static final Integer USER_TYPE_ADMIN = 0;
    public static final Integer USER_TYPE_HIGH_MANAGER = 1;
    public static final Integer USER_TYPE_USER = 2;

    // 菜单类型(M:目录 C:菜单 F:按钮 )
    public static final String MENU_TYPE_M = "M";
    public static final String MENU_TYPE_C = "C";
    public static final String MENU_TYPE_F = "F";

    // 删除标识(0：未删除  1：已删除)
    public static final Integer DELETE_FLAG_EXIST = 0;
    public static final Integer DELETE_FLAG_DELETED = 1;

}
