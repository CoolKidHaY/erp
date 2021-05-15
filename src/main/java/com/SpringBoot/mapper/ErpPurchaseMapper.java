package com.SpringBoot.mapper;

import java.util.List;

import com.SpringBoot.bean.ErpPurchase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 采购Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-28
 */
public interface ErpPurchaseMapper extends BaseMapper<ErpPurchase>
{
    /**
     * 查询采购
     * 
     * @param id 采购ID
     * @return 采购
     */
    public ErpPurchase selectErpPurchaseById(Long id);

    /**
     * 查询采购列表
     * 
     * @param erpPurchase 采购
     * @return 采购集合
     */
    public List<ErpPurchase> selectErpPurchaseList(ErpPurchase erpPurchase);

    /**
     * 新增采购
     * 
     * @param erpPurchase 采购
     * @return 结果
     */
    public int insertErpPurchase(ErpPurchase erpPurchase);

    /**
     * 修改采购
     * 
     * @param erpPurchase 采购
     * @return 结果
     */
    public int updateErpPurchase(ErpPurchase erpPurchase);

    /**
     * 删除采购
     * 
     * @param id 采购ID
     * @return 结果
     */
    public int deleteErpPurchaseById(Long id);

    /**
     * 批量删除采购
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpPurchaseByIds(String[] ids);
}
