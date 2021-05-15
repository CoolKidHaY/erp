package com.SpringBoot.mapper;

import com.SpringBoot.bean.ErpProduceCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 材料类别Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public interface ErpProduceCategoryMapper extends BaseMapper<ErpProduceCategory>
{
    /**
     * 查询材料类别
     * 
     * @param id 材料类别ID
     * @return 材料类别
     */
    public ErpProduceCategory selectErpProduceCategoryById(Long id);

    /**
     * 查询材料类别列表
     * 
     * @param erpProduceCategory 材料类别
     * @return 材料类别集合
     */
    public List<ErpProduceCategory> selectErpProduceCategoryList(ErpProduceCategory erpProduceCategory);

    /**
     * 新增材料类别
     * 
     * @param erpProduceCategory 材料类别
     * @return 结果
     */
    public int insertErpProduceCategory(ErpProduceCategory erpProduceCategory);

    /**
     * 修改材料类别
     * 
     * @param erpProduceCategory 材料类别
     * @return 结果
     */
    public int updateErpProduceCategory(ErpProduceCategory erpProduceCategory);

    /**
     * 删除材料类别
     * 
     * @param id 材料类别ID
     * @return 结果
     */
    public int deleteErpProduceCategoryById(Long id);

    /**
     * 批量删除材料类别
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpProduceCategoryByIds(String[] ids);

    /**
     * id查询种类名称
     * @param id
     * @return
     */
    String getNameById(Long id);
}
