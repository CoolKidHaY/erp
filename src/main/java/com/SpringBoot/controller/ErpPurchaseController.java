package com.SpringBoot.controller;

import java.util.Date;
import java.util.List;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.ErpFactory;
import com.SpringBoot.bean.ErpPurchase;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.enums.BusinessType;
import com.SpringBoot.service.IErpFactoryService;
import com.SpringBoot.service.IErpPurchaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 采购Controller
 * 
 * @author ruoyi
 * @date 2021-04-28
 */
@Controller
@RequestMapping("/business/purchase")
public class ErpPurchaseController
{
    private String prefix = "business/purchase";

    @Autowired
    private IErpPurchaseService erpPurchaseService;

    @Autowired
    private IErpFactoryService iErpFactoryService;

//    @RequiresPermissions("business:purchase:view")
    @GetMapping()
    public String purchase()
    {
        return prefix + "/purchase";
    }

    /**
     * 查询采购列表
     */
//    @RequiresPermissions("business:purchase:list")
    @PostMapping("/list")
    @ResponseBody
    public LayuiJson list(ErpPurchase erpPurchase)
    {
        List<ErpPurchase> list = erpPurchaseService.selectErpPurchaseList(erpPurchase);
        return LayuiJson.success((long) list.size(), list);
    }

//    /**
//     * 导出采购列表
//     */
//    @RequiresPermissions("business:purchase:export")
//    @Log(moduleName = "采购", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(ErpPurchase erpPurchase)
//    {
//        List<ErpPurchase> list = erpPurchaseService.selectErpPurchaseList(erpPurchase);
//        ExcelUtil<ErpPurchase> util = new ExcelUtil<ErpPurchase>(ErpPurchase.class);
//        return util.exportExcel(list, "purchase");
//    }

    /**
     * 新增采购
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购
     */
//    @RequiresPermissions("business:purchase:add")
    @Log(moduleName = "采购", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public LayuiJson addSave(@RequestBody ErpPurchase erpPurchase)
    {
        return LayuiJson.toAjax(erpPurchaseService.insertErpPurchase(erpPurchase));
    }

    /**
     * 采购详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpPurchase erpPurchase = erpPurchaseService.selectErpPurchaseById(id);
        mmap.put("erpPurchase", erpPurchase);
        return prefix + "/detail";
    }

    /**
     * 修改采购
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ErpPurchase erpPurchase = erpPurchaseService.selectErpPurchaseById(id);
        mmap.put("erpPurchase", erpPurchase);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购
     */
//    @RequiresPermissions("business:purchase:edit")
    @Log(moduleName = "采购", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public LayuiJson editSave(@RequestBody ErpPurchase erpPurchase)
    {
        return LayuiJson.toAjax(erpPurchaseService.updateErpPurchase(erpPurchase));
    }

    /**
     * 删除采购
     */
//    @RequiresPermissions("business:purchase:remove")
    @Log(moduleName = "采购", businessType = BusinessType.REMOVE)
    @PostMapping( "/remove")
    @ResponseBody
    public LayuiJson remove(String ids)
    {
        return LayuiJson.toAjax(erpPurchaseService.deleteErpPurchaseByIds(ids));
    }

    /**
     * 材料列表
     * @return
     */
    @GetMapping("/allMaterial")
    public String materialForm(){
        return prefix + "/materialForm";
    }

    /**
     * 材料列表
     * @return
     */
    @GetMapping("/allProvider")
    public String providerForm(){
        return prefix + "/providerForm";
    }

    /**
     * 材料列表
     * @return
     */
    @GetMapping("/userList")
    public String userForm(){
        return prefix + "/userForm";
    }

    /**
     * 采购审核
     * @return
     */
    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id, Model mmap){
        ErpPurchase erpPurchase = erpPurchaseService.selectErpPurchaseById(id);
        mmap.addAttribute("erpPurchase", erpPurchase);
        return prefix + "/check";
    }

    /**
     * 保存审核
     * @param erpPurchase
     * @return
     */
    @PostMapping("/check")
    @ResponseBody
    public LayuiJson check(ErpPurchase erpPurchase){
        return LayuiJson.toAjax(erpPurchaseService.checkErpPurchase(erpPurchase));
    }

    /**
     * 开始采购
     * @param erpPurchase
     * @return
     */
    @PostMapping("/purchase")
    @ResponseBody
    public LayuiJson purchase(ErpPurchase erpPurchase){
        if ("PURCHASING".equals(erpPurchase.getState())){
            erpPurchase.setPurBeginTime(new Date());
        } else {
            erpPurchase.setPurEndTime(new Date());
        }
        return LayuiJson.toAjax(erpPurchaseService.checkErpPurchase(erpPurchase));
    }

    /**
     * 查询仓库列表
     * @return
     */
    @GetMapping("/factory")
    public String factory(){
        // 直接跳转到仓库列表界面
        return prefix + "/factoryForm";
    }

    /**
     * 入库
     * @param pCode
     * @param fId
     * @return
     */
    @PostMapping("/factory")
    @ResponseBody
    public LayuiJson factory(String pCode, Long fId){
        try {
            int row = erpPurchaseService.factory(pCode, fId);
            return LayuiJson.toAjax(row);
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiJson.error("仓库容量不足");
        }
    }

}
