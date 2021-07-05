package com.SpringBoot.service;

import java.util.List;

import com.SpringBoot.bean.ErpProduceRecord;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 生产记录Service接口
 * 
 * @author ruoyi
 * @date 2021-05-15
 */
public interface IErpProduceRecordService extends IService<ErpProduceRecord>
{
    /**
     * 查询生产记录
     * 
     * @param id 生产记录ID
     * @return 生产记录
     */
    public ErpProduceRecord selectErpProduceRecordById(Long id);

    /**
     * 查询生产记录列表
     * 
     * @param erpProduceRecord 生产记录
     * @return 生产记录集合
     */
    public Page<ErpProduceRecord> selectErpProduceRecordList(ErpProduceRecord erpProduceRecord);

    /**
     * 新增生产记录
     * 
     * @param erpProduceRecord 生产记录
     * @return 结果
     */
    public int insertErpProduceRecord(ErpProduceRecord erpProduceRecord);

    /**
     * 修改生产记录
     * 
     * @param erpProduceRecord 生产记录
     * @return 结果
     */
    public int updateErpProduceRecord(ErpProduceRecord erpProduceRecord);

    /**
     * 批量删除生产记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpProduceRecordByIds(String ids);

    /**
     * 删除生产记录信息
     * 
     * @param id 生产记录ID
     * @return 结果
     */
    public int deleteErpProduceRecordById(Long id);
}
