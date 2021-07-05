package com.SpringBoot.service;

import java.util.List;

import com.SpringBoot.bean.ErpProvider;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 供应商Service接口
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
public interface IErpProviderService extends IService<ErpProvider>
{
    /**
     * 查询供应商
     * 
     * @param id 供应商ID
     * @return 供应商
     */
    public ErpProvider selectErpProviderById(Long id);

    /**
     * 查询供应商列表
     * 
     * @param erpProvider 供应商
     * @return 供应商集合
     */
    public Page<ErpProvider> selectErpProviderList(ErpProvider erpProvider);

    /**
     * 新增供应商
     * 
     * @param erpProvider 供应商
     * @return 结果
     */
    public int insertErpProvider(ErpProvider erpProvider);

    /**
     * 修改供应商
     * 
     * @param erpProvider 供应商
     * @return 结果
     */
    public int updateErpProvider(ErpProvider erpProvider);

    /**
     * 批量删除供应商
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpProviderByIds(String ids);

    /**
     * 删除供应商信息
     * 
     * @param id 供应商ID
     * @return 结果
     */
    public int deleteErpProviderById(Long id);
}
