package com.SpringBoot.mapper;

import com.SpringBoot.bean.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 存入获取此角色的用户集合
     * @param users
     * @return
     */
    int batchInsertUserRole(ArrayList<UserRole> users);
}
