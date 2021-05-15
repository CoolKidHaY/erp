package com.SpringBoot.mapper;

import java.util.List;

import com.SpringBoot.bean.ErpFactoryRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 仓库操作记录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
public interface ErpFactoryRecordMapper extends BaseMapper<ErpFactoryRecord>
{
    /**
     * 查询仓库操作记录
     * 
     * @param id 仓库操作记录ID
     * @return 仓库操作记录
     */
    public ErpFactoryRecord selectErpFactoryRecordById(Long id);

    /**
     * 查询仓库操作记录列表
     * 
     * @param erpFactoryRecord 仓库操作记录
     * @return 仓库操作记录集合
     */
    public List<ErpFactoryRecord> selectErpFactoryRecordList(ErpFactoryRecord erpFactoryRecord);

    /**
     * 新增仓库操作记录
     * 
     * @param erpFactoryRecord 仓库操作记录
     * @return 结果
     */
    public int insertErpFactoryRecord(ErpFactoryRecord erpFactoryRecord);

    /**
     * 修改仓库操作记录
     * 
     * @param erpFactoryRecord 仓库操作记录
     * @return 结果
     */
    public int updateErpFactoryRecord(ErpFactoryRecord erpFactoryRecord);

    /**
     * 删除仓库操作记录
     * 
     * @param id 仓库操作记录ID
     * @return 结果
     */
    public int deleteErpFactoryRecordById(Long id);

    /**
     * 批量删除仓库操作记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpFactoryRecordByIds(String[] ids);
}
