package com.SpringBoot.controller;

import java.util.List;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpFactoryMaterial;
import com.SpringBoot.bean.ErpMaterial;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpFactoryMaterialService;
import com.SpringBoot.service.IErpMaterialService;
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
 * 材料Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Controller
@RequestMapping("/business/material")
public class ErpMaterialController
{
    private String prefix = "business/material";

    @Autowired
    private IErpMaterialService erpMaterialService;

    @Autowired
    private IErpFactoryMaterialService erpFactoryMaterialService;

//    @RequiresPermissions("business:material:view")
    @GetMapping()
    public String material()
    {
        return prefix + "/material";
    }

    /**
     * 查询材料列表
     */
//    @RequiresPermissions("business:material:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson<List<ErpMaterial>> list(ErpMaterial erpMaterial)
    {
        List<ErpMaterial> list = erpMaterialService.selectErpMaterialList(erpMaterial);
        return LayuiJson.success((long) list.size(),list);
    }

//    /**
//     * 导出材料列表
//     */
//    @RequiresPermissions("business:material:export")
//    @Log(moduleName = "材料", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public LayuiJson export(ErpMaterial erpMaterial)
//    {
//        List<ErpMaterial> list = erpMaterialService.selectErpMaterialList(erpMaterial);
//        ExcelUtil<ErpMaterial> util = new ExcelUtil<ErpMaterial>(ErpMaterial.class);
//        return util.exportExcel(list, "material");
//    }

    /**
     * 新增材料
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存材料
     */
//    @RequiresPermissions("business:material:add")
    @Log(moduleName = "材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpMaterial erpMaterial)
    {
        return LayuiJson.toAjax(erpMaterialService.insertErpMaterial(erpMaterial));
    }

    /**
     * 修改材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpMaterial erpMaterial = erpMaterialService.selectErpMaterialById(id);
        mmap.put("erpMaterial", erpMaterial);
        return prefix + "/edit";
    }

    /**
     * 修改保存材料
     */
//    @RequiresPermissions("business:material:edit")
    @Log(moduleName = "材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpMaterial erpMaterial)
    {
        return LayuiJson.toAjax(erpMaterialService.updateErpMaterial(erpMaterial));
    }

    /**
     * 删除材料
     */
//    @RequiresPermissions("business:material:remove")
    @Log(moduleName = "材料", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpMaterialService.deleteErpMaterialByIds(ids));
    }


    /**
     * 查询材料库存
     * @param id
     * @return
     */
    @GetMapping("/stock/{id}")
    public String selectStock(@PathVariable("id") Long id, Model mmap){
//        List<ErpFactoryMaterial> list = erpFactoryMaterialService.selectStockByMid(id);
        mmap.addAttribute("mId", id);
        return "/stock";
    }

}
