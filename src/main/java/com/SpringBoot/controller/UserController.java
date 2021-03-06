package com.SpringBoot.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.SpringBoot.annotation.Log;
import com.SpringBoot.config.ShiroConfig;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.impl.UserServiceImpl;
import com.SpringBoot.utils.IdWorker;
import com.SpringBoot.vo.PwdVo;
import com.SpringBoot.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.bean.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    @Value("${upload.file.location}")
    private String uploadImageLocation;
	
	@Autowired
	private UserServiceImpl userService;

	private final String prefix = "system/user/";


    @GetMapping("/addUserIndex")
    public String addUserIndex(){
        return prefix + "add";
    }

    @GetMapping("/updateUserIndex/{id}")
    public String updateUserIndex(@PathVariable("id") Long id, Model mmap){
        User user = userService.getUserById(id);
        mmap.addAttribute("user", user);
        return prefix + "edit";
    }

    @GetMapping("/changePwdIndex")
    public String changePwdIndex(){
        return prefix + "changePassword";
    }

    /**
     * 用户全查询
     */
    @PostMapping("/selectUserList")
    @ResponseBody
    public LayuiJson<User> select(UserVo vo){
        IPage<User> page = userService.selectUserList(vo);
		return new LayuiJson<>(page.getRecords(), page.getTotal(), 0);
        
    }

    /**
     * 根据部门ID查询用户
     */
    @PostMapping("loadUsersByDeptId")
    @ResponseBody
    public List<User> selectUsersByDeptId(Long deptId) {
    	List<User> list = userService.selectUsersByDeptId(deptId);
        return list;
    }

    /**
     * 添加用户
     */
    @PostMapping("addUser")
    @ResponseBody
    public ResultObj addUser(User user) {
        try {
            user.setDeptName(user.getDeptTree_select_input());
            int row = userService.addUser(user);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("updateUser")
    @ResponseBody
    public ResultObj updateUser(User user) {
        try {
            user.setDeptName(user.getDeptTree_select_input());
            int row = userService.updateUser(user);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    @Log(moduleName = "用户模块", businessType = BusinessType.REMOVE)
    public ResultObj deleteUser(@PathVariable("id") Long id) {
        try {
            int row = userService.deleteUserById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public ResultObj changePassword(PwdVo vo, HttpServletRequest request){
        if (!vo.getNewPassword().equals(vo.getConfirmPassword()))
            return new ResultObj(200, "两次输入的密码不一致！");
        User user = (User) request.getSession().getAttribute("user");
        vo.setUserId(user.getId());
        int row = userService.changePassword(vo);
        if (row == 1){
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("type");
            request.getSession().removeAttribute("username");
            return ResultObj.UPDATE_SUCCESS;
        } else if (row == 0){
            return new ResultObj(200, "用户名不存在，或密码不正确！");
        } else
            return ResultObj.UPDATE_ERROR;
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public LayuiJson upload(MultipartFile file, Model mmap){
        if (file.isEmpty()){
            return LayuiJson.error("上传文件不能为空！");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = uploadImageLocation; // 上传后的路径
        String finalName = StrUtil.uuid().replace("-", "") +  suffixName; // 新文件名
        File dest = new File(filePath + finalName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return LayuiJson.error("保存失败！");
        }
        return LayuiJson.success(finalName);
    }

}
