package com.SpringBoot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.SpringBoot.annotation.Log;
import com.SpringBoot.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.SpringBoot.bean.Dept;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.common.TreeNode;
import com.SpringBoot.service.DeptService;

@Controller
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 加载部门左边的菜单树
     * @param 
     * @return
     */
	@RequestMapping("loadDeptManagerLeftTreeJson")
    @ResponseBody
    public DataGridView loadManagerLeftTreeJson(){
        //查询出所有的部门，存放进list中
        List<Dept> list = deptService.selectAllDept();

        List<TreeNode> treeNodes = new ArrayList<>();
        //将部门放入treeNodes中，组装成json
        for (Dept dept : list) {
            treeNodes.add(TreeNode.getTreeNode(dept));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询所有部门数据
     * @param 
     * @return
     */
    ///dept/loadAllDept
    @RequestMapping("loadAllDept")
    @ResponseBody
    public LayuiJson<Dept> select(Dept dept){
        List<Dept> data = deptService.select(dept);
        LayuiJson<Dept> resultData = new LayuiJson<>(data, (long) data.size(), 0);
        return resultData;
	}

    /**
     * 添加部门
     * @param 
     * @return
     */
    @RequestMapping("addDept")
    @ResponseBody
    public ResultObj addDept(Dept dept){
        try {
            int row = deptService.addDept(dept);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 更新部门
     * @param 
     * @return
     */
    @RequestMapping("updateDept")
    @ResponseBody
    public ResultObj updateDept(Dept dept){
        try {
            int row = deptService.updateDept(dept);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 检查当前部门是否有子部门
     * @param 
     * @return
     */
    @RequestMapping("checkDeptHasChildrenNode")
    @ResponseBody
    public Map<String, Boolean> checkDeptHasChildrenNode(Long id){
    	Map<String, Boolean> map = new HashMap<String, Boolean>();
    	Dept dept = deptService.selectDeptByPId(id);
         if (ObjectUtil.isNotNull(dept)) {
             map.put("value", true);
         } else {
             map.put("value", false);
         }
         return map;
    }

    /**
     * 删除部门
     * @param 
     * @return
     */
    @RequestMapping("deleteDept")
    @ResponseBody
    @Log(moduleName = "部门管理", businessType = BusinessType.REMOVE)
    public ResultObj deleteDept(Long id){
        try {
            int row = deptService.deleteDeptById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @PostMapping("/getFatherById")
    @ResponseBody
    public Dept getFatherById(Long id){
        return deptService.getFatherById(id);
    }

}

