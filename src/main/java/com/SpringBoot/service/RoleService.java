package com.SpringBoot.service;

import com.SpringBoot.bean.User;
import com.SpringBoot.vo.RoleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.SpringBoot.bean.Role;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;

public interface RoleService extends IService<Role> {

    Role getRoleById(Long id);

    int updateRole(RoleVo roleVo, User user);

    IPage<Role> selectRoleList(RoleVo vo);

    /**
     * 角色id查询用户集合
     * @param vo
     * @return
     */
    IPage<User> selectUserByRoleId(RoleVo vo);

    /**
     * 删除用户与角色之间的联系
     * @param userId
     * @param roleId
     * @return
     */
    int deleteRolesUser(Long userId, Long roleId);

    /**
     * 查询未拥有此角色用户列表
     * @param vo
     * @return
     */
    IPage<User> selectUsersNotThisRole(RoleVo vo);

    /**
     * 存入获取此角色的用户集合
     * @param ids
     * @param roleId
     * @return
     */
    int insertUserRole(String ids, Long roleId);

    /**
     * 新增角色
     * @param roleVo
     * @return
     */
    int insertRole(RoleVo roleVo, User user) throws InvocationTargetException, IllegalAccessException;

    void deleteRoleById(Long id);
}
