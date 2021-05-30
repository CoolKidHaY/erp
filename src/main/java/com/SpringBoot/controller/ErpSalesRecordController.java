package com.SpringBoot.controller;

import java.util.List;

import cn.hutool.poi.excel.ExcelUtil;
import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpProduceRecord;
import com.SpringBoot.bean.ErpSalesRecord;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpSalesRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 销售记录Controller
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Controller
@RequestMapping("/business/sales")
public class ErpSalesRecordController
{
    private String prefix = "business/sales";

    @Autowired
    private IErpSalesRecordService erpSalesRecordService;

    @RequiresPermissions("business:sales:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询销售记录列表
     */
    @RequiresPermissions("business:sales:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson list(ErpSalesRecord erpSalesRecord)
    {
        List<ErpSalesRecord> list = erpSalesRecordService.selectErpSalesRecordList(erpSalesRecord);
        return LayuiJson.success((long) list.size(), list);
    }

//    /**
//     * 导出销售记录列表
//     */
//    @RequiresPermissions("business:sales:export")
//    @Log(moduleName = "销售记录", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public LayuiJson export(ErpSalesRecord erpSalesRecord)
//    {
//        List<ErpSalesRecord> list = erpSalesRecordService.selectErpSalesRecordList(erpSalesRecord);
//        ExcelUtil util = new ExcelUtil<ErpSalesRecord>(ErpSalesRecord.class);
//        return util.exportExcel(list, "record");
//    }

    /**
     * 新增销售记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存销售记录
     */
    @RequiresPermissions("business:sales:add")
    @Log(moduleName = "销售记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpSalesRecord erpSalesRecord)
    {
        return LayuiJson.toAjax(erpSalesRecordService.insertErpSalesRecord(erpSalesRecord));
    }

    /**
     * 修改销售记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpSalesRecord erpSalesRecord = erpSalesRecordService.selectErpSalesRecordById(id);
        mmap.put("erpSalesRecord", erpSalesRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售记录
     */
    @RequiresPermissions("business:sales:edit")
    @Log(moduleName = "销售记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpSalesRecord erpSalesRecord)
    {
        return LayuiJson.toAjax(erpSalesRecordService.updateErpSalesRecord(erpSalesRecord));
    }

    /**
     * 删除销售记录
     */
    @RequiresPermissions("business:sales:remove")
    @Log(moduleName = "销售记录", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpSalesRecordService.deleteErpSalesRecordByIds(ids));
    }

    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id, Model mmap){
        ErpSalesRecord erpSalesRecord = erpSalesRecordService.getById(id);
        mmap.addAttribute("erpSalesRecord", erpSalesRecord);
        return prefix + "/check";
    }

    /**
     * 保存审核
     * @param erpSalesRecord
     * @return
     */
    @PostMapping("/check")
    @ResponseBody
    public LayuiJson check(ErpSalesRecord erpSalesRecord){
        return LayuiJson.toAjax(erpSalesRecordService.updateErpSalesRecord(erpSalesRecord));
    }

    /**
     * 改变状态
     * @param erpSalesRecord
     * @return
     */
    @PostMapping("/sales")
    @ResponseBody
    public LayuiJson produce(ErpSalesRecord erpSalesRecord){
        return LayuiJson.toAjax(erpSalesRecordService.updateErpSalesRecord(erpSalesRecord));
    }
}
