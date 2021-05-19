package com.SpringBoot.controller;

import java.util.List;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpProduceRecord;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpProduceRecordService;
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
 * 生产记录Controller
 * 
 * @author ruoyi
 * @date 2021-05-15
 */
@Controller
@RequestMapping("/business/produceRecord")
public class ErpProduceRecordController
{
    private String prefix = "business/produceRecord";

    @Autowired
    private IErpProduceRecordService erpProduceRecordService;

//    @RequiresPermissions("system:produceRecord:view")
    @GetMapping()
    public String produceRecord()
    {
        return prefix + "/record";
    }

    /**
     * 查询生产记录列表
     */
//    @RequiresPermissions("system:produceRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson list(ErpProduceRecord erpProduceRecord)
    {
        List<ErpProduceRecord> list = erpProduceRecordService.selectErpProduceRecordList(erpProduceRecord);
        return LayuiJson.success((long) list.size(), list);
    }

//    /**
//     * 导出生产记录列表
//     */
//    @RequiresPermissions("system:produceRecord:export")
//    @Log(title = "生产记录", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(ErpProduceRecord erpProduceRecord)
//    {
//        List<ErpProduceRecord> list = erpProduceRecordService.selectErpProduceRecordList(erpProduceRecord);
//        ExcelUtil<ErpProduceRecord> util = new ExcelUtil<ErpProduceRecord>(ErpProduceRecord.class);
//        return util.exportExcel(list, "produceRecord");
//    }

    /**
     * 新增生产记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产记录
     */
//    @RequiresPermissions("system:produceRecord:add")
    @Log( moduleName = "生产记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpProduceRecord erpProduceRecord)
    {
        return LayuiJson.toAjax(erpProduceRecordService.insertErpProduceRecord(erpProduceRecord));
    }

    /**
     * 修改生产记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpProduceRecord erpProduceRecord = erpProduceRecordService.selectErpProduceRecordById(id);
        mmap.put("erpProduceRecord", erpProduceRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产记录
     */
//    @RequiresPermissions("system:produceRecord:edit")
    @Log(moduleName = "生产记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpProduceRecord erpProduceRecord)
    {
        return LayuiJson.toAjax(erpProduceRecordService.updateErpProduceRecord(erpProduceRecord));
    }

    /**
     * 删除生产记录
     */
//    @RequiresPermissions("system:produceRecord:remove")
    @Log(moduleName = "生产记录", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpProduceRecordService.deleteErpProduceRecordByIds(ids));
    }

    @GetMapping("/produceList")
    public String produceList(){
        return prefix + "/produceForm";
    }

    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id, Model mmap){
        ErpProduceRecord erpProduceRecord = erpProduceRecordService.getById(id);
        mmap.addAttribute("erpProduceRecord", erpProduceRecord);
        return prefix + "/check";
    }

    /**
     * 保存审核
     * @param erpProduceRecord
     * @return
     */
    @PostMapping("/check")
    @ResponseBody
    public LayuiJson check(ErpProduceRecord erpProduceRecord){
        return LayuiJson.toAjax(erpProduceRecordService.insertErpProduceRecord(erpProduceRecord));
    }
}
