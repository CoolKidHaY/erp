package com.SpringBoot.controller;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpProduce;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpProduceService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Controller
@RequestMapping("/business/produce")
public class ErpProduceController
{
    private String prefix = "business/produce";

    @Autowired
    private IErpProduceService erpProduceService;

//    @RequiresPermissions("business:produce:view")
    @GetMapping()
    public String produce()
    {
        return prefix + "/produce";
    }

    /**
     * 查询产品列表
     */
//    @RequiresPermissions("business:produce:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson<List<ErpProduce>> list(ErpProduce erpProduce)
    {
        Page<ErpProduce> page = erpProduceService.selectErpProduceList(erpProduce);
        return LayuiJson.success(page.getTotal(), page.getRecords());
    }

//    /**
//     * 导出产品列表
//     */
//    @RequiresPermissions("business:produce:export")
//    @Log(moduleName = "产品", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public LayuiJson export(ErpProduce erpProduce)
//    {
//        List<ErpProduce> list = erpProduceService.selectErpProduceList(erpProduce);
//        ExcelUtil<ErpProduce> util = new ExcelUtil<ErpProduce>(ErpProduce.class);
//        return util.exportExcel(list, "produce");
//    }

    /**
     * 新增产品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品
     */
//    @RequiresPermissions("business:produce:add")
    @Log(moduleName = "产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpProduce erpProduce)
    {
        return LayuiJson.toAjax(erpProduceService.insertErpProduce(erpProduce));
    }

    /**
     * 修改产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpProduce erpProduce = erpProduceService.selectErpProduceById(id);
        mmap.put("erpProduce", erpProduce);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品
     */
//    @RequiresPermissions("business:produce:edit")
    @Log(moduleName = "产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpProduce erpProduce)
    {
        return LayuiJson.toAjax(erpProduceService.updateErpProduce(erpProduce));
    }

    /**
     * 删除产品
     */
//    @RequiresPermissions("business:produce:remove")
    @Log(moduleName = "产品", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpProduceService.deleteErpProduceByIds(ids));
    }
}
