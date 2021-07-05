package com.SpringBoot.service;


import com.SpringBoot.bean.ErpFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 仓库Service接口
 * 
 * @author ruoyi
 * @date 2021-04-24
 */
public interface IErpFactoryService extends IService<ErpFactory>
{
    /**
     * 查询仓库
     * 
     * @param id 仓库ID
     * @return 仓库
     */
    public ErpFactory selectErpFactoryById(Long id);

    /**
     * 查询仓库列表
     * 
     * @param erpFactory 仓库
     * @return 仓库集合
     */
    public Page<ErpFactory> selectErpFactoryList(ErpFactory erpFactory);

    /**
     * 新增仓库
     * 
     * @param erpFactory 仓库
     * @return 结果
     */
    public int insertErpFactory(ErpFactory erpFactory);

    /**
     * 修改仓库
     * 
     * @param erpFactory 仓库
     * @return 结果
     */
    public int updateErpFactory(ErpFactory erpFactory);

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpFactoryByIds(String ids);

    /**
     * 删除仓库信息
     * 
     * @param id 仓库ID
     * @return 结果
     */
    public int deleteErpFactoryById(Long id);

    /**
     * 修改仓库已使用容量
     * @param increaseCapacity
     * @return
     */
    int updateUsedCapacity(Long id, Long increaseCapacity);
}
