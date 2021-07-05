package com.SpringBoot.service;

import java.util.List;

import com.SpringBoot.bean.ErpFactoryRecord;
import com.SpringBoot.dto.InOrOutFactoryDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 仓库操作记录Service接口
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
public interface IErpFactoryRecordService extends IService<ErpFactoryRecord>
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
    public Page<ErpFactoryRecord> selectErpFactoryRecordList(ErpFactoryRecord erpFactoryRecord);

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
     * 批量删除仓库操作记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpFactoryRecordByIds(String ids);

    /**
     * 删除仓库操作记录信息
     * 
     * @param id 仓库操作记录ID
     * @return 结果
     */
    public int deleteErpFactoryRecordById(Long id);

    /**
     * 根据记录id查询记录详情
     * @param id
     * @return
     */
    InOrOutFactoryDto selectErpFactoryRecordDetailById(Long id);
}
