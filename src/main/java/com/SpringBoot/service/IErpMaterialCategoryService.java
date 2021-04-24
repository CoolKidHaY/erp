package com.SpringBoot.service;

import java.util.List;

import com.SpringBoot.bean.ErpMaterialCategory;
import com.SpringBoot.utils.Ztree;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 材料类别Service接口
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public interface IErpMaterialCategoryService extends IService<ErpMaterialCategory>
{
    /**
     * 查询材料类别
     * 
     * @param id 材料类别ID
     * @return 材料类别
     */
    public ErpMaterialCategory selectErpMaterialCategoryById(Long id);

    /**
     * 查询材料类别列表
     * 
     * @param erpMaterialCategory 材料类别
     * @return 材料类别集合
     */
    public List<ErpMaterialCategory> selectErpMaterialCategoryList(ErpMaterialCategory erpMaterialCategory);

    /**
     * 新增材料类别
     * 
     * @param erpMaterialCategory 材料类别
     * @return 结果
     */
    public int insertErpMaterialCategory(ErpMaterialCategory erpMaterialCategory);

    /**
     * 修改材料类别
     * 
     * @param erpMaterialCategory 材料类别
     * @return 结果
     */
    public int updateErpMaterialCategory(ErpMaterialCategory erpMaterialCategory);

    /**
     * 批量删除材料类别
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpMaterialCategoryByIds(String ids);

    /**
     * 删除材料类别信息
     * 
     * @param id 材料类别ID
     * @return 结果
     */
    public int deleteErpMaterialCategoryById(Long id);

    /**
     * 查询材料类别树列表
     *
     * @return 所有材料类别信息
     */
    public List<Ztree> selectErpMaterialCategoryTree();

    /**
     * id查询种类名称
     * @param id
     * @return
     */
    String getNameById(Long id);
}
