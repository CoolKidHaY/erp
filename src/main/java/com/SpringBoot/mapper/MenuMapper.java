package com.SpringBoot.mapper;

import com.SpringBoot.bean.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
	/**
	 * 用户id查询权限字符
	 * @param id
	 * @return
	 */
    Set<String> selectPerCodesByUserId(@Param("id") Long id);

	/**
	 * 角色id查询菜单
	 * @param id
	 * @return
	 */
	List<Menu> getRoleHasMenus(@Param("id") Long id);

	/**
	 * 用户id查询菜单
	 * @param id
	 * @return
	 */
	List<Menu> getUserHasMenus(@Param("id") Long id);

	/**
	 * 根据用户id查询角色
	 * @param id
	 * @return
	 */
    Set<String> selectRolesByUid(@Param("id") Long id);

	/**
	 * 保存菜单，并返回其主键id
	 * @param menu
	 * @return
	 */
	Long insertAndGetId(@Param("menu") Menu menu);
}
