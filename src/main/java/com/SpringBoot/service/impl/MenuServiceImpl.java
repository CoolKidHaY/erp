package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Menu;
import com.SpringBoot.bean.RoleMenu;
import com.SpringBoot.bean.User;
import com.SpringBoot.mapper.MenuMapper;
import com.SpringBoot.mapper.RoleMapper;
import com.SpringBoot.mapper.RoleMenuMapper;
import com.SpringBoot.service.MenuService;
import com.SpringBoot.service.RoleService;
import com.SpringBoot.utils.Constants;
import com.SpringBoot.utils.IdWorker;
import com.SpringBoot.vo.MenuVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleMenuMapper roleMenuMapper;


	/**
	 * 查询目录级别和菜单级别的菜单
	 * @return
	 */
	@Override
	public List<Menu> selectMenu() {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>()
				.eq("visible", 0)
				.and(wrapper -> wrapper.eq("type", Constants.MENU_TYPE_M).or().eq("type", Constants.MENU_TYPE_C))
				.orderByAsc("order_num");
		List<Menu> menus = baseMapper.selectList(queryWrapper);
		return menus;

	}

	/**
	 * 新增菜单
	 * @param menu
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addMenu(Menu menu, User user) {
		// 保存菜单
		menu.setCreateTime(LocalDateTime.now());
		int row = this.getBaseMapper().insert(menu);

		// 将菜单权限赋予给超级管理员
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setMid(menu.getId());
		roleMenu.setRid(Constants.ADMIN_ROLE_ID);

		return roleMapper.batchInsertRoleMenu(Collections.singletonList(roleMenu));
	}

	/**
	 * 用户id查询权限字符
	 * @param id
	 * @return
	 */
	@Override
	public Set<String> selectPerCodesByUserId(Long id) {
		return menuMapper.selectPerCodesByUserId(id);
	}

	/**
	 * 查询此角色已有的menus
	 * @param id
	 * @return
	 */
	@Override
	public List<Menu> selectRoleHasMenus(Long id) {
		List<Menu> list = menuMapper.getRoleHasMenus(id);
		return list;
	}

	/**
	 * 用户id查询菜单
	 * @param id
	 * @return
	 */
	@Override
	public List<Menu> selectUserHasMenus(Long id) {
		return menuMapper.getUserHasMenus(id);
	}

	/**
	 * 根据用户id查询用户角色
	 * @param id
	 * @return
	 */
	@Override
	public Set<String> selectRolesByUid(Long id) {
		return menuMapper.selectRolesByUid(id);
	}

	/**
	 * 查询此菜单是否有子菜单
	 * @param id
	 * @return
	 */
	@Override
	public int selectMenuSonById(Long id) {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("pid", id);
		return this.count(queryWrapper);

	}

	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteMenuById(Long id) {
		// 删除菜单
		int i = this.getBaseMapper().deleteById(id);
		// 删除角色与菜单联系
		QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("mid", id);
		return roleMenuMapper.delete(queryWrapper);
	}

	/**
	 * 名称查询菜单
	 * @param vo
	 * @return
	 */
	@Override
	public List<Menu> selectMenuByTitle(MenuVo vo) {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>()
				.like(StringUtils.isNotBlank(vo.getTitle()), "title", vo.getTitle())
				.eq("delete_flag", Constants.DELETE_FLAG_EXIST)
				.orderByAsc("order_num");
		return menuMapper.selectList(queryWrapper);
	}

	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 */
	@Override
	public int updateMenu(Menu menu) {
		return this.getBaseMapper().updateById(menu);
	}
	
}
