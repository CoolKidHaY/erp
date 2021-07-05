package com.SpringBoot.controller;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpProvider;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpProviderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采购商Controller
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
@Controller
@RequestMapping("/business/consumer")
public class ErpConsumerController
{
    private String prefix = "business/consumer";

    @Autowired
    private IErpProviderService erpProviderService;

//    @RequiresPermissions("business:consumer:view")
    @GetMapping()
    public String consumer()
    {
        return prefix + "/consumer";
    }

    /**
     * 查询供应商列表
     */
//    @RequiresPermissions("business:consumer:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson list(ErpProvider erpProvider)
    {
        Page<ErpProvider> page = erpProviderService.selectErpProviderList(erpProvider);
        return LayuiJson.success(page.getTotal(), page.getRecords());
    }

//    /**
//     * 导出供应商列表
//     */
//    @RequiresPermissions("business:consumer:export")
//    @Log(moduleName = "供应商", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public LayuiJson export(ErpProvider erpProvider)
//    {
//        List<ErpProvider> list = erpProviderService.selectErpProviderList(erpProvider);
//        ExcelUtil<ErpProvider> util = new ExcelUtil<ErpProvider>(ErpProvider.class);
//        return util.exportExcel(list, "consumer");
//    }

    /**
     * 新增供应商
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存供应商
     */
//    @RequiresPermissions("business:consumer:add")
    @Log(moduleName = "供应商", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpProvider erpProvider)
    {
        return LayuiJson.toAjax(erpProviderService.insertErpProvider(erpProvider));
    }

    /**
     * 修改供应商
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpProvider erpProvider = erpProviderService.selectErpProviderById(id);
        mmap.put("erpProvider", erpProvider);
        return prefix + "/edit";
    }

    /**
     * 修改保存供应商
     */
//    @RequiresPermissions("business:consumer:edit")
    @Log(moduleName = "供应商", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpProvider erpProvider)
    {
        return LayuiJson.toAjax(erpProviderService.updateErpProvider(erpProvider));
    }

    /**
     * 删除供应商
     */
//    @RequiresPermissions("business:consumer:remove")
    @Log(moduleName = "供应商", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpProviderService.deleteErpProviderByIds(ids));
    }
}
