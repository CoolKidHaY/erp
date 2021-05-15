package com.SpringBoot.mapper;

import java.util.List;

import com.SpringBoot.bean.ErpPurchaseRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-06
 */
public interface ErpPurchaseRecordMapper extends BaseMapper<ErpPurchaseRecord>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public ErpPurchaseRecord selectErpPurchaseRecordById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param erpPurchaseRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ErpPurchaseRecord> selectErpPurchaseRecordList(ErpPurchaseRecord erpPurchaseRecord);

    /**
     * 新增【请填写功能名称】
     * 
     * @param erpPurchaseRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertErpPurchaseRecord(ErpPurchaseRecord erpPurchaseRecord);

    /**
     * 修改【请填写功能名称】
     * 
     * @param erpPurchaseRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateErpPurchaseRecord(ErpPurchaseRecord erpPurchaseRecord);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteErpPurchaseRecordById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpPurchaseRecordByIds(String[] ids);
}
