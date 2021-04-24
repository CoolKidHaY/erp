package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Role;
import com.SpringBoot.bean.RoleMenu;
import com.SpringBoot.bean.User;
import com.SpringBoot.bean.UserRole;
import com.SpringBoot.mapper.RoleMapper;
import com.SpringBoot.mapper.UserMapper;
import com.SpringBoot.mapper.UserRoleMapper;
import com.SpringBoot.service.RoleService;
import com.SpringBoot.utils.Convert;
import com.SpringBoot.vo.RoleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;


	/**
	 * 查询角色列表
	 * @param vo
	 * @return
	 */
	@Override
	public IPage<Role> selectRoleList(RoleVo vo) {
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(vo.getName()), "name", vo.getName())
				.like(StringUtils.isNotBlank(vo.getRemark()), "remark", vo.getRemark())
				.eq("available", 1);
		Page<Role> page = this.baseMapper.selectPage(new Page<>(vo.getPageNum(), vo.getPageSize()), queryWrapper);
		return page;
	}

	@Transactional(rollbackFor = Exception.class)
	public int updateRole(RoleVo roleVo, User user) {
		// 设置更新时间
		LocalDateTime updateTime = LocalDateTime.now();
		roleVo.setUpdateBy(user.getName());
		roleVo.setUpdateTime(updateTime);

		// 更新角色
		baseMapper.updateById(roleVo);

		// 删除以前的角色-菜单对应关系
		roleMapper.deleteRoleAndMenuByRoleId(roleVo.getId());

		// 新增现有关系
		List roleMenuList = new ArrayList<RoleMenu>();
		for (Long menuId : roleVo.getMenuIds()) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRid(roleVo.getId());
			roleMenu.setMid(menuId);
			roleMenuList.add(roleMenu);
		}
		int row = roleMapper.batchInsertRoleMenu(roleMenuList);
		return row;
	}

	/**
	 * 角色id查询用户
	 * @param vo
	 * @return
	 */
	@Override
	public IPage<User> selectUserByRoleId(RoleVo vo) {
		IPage<User> roleIPage = userMapper.selectUserByRoleId(new Page<User>(vo.getPageNum(),vo.getPageSize()), vo);
		return roleIPage;
	}

	/**
	 * 删除用户与角色之间的联系
	 * @param userId
	 * @param roleId
	 * @return
	 */
	@Override
	public int deleteRolesUser(Long userId, Long roleId) {

		int row = roleMapper.deleteRolesUser(userId, roleId);

		return 0;
	}

	/**
	 * 查询为拥有此角色用户列表
	 * @param vo
	 * @return
	 */
	@Override
	public IPage<User> selectUsersNotThisRole(RoleVo vo) {
		return roleMapper.selectUsersNotThisRole(new Page<User>(vo.getPageNum(), vo.getPageSize()), vo);
	}

	/**
	 * 存入获得此角色的用户集合
	 * @param ids
	 * @param roleId
	 * @return
	 */
	@Override
	public int insertUserRole(String ids, Long roleId) {
		Long[] userIds = Convert.toLongArray(",", ids);
		ArrayList<UserRole> users = new ArrayList<>();
		for (Long userId : userIds) {
			UserRole userRole = new UserRole();
			userRole.setUid(userId);
			userRole.setRid(roleId);
			users.add(userRole);
		}
		return userRoleMapper.batchInsertUserRole(users);
	}

	/**
	 * 新增角色
	 * @paramr roleVo
	 * @param user
	 * @return
	 */
	@Override
	@Transactional
	public int insertRole(RoleVo roleVo, User user) {
		LocalDateTime createTime = LocalDateTime.now();
		roleVo.setCreateBy(user.getName());
		roleVo.setCreateTime(createTime);
		roleVo.setUpdateBy(user.getName());
		roleVo.setUpdateTime(createTime);
		Role role = new Role();
		try {
			BeanUtils.copyProperties(role, roleVo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		this.getBaseMapper().insert(role);

		// 新增关系
		List roleMenuList = new ArrayList<RoleMenu>();
		List<Long> menuIds = roleVo.getMenuIds();
		for (Long menuId : menuIds) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRid(role.getId());
			roleMenu.setMid(menuId);
			roleMenuList.add(roleMenu);
		}
		int row = roleMapper.batchInsertRoleMenu(roleMenuList);
		return row;
	}

	@Override
	public void deleteRoleById(Long id) {
		this.baseMapper.deleteById(id);
	}


	/**
	 * 根据id查询role角色
	 * @param id
	 * @return
	 */
	@Override
	public Role getRoleById(Long id) {
		return this.baseMapper.selectById(id);
	}

}
