package com.SpringBoot.service;

import com.SpringBoot.vo.PwdVo;
import com.SpringBoot.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.SpringBoot.bean.User;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * 查询用户集合
     * @param vo
     * @return
     */
    IPage<User> selectUserList(UserVo vo);

    /**
     * 部门id查询用户列表
     * @param deptId
     * @return
     */
    List<User> selectUsersByDeptId(Long deptId);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * id删除用户
     * @param id
     * @return
     */
    int deleteUserById(Long id);

    /**
     * 登陆名查询用户
     * @param username
     * @return
     */
    User selectUserByLoginName(String username);

    User getUserById(Long id);

    /**
     * 修改密码
     * @param vo
     * @return
     */
    int changePassword(PwdVo vo);
}
