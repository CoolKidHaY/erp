package com.SpringBoot.controller;

import java.util.List;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpFactoryMaterial;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpFactoryMaterialService;
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
 * 库存Controller
 * 
 * @author ruoyi
 * @date 2021-04-24
 */
@Controller
@RequestMapping("/business/stock")
public class ErpFactoryMaterialController
{
    private String prefix = "business/stock";

    @Autowired
    private IErpFactoryMaterialService erpFactoryMaterialService;

//    @RequiresPermissions("business:stock:view")
    @GetMapping()
    public String material()
    {
        return prefix + "/stock";
    }

    /**
     * 仓库菜单点击查看库存时跳转到库存表
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/getStock/{id}")
    public String getStockById(@PathVariable("id") Long id, Model mmap){
        mmap.addAttribute("fId", id);
        return prefix + "/stock";
    }

    /**
     * 查询库存列表
     */
//    @RequiresPermissions("business:stock:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson list(ErpFactoryMaterial erpFactoryMaterial)
    {
        List<ErpFactoryMaterial> list = erpFactoryMaterialService.selectErpFactoryMaterialList(erpFactoryMaterial);
        return LayuiJson.success((long) list.size(), list);
    }

//    /**
//     * 导出库存列表
//     */
//    @RequiresPermissions("business:stock:export")
//    @Log(title = "库存", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(ErpFactoryMaterial ErpFactoryMaterial)
//    {
//        List<ErpFactoryMaterial> list = ErpFactoryMaterialService.selectErpFactoryMaterialList(ErpFactoryMaterial);
//        ExcelUtil<ErpFactoryMaterial> util = new ExcelUtil<ErpFactoryMaterial>(ErpFactoryMaterial.class);
//        return util.exportExcel(list, "material");
//    }

    /**
     * 新增库存
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库存
     */
//    @RequiresPermissions("business:stock:add")
    @Log(moduleName = "库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpFactoryMaterial ErpFactoryMaterial)
    {
        return LayuiJson.toAjax(erpFactoryMaterialService.insertErpFactoryMaterial(ErpFactoryMaterial));
    }

    /**
     * 修改库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpFactoryMaterial ErpFactoryMaterial = erpFactoryMaterialService.selectErpFactoryMaterialById(id);
        mmap.put("ErpFactoryMaterial", ErpFactoryMaterial);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存
     */
//    @RequiresPermissions("business:stock:edit")
    @Log(moduleName = "库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpFactoryMaterial ErpFactoryMaterial)
    {
        return LayuiJson.toAjax(erpFactoryMaterialService.updateErpFactoryMaterial(ErpFactoryMaterial));
    }

    /**
     * 删除库存
     */
//    @RequiresPermissions("business:stock:remove")
    @Log(moduleName = "库存", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpFactoryMaterialService.deleteErpFactoryMaterialByIds(ids));
    }
}
