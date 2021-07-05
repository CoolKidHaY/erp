package com.SpringBoot.service;

import java.util.List;

import com.SpringBoot.bean.ErpFactoryMaterial;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 库存Service接口
 * 
 * @author ruoyi
 * @date 2021-04-24
 */
public interface IErpFactoryMaterialService extends IService<ErpFactoryMaterial>
{
    /**
     * 查询库存
     * 
     * @param id 库存ID
     * @return 库存
     */
    public ErpFactoryMaterial selectErpFactoryMaterialById(Long id);

    /**
     * 查询库存列表
     * 
     * @param erpFactoryMaterial 库存
     * @return 库存集合
     */
    public Page<ErpFactoryMaterial> selectErpFactoryMaterialList(ErpFactoryMaterial erpFactoryMaterial);

    /**
     * 新增库存
     * 
     * @param erpFactoryMaterial 库存
     * @return 结果
     */
    public int insertErpFactoryMaterial(ErpFactoryMaterial erpFactoryMaterial);

    /**
     * 修改库存
     * 
     * @param erpFactoryMaterial 库存
     * @return 结果
     */
    public int updateErpFactoryMaterial(ErpFactoryMaterial erpFactoryMaterial);

    /**
     * 批量删除库存
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteErpFactoryMaterialByIds(String ids);

    /**
     * 删除库存信息
     * 
     * @param id 库存ID
     * @return 结果
     */
    public int deleteErpFactoryMaterialById(Long id);

//    /**
//     * 材料id查询库存
//     * @param id
//     * @return
//     */
//    List<ErpFactoryMaterial> selectStockByMid(Long id);
}
