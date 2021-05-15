package com.SpringBoot.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.convert.Convert;
import com.SpringBoot.bean.ErpFactory;
import com.SpringBoot.mapper.ErpFactoryMapper;
import com.SpringBoot.service.IErpFactoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 仓库Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-24
 */
@Service
public class ErpFactoryServiceImpl extends ServiceImpl<ErpFactoryMapper, ErpFactory> implements IErpFactoryService
{
    @Autowired
    private ErpFactoryMapper erpFactoryMapper;

    /**
     * 查询仓库
     * 
     * @param id 仓库ID
     * @return 仓库
     */
    @Override
    public ErpFactory selectErpFactoryById(Long id)
    {
        return this.getBaseMapper().selectById(id);
    }

    /**
     * 查询仓库列表
     * 
     * @param erpFactory 仓库
     * @return 仓库
     */
    @Override
    public List<ErpFactory> selectErpFactoryList(ErpFactory erpFactory)
    {
        return erpFactoryMapper.selectErpFactoryList(erpFactory);
    }

    /**
     * 新增仓库
     * 
     * @param erpFactory 仓库
     * @return 结果
     */
    @Override
    public int insertErpFactory(ErpFactory erpFactory)
    {
        return this.getBaseMapper().insert(erpFactory);
    }

    /**
     * 修改仓库
     * 
     * @param erpFactory 仓库
     * @return 结果
     */
    @Override
    public int updateErpFactory(ErpFactory erpFactory)
    {
        return this.getBaseMapper().updateById(erpFactory);
    }

    /**
     * 删除仓库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpFactoryByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除仓库信息
     * 
     * @param id 仓库ID
     * @return 结果
     */
    @Override
    public int deleteErpFactoryById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }

    /**
     * 修改仓库已使用容量
     * @param increaseCapacity
     * @return
     */
    @Override
    public int updateUsedCapacity(Long id, Long increaseCapacity) {
        ErpFactory erpFactory = erpFactoryMapper.selectById(id);
        Long totalCapacity = erpFactory.getTotalCapacity();
        Long usedCapacity = erpFactory.getUsedCapacity();
        if (totalCapacity < usedCapacity + increaseCapacity){
            return -1;
        } else {
            return erpFactoryMapper.updateUsedCapacity(id,increaseCapacity);
        }
    }
}
