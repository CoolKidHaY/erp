package com.SpringBoot.mapper;

import com.SpringBoot.bean.Role;
import com.SpringBoot.bean.RoleMenu;
import com.SpringBoot.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 删除角色与菜单之间的关系
	 * @param id
	 */
    void deleteRoleAndMenuByRoleId(Long id);

	/**
	 * 新增角色与菜单之间的关系
	 * @param roleMenuList
	 * @return
	 */
	int batchInsertRoleMenu(List<RoleMenu> roleMenuList);

	/**
	 * 删除用户与角色之间的联系
	 * @param userId
	 * @param roleId
	 * @return
	 */
    int deleteRolesUser(Long userId, Long roleId);

	/**
	 * 查询为拥有此角色用户列表
	 * @param roleId
	 * @return
	 */
    IPage<User> selectUsersNotThisRole(IPage page, Long roleId);
}
