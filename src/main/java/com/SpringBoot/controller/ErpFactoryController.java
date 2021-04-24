package com.SpringBoot.controller;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpFactory;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpFactoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 仓库Controller
 * 
 * @author ruoyi
 * @date 2021-04-24
 */
@Controller
@RequestMapping("/business/factory")
public class ErpFactoryController
{
    private String prefix = "business/factory";

    @Autowired
    private IErpFactoryService erpFactoryService;

//    @RequiresPermissions("business:factory:view")
    @GetMapping()
    public String factory()
    {
        return prefix + "/factory";
    }

    /**
     * 查询仓库列表
     */
//    @RequiresPermissions("business:factory:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson<List> list(ErpFactory erpFactory)
    {
        List<ErpFactory> list = erpFactoryService.selectErpFactoryList(erpFactory);
        return LayuiJson.success((long) list.size(), list);
    }

//    /**
//     * 导出仓库列表
//     */
//    @RequiresPermissions("business:factory:export")
//    @Log(title = "仓库", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(ErpFactory erpFactory)
//    {
//        List<ErpFactory> list = erpFactoryService.selectErpFactoryList(erpFactory);
//        ExcelUtil<ErpFactory> util = new ExcelUtil<ErpFactory>(ErpFactory.class);
//        return util.exportExcel(list, "factory");
//    }

    /**
     * 新增仓库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库
     */
//    @RequiresPermissions("business:factory:add")
    @Log(moduleName = "仓库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpFactory erpFactory)
    {
        return LayuiJson.toAjax(erpFactoryService.insertErpFactory(erpFactory));
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public ErpFactory detail(@PathVariable("id") Long id){
        return erpFactoryService.getById(id);
    }

    /**
     * 修改仓库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpFactory erpFactory = erpFactoryService.selectErpFactoryById(id);
        mmap.put("erpFactory", erpFactory);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库
     */
//    @RequiresPermissions("business:factory:edit")
    @Log(moduleName = "仓库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpFactory erpFactory)
    {
        return LayuiJson.toAjax(erpFactoryService.updateErpFactory(erpFactory));
    }

    /**
     * 删除仓库
     */
//    @RequiresPermissions("business:factory:remove")
    @Log(moduleName = "仓库", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpFactoryService.deleteErpFactoryByIds(ids));
    }
}
