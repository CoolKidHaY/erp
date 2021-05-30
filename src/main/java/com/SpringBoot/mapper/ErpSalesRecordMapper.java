package com.SpringBoot.mapper;

import java.util.List;

import com.SpringBoot.bean.ErpSalesRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 生产记录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
public interface ErpSalesRecordMapper extends BaseMapper<ErpSalesRecord>
{
    /**
     * 查询生产记录
     * 
     * @param id 生产记录ID
     * @return 生产记录
     */
    public ErpSalesRecord selectErpSalesRecordById(Long id);

    /**
     * 查询生产记录列表
     * 
     * @param erpSalesRecord 生产记录
     * @return 生产记录集合
     */
    public List<ErpSalesRecord> selectErpSalesRecordList(ErpSalesRecord erpSalesRecord);

    /**
     * 新增生产记录
     * 
     * @param erpSalesRecord 生产记录
     * @return 结果
     */
    public int insertErpSalesRecord(ErpSalesRecord erpSalesRecord);

    /**
     * 修改生产记录
     * 
     * @param erpSalesRecord 生产记录
     * @return 结果
     */
    public int updateErpSalesRecord(ErpSalesRecord erpSalesRecord);

    /**
     * 删除生产记录
     * 
     * @param id 生产记录ID
     * @return 结果
     */
    public int deleteErpSalesRecordById(Long id);

    /**
     * 批量删除生产记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpSalesRecordByIds(String[] ids);
}
