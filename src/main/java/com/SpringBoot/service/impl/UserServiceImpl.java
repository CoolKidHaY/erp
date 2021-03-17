package com.SpringBoot.service.impl;

import com.SpringBoot.bean.User;
import com.SpringBoot.config.ShiroConfig;
import com.SpringBoot.mapper.UserMapper;
import com.SpringBoot.service.UserService;
import com.SpringBoot.vo.PwdVo;
import com.SpringBoot.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 部门id查询用户集合
	 * @param deptId
	 * @return
	 */
	public List<User> selectUsersByDeptId(Long deptId) {
		return this.getBaseMapper().selectList(new QueryWrapper<User>().eq("dept_id", deptId));
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@Override
	public int addUser(User user) {
		return this.getBaseMapper().insert(user);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@Override
	public int updateUser(User user) {
		return this.getBaseMapper().updateById(user);
	}

	/**
	 * id删除用户
	 * @param id
	 * @return
	 */
	@Override
	public int deleteUserById(Long id) {
		return this.baseMapper.deleteById(id);
	}

	/**
	 * 登陆名查询用户
	 * @param username
	 * @return
	 */
	@Override
	public User selectUserByLoginName(String username) {
		return this.getBaseMapper().selectOne(new QueryWrapper<User>().eq("login_name", username));
	}

	@Override
	public User getUserById(Long id) {
		return this.getBaseMapper().selectById(id);
	}

	@Override
	public int changePassword(PwdVo vo) {
		User user = new User();
		user.setId(vo.getUserId());
		user.setPwd(vo.getOldPassword());
		// 查询是否有此用户
		User data = this.getBaseMapper().selectOne(new QueryWrapper<User>().eq("id", user.getId()));
		if (data == null){
			return 0;
		}
		// 判断密码是否正确
		SimpleHash oldHash = new SimpleHash("MD5", vo.getOldPassword(), data.getSalt(), 5);
		if (!oldHash.toHex().equals(data.getPwd())){
			return 0;
		}
		// 创建salt
		String salt = ShiroConfig.randomSalt();
		SimpleHash md5 = new SimpleHash("MD5", vo.getNewPassword(), salt, 5);
		// 设置密码
		user.setPwd(md5.toHex());
		user.setSalt(salt);
		// 更新密码
		return this.getBaseMapper().updateById(user);
	}


	/**
	 * 查询用户集合
	 * @param vo
	 * @return
	 */
	@Override
	public IPage<User> selectUserList(UserVo vo) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(vo.getDeptId() != null, "dept_id", vo.getDeptId())
				.like(vo.getName() != null, "name", vo.getName())
				.like(vo.getAddress() != null, "address", vo.getAddress())
				.orderByAsc("order_num");
		return this.baseMapper.selectPage(new Page<>(vo.getPageNum(), vo.getPageSize()), queryWrapper);
	}
}
