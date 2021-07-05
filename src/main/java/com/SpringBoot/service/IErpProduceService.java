package com.SpringBoot.service;

import com.SpringBoot.bean.ErpProduce;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 材料Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IErpProduceService extends IService<ErpProduce>
{
    /**
     * 查询材料
     * 
     * @param id 材料ID
     * @return 材料
     */
    public ErpProduce selectErpProduceById(Long id);

    /**
     * 查询材料列表
     * 
     * @param erpProduce 材料
     * @return 材料集合
     */
    public Page<ErpProduce> selectErpProduceList(ErpProduce erpProduce);

    /**
     * 新增材料
     * 
     * @param erpProduce 材料
     * @return 结果
     */
    public int insertErpProduce(ErpProduce erpProduce);

    /**
     * 修改材料
     * 
     * @param erpProduce 材料
     * @return 结果
     */
    public int updateErpProduce(ErpProduce erpProduce);

    /**
     * 批量删除材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpProduceByIds(String ids);

    /**
     * 删除材料信息
     * 
     * @param id 材料ID
     * @return 结果
     */
    public int deleteErpProduceById(Long id);
}
