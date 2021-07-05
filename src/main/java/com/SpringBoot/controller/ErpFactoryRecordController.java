package com.SpringBoot.controller;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpFactoryRecord;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.dto.InOrOutFactoryDto;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpFactoryRecordService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * 仓库操作记录Controller
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
@Controller
@RequestMapping("/business/record")
public class ErpFactoryRecordController
{
    private String prefix = "business/record";

    @Autowired
    private IErpFactoryRecordService erpFactoryRecordService;

//    @RequiresPermissions("business:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询仓库操作记录列表
     */
//    @RequiresPermissions("business:record:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson list(ErpFactoryRecord erpFactoryRecord)
    {
        Page<ErpFactoryRecord> page = erpFactoryRecordService.selectErpFactoryRecordList(erpFactoryRecord);
        return LayuiJson.success(page.getTotal(), page.getRecords());
    }

//    /**
//     * 导出仓库操作记录列表
//     */
//    @RequiresPermissions("business:record:export")
//    @Log(title = "仓库操作记录", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(ErpFactoryRecord erpFactoryRecord)
//    {
//        List<ErpFactoryRecord> list = erpFactoryRecordService.selectErpFactoryRecordList(erpFactoryRecord);
//        ExcelUtil<ErpFactoryRecord> util = new ExcelUtil<ErpFactoryRecord>(ErpFactoryRecord.class);
//        return util.exportExcel(list, "record");
//    }

    /**
     * 新增仓库操作记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库操作记录
     */
//    @RequiresPermissions("business:record:add")
    @Log(moduleName = "仓库操作记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpFactoryRecord erpFactoryRecord)
    {
        return LayuiJson.toAjax(erpFactoryRecordService.insertErpFactoryRecord(erpFactoryRecord));
    }

    /**
     * 仓库操作记录详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        InOrOutFactoryDto record = erpFactoryRecordService.selectErpFactoryRecordDetailById(id);
        mmap.put("erpFactoryRecord", record);
        return prefix + "/detail";
    }

    /**
     * 修改仓库操作记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpFactoryRecord erpFactoryRecord = erpFactoryRecordService.selectErpFactoryRecordById(id);
        mmap.put("erpFactoryRecord", erpFactoryRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库操作记录
     */
//    @RequiresPermissions("business:record:edit")
    @Log(moduleName = "仓库操作记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpFactoryRecord erpFactoryRecord)
    {
        return LayuiJson.toAjax(erpFactoryRecordService.updateErpFactoryRecord(erpFactoryRecord));
    }

    /**
     * 删除仓库操作记录
     */
//    @RequiresPermissions("business:record:remove")
    @Log(moduleName = "仓库操作记录", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpFactoryRecordService.deleteErpFactoryRecordByIds(ids));
    }
}
