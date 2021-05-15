package com.SpringBoot.controller;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpProduceCategory;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpProduceCategoryService;
import com.SpringBoot.utils.Ztree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品类别Controller
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Controller
@RequestMapping("/business/kind")
public class ErpProduceCategoryController
{
    private String prefix = "business/kind";

    @Autowired
    private IErpProduceCategoryService erpProduceCategoryService;

//    @RequiresPermissions("business:kind:view")
    @GetMapping()
    public String kind()
    {
        return prefix + "/kind";
    }

    /**
     * 查询产品类别树列表
     */
//    @RequiresPermissions("business:kind:list")
    @PostMapping("/list")
    @ResponseBody
    public List<ErpProduceCategory> list(ErpProduceCategory erpProduceCategory)
    {
        List<ErpProduceCategory> list = erpProduceCategoryService.selectErpProduceCategoryList(erpProduceCategory);
        return list;
    }

//    /**
//     * 导出产品类别列表
//     */
//    @RequiresPermissions("business:kind:export")
//    @Log(title = "产品类别", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(ErpProduceCategory erpProduceCategory)
//    {
//        List<ErpProduceCategory> list = erpProduceCategoryService.selectErpProduceCategoryList(erpProduceCategory);
//        ExcelUtil<ErpProduceCategory> util = new ExcelUtil<ErpProduceCategory>(ErpProduceCategory.class);
//        return util.exportExcel(list, "kind");
//    }

    /**
     * 新增产品类别
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (id != null && StringUtils.isNotBlank(id.toString()))
        {
            mmap.put("erpProduceCategory", erpProduceCategoryService.selectErpProduceCategoryById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存产品类别
     */
//    @RequiresPermissions("business:kind:add")
    @Log(moduleName = "产品类别", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(ErpProduceCategory erpProduceCategory)
    {
        return LayuiJson.toAjax(erpProduceCategoryService.insertErpProduceCategory(erpProduceCategory));
    }

    /**
     * 修改产品类别
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpProduceCategory erpProduceCategory = erpProduceCategoryService.selectErpProduceCategoryById(id);
        mmap.put("erpProduceCategory", erpProduceCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品类别
     */
//    @RequiresPermissions("business:kind:edit")
    @Log(moduleName = "产品类别", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(ErpProduceCategory erpProduceCategory)
    {
        return LayuiJson.toAjax(erpProduceCategoryService.updateErpProduceCategory(erpProduceCategory));
    }

    /**
     * 删除
     */
//    @RequiresPermissions("business:kind:remove")
    @Log(moduleName = "产品类别", businessType = BusinessType.REMOVE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public LayuiJson remove(@PathVariable("id") Long id)
    {
        return LayuiJson.toAjax(erpProduceCategoryService.deleteErpProduceCategoryById(id));
    }

    /**
     * 选择产品类别树
     */
    @GetMapping(value = { "/selectCategoryTree/{id}", "/selectCategoryTree/" })
    public String selectCategoryTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (id != null && StringUtils.isNotBlank(id.toString()))
        {
            mmap.put("erpProduceCategory", erpProduceCategoryService.selectErpProduceCategoryById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载产品类别树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = erpProduceCategoryService.selectErpProduceCategoryTree();
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
        return erpProduceCategoryService.getNameById(id);
    }
}
