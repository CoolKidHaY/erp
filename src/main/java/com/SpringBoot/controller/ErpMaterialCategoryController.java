package com.SpringBoot.controller;

import java.util.List;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpMaterialCategory;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpMaterialCategoryService;
import com.SpringBoot.utils.Ztree;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 材料类别Controller
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Controller
@RequestMapping("/business/category")
public class ErpMaterialCategoryController
{
    private String prefix = "business/category";

    @Autowired
    private IErpMaterialCategoryService erpMaterialCategoryService;

//    @RequiresPermissions("business:category:view")
    @GetMapping()
    public String category()
    {
        return prefix + "/category";
    }

    /**
     * 查询材料类别树列表
     */
//    @RequiresPermissions("business:category:list")
    @PostMapping("/list")
    @ResponseBody
    public List<ErpMaterialCategory> list(ErpMaterialCategory erpMaterialCategory)
    {
        List<ErpMaterialCategory> list = erpMaterialCategoryService.selectErpMaterialCategoryList(erpMaterialCategory);
        return list;
    }

//    /**
//     * 导出材料类别列表
//     */
//    @RequiresPermissions("business:category:export")
//    @Log(title = "材料类别", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(ErpMaterialCategory erpMaterialCategory)
//    {
//        List<ErpMaterialCategory> list = erpMaterialCategoryService.selectErpMaterialCategoryList(erpMaterialCategory);
//        ExcelUtil<ErpMaterialCategory> util = new ExcelUtil<ErpMaterialCategory>(ErpMaterialCategory.class);
//        return util.exportExcel(list, "category");
//    }

    /**
     * 新增材料类别
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (id != null && StringUtils.isNotBlank(id.toString()))
        {
            mmap.put("erpMaterialCategory", erpMaterialCategoryService.selectErpMaterialCategoryById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存材料类别
     */
//    @RequiresPermissions("business:category:add")
    @Log(moduleName = "材料类别", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpMaterialCategory erpMaterialCategory)
    {
        return LayuiJson.toAjax(erpMaterialCategoryService.insertErpMaterialCategory(erpMaterialCategory));
    }

    /**
     * 修改材料类别
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpMaterialCategory erpMaterialCategory = erpMaterialCategoryService.selectErpMaterialCategoryById(id);
        mmap.put("erpMaterialCategory", erpMaterialCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存材料类别
     */
//    @RequiresPermissions("business:category:edit")
    @Log(moduleName = "材料类别", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpMaterialCategory erpMaterialCategory)
    {
        return LayuiJson.toAjax(erpMaterialCategoryService.updateErpMaterialCategory(erpMaterialCategory));
    }

    /**
     * 删除
     */
//    @RequiresPermissions("business:category:remove")
    @Log(moduleName = "材料类别", businessType = BusinessType.REMOVE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public LayuiJson remove(@PathVariable("id") Long id)
    {
        return LayuiJson.toAjax(erpMaterialCategoryService.deleteErpMaterialCategoryById(id));
    }

    /**
     * 选择材料类别树
     */
    @GetMapping(value = { "/selectCategoryTree/{id}", "/selectCategoryTree/" })
    public String selectCategoryTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (id != null && StringUtils.isNotBlank(id.toString()))
        {
            mmap.put("erpMaterialCategory", erpMaterialCategoryService.selectErpMaterialCategoryById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载材料类别树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = erpMaterialCategoryService.selectErpMaterialCategoryTree();
        return ztrees;
    }

    /**
     * id查询种类名称
     * @param id
     * @return
     */
    @GetMapping("getNameById/{id}")
    @ResponseBody
    public String getNameById(@PathVariable("id") Long id){
        return erpMaterialCategoryService.getNameById(id);
    }
}
