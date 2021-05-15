package com.SpringBoot.mapper;

import com.SpringBoot.bean.ErpProduce;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 材料Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface ErpProduceMapper extends BaseMapper<ErpProduce>
{

    /**
     * 查询材料列表
     * 
     * @param erpProduce 材料
     * @return 材料集合
     */
    public List<ErpProduce> selectErpProduceList(ErpProduce erpProduce);
}
