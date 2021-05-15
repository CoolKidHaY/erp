package com.SpringBoot.service;

import com.SpringBoot.bean.ErpProduceCategory;
import com.SpringBoot.utils.Ztree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 产品类别Service接口
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public interface IErpProduceCategoryService extends IService<ErpProduceCategory>
{
    /**
     * 查询产品类别
     * 
     * @param id 产品类别ID
     * @return 产品类别
     */
    public ErpProduceCategory selectErpProduceCategoryById(Long id);

    /**
     * 查询产品类别列表
     * 
     * @param erpProduceCategory 产品类别
     * @return 产品类别集合
     */
    public List<ErpProduceCategory> selectErpProduceCategoryList(ErpProduceCategory erpProduceCategory);

    /**
     * 新增产品类别
     * 
     * @param erpProduceCategory 产品类别
     * @return 结果
     */
    public int insertErpProduceCategory(ErpProduceCategory erpProduceCategory);

    /**
     * 修改产品类别
     * 
     * @param erpProduceCategory 产品类别
     * @return 结果
     */
    public int updateErpProduceCategory(ErpProduceCategory erpProduceCategory);

    /**
     * 批量删除产品类别
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpProduceCategoryByIds(String ids);

    /**
     * 删除产品类别信息
     * 
     * @param id 产品类别ID
     * @return 结果
     */
    public int deleteErpProduceCategoryById(Long id);

    /**
     * 查询产品类别树列表
     *
     * @return 所有产品类别信息
     */
    public List<Ztree> selectErpProduceCategoryTree();

    /**
     * id查询种类名称
     * @param id
     * @return
     */
    String getNameById(Long id);
}
