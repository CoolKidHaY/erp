package com.SpringBoot.mapper;

import com.SpringBoot.bean.User;
import com.SpringBoot.vo.RoleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	public User select(String loginName);

	/**
	 * 角色id分页查询用户
	 * @param userPage
	 * @param role
	 * @return
	 */
    IPage<User> selectUserByRoleId(Page<User> userPage, RoleVo role);
}
