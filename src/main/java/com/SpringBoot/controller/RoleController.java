package com.SpringBoot.controller;

import com.SpringBoot.bean.User;
import com.SpringBoot.service.RoleService;
import com.SpringBoot.service.impl.RoleServiceImpl;
import com.SpringBoot.vo.RoleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.SpringBoot.bean.Role;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	private final String prefix = "system/role/";


	/**
	 * 查询角色列表
	 * @param vo
	 * @return
	 */
	@PostMapping("/loadAllRole")
	@ResponseBody
	public LayuiJson<Role> loadAllRole(RoleVo vo){
		IPage<Role> page = roleService.selectRoleList(vo);
		LayuiJson<Role> layuiJson = new LayuiJson<>();
		layuiJson.setCode(0);
		layuiJson.setCount(page.getTotal());
		layuiJson.setData(page.getRecords());
		return layuiJson;

	 }

	@RequiresPermissions(value = "sys:role:view")
	 @GetMapping("/addRoleIndex")
	 public String addRoleIndex(HttpServletRequest request, Model mmap){
		 User user = (User) request.getSession().getAttribute("user");
		 mmap.addAttribute("userId", user.getId());
		 return prefix + "add";
	 }

	/**
	 * 新增角色
	 * @param roleVo
	 * @param request
	 * @return
	 */
	@RequiresPermissions(value = "sys:role:add")
	@RequestMapping("/addRole")
	@ResponseBody
    public ResultObj addRole(RoleVo roleVo, HttpServletRequest request) {
        try {
			User user = (User) request.getSession().getAttribute("user");
			int row =  roleService.insertRole(roleVo, user);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
	@RequiresPermissions(value = "sys:role:delete")
	@RequestMapping("/deleteRole")
	@ResponseBody
	public ResultObj deleteRole(Long id) {
        try {
        	roleService.deleteRoleById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

	@RequiresPermissions(value = "sys:role:view")
	@GetMapping("/editRoleIndex")
	public String editRoleIndex(Long id, Model mmp){
		Role role = roleService.getRoleById(id);
		mmp.addAttribute("role", role);
		return prefix + "edit";
	}

	@RequiresPermissions(value = "sys:role:edit")
	@RequestMapping("/updateRole")
	@ResponseBody
	public ResultObj updateRole(RoleVo roleVo, HttpServletRequest request) {
		try {
			// 设置更新人
			User user = (User) request.getSession().getAttribute("user");
			int row = roleService.updateRole(roleVo, user);
			return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
	}

	/**
	 * 跳转到给用户分配角色页面
	 * @param id
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions(value = "sys:role:view")
	@GetMapping("/getSetRoleIndex/{id}")
	public String getRoleDetailById(@PathVariable("id") Long id, Model mmap){
		mmap.addAttribute("roleId", id);
		return prefix + "setRole";
	}

	/**
	 * 角色id分页查询用户集合
	 */
	@RequiresPermissions(value = "sys:role:select")
	@GetMapping("/selectGiveUserRole/{id}")
	@ResponseBody
	public LayuiJson<User>  toGiveUserRoleIndex(@PathVariable("id") Long id){

		// 查询已有此角色的用户
		IPage<User> page = roleService.selectUserByRoleId(id);

		LayuiJson<User> layuiJson = new LayuiJson<>();
		layuiJson.setData(page.getRecords());
		layuiJson.setCount(page.getTotal());
		layuiJson.setCode(0);

		return layuiJson;

	}

	/**
	 * 删除用户与角色之间的联系
	 * @param userId
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions(value = "sys:role:delete")
	@PostMapping("/deleteRolesUser")
	@ResponseBody
	public ResultObj deleteRolesUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId){
		try {
			int row = roleService.deleteRolesUser(userId, roleId);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e){
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}

	}

	/**
	 * 跳转到查询为拥有此角色用户列表查询页面
	 * @param roleId
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions(value = "sys:role:view")
	@GetMapping("toUsersNotThisRoleIndex/{roleId}")
	public String selectUsersNotThisRoleIndex(@PathVariable("roleId") Long roleId, Model mmap){
		mmap.addAttribute("roleId", roleId);
		return prefix + "userList";

	}

	/**
	 * 查询未拥有此角色用户列表
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions(value = "sys:user:select")
	@GetMapping("selectUsersNotThisRole/{roleId}")
	@ResponseBody
	public LayuiJson<User> selectUsersNotThisRole(@PathVariable("roleId") Long roleId,
												  @RequestParam("pageNum") Long pageNum,
												  @RequestParam("pageSize") Long pageSize) {
		IPage<User> page = roleService.selectUsersNotThisRole(roleId, pageNum, pageSize);
		LayuiJson<User> layuiJson = new LayuiJson<>();
		layuiJson.setData(page.getRecords());
		layuiJson.setCount(page.getTotal());
		layuiJson.setCode(0);

		return layuiJson;
	}

	/**
	 * 存入获得此角色的用户集合
	 * @param ids
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions(value = "sys:user:add")
	@PostMapping("insertUserRole")
	@ResponseBody
	public ResultObj insertUserRole(String ids, Long roleId){
		try {
			int row = roleService.insertUserRole(ids, roleId);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e){
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}

	}
}