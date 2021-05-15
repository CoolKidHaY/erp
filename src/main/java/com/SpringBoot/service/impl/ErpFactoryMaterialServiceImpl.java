package com.SpringBoot.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.convert.Convert;
import com.SpringBoot.bean.ErpFactoryMaterial;
import com.SpringBoot.mapper.ErpFactoryMaterialMapper;
import com.SpringBoot.service.IErpFactoryMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 库存Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-24
 */
@Service
public class ErpFactoryMaterialServiceImpl extends ServiceImpl<ErpFactoryMaterialMapper, ErpFactoryMaterial> implements IErpFactoryMaterialService
{
    @Autowired
    private ErpFactoryMaterialMapper erpFactoryMaterialMapper;

    /**
     * 查询库存
     * 
     * @param id 库存ID
     * @return 库存
     */
    @Override
    public ErpFactoryMaterial selectErpFactoryMaterialById(Long id)
    {
        return this.getBaseMapper().selectById(id);
    }

    /**
     * 查询库存列表
     * 
     * @param erpFactoryMaterial 库存
     * @return 库存
     */
    @Override
    public List<ErpFactoryMaterial> selectErpFactoryMaterialList(ErpFactoryMaterial erpFactoryMaterial)
    {
        return erpFactoryMaterialMapper.selectErpFactoryMaterialList(erpFactoryMaterial);
    }

    /**
     * 新增库存
     * 
     * @param erpFactoryMaterial 库存
     * @return 结果
     */
    @Override
    public int insertErpFactoryMaterial(ErpFactoryMaterial erpFactoryMaterial)
    {
        return this.getBaseMapper().insert(erpFactoryMaterial);
    }

    /**
     * 修改库存
     * 
     * @param erpFactoryMaterial 库存
     * @return 结果
     */
    @Override
    public int updateErpFactoryMaterial(ErpFactoryMaterial erpFactoryMaterial)
    {
        return this.getBaseMapper().updateById(erpFactoryMaterial);
    }

    /**
     * 删除库存对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpFactoryMaterialByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除库存信息
     * 
     * @param id 库存ID
     * @return 结果
     */
    @Override
    public int deleteErpFactoryMaterialById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }

//    /**
//     * 材料id查询库存
//     * @param id
//     * @return
//     */
//    @Override
//    public List<ErpFactoryMaterial> selectStockByMid(Long id) {
//        List<ErpFactoryMaterial> list = this.getBaseMapper().selectList(new QueryWrapper<ErpFactoryMaterial>().eq("m_id", id));
//        return list;
//    }
}
