package com.SpringBoot.service.impl;

import cn.hutool.core.convert.Convert;
import com.SpringBoot.bean.ErpProduce;
import com.SpringBoot.bean.User;
import com.SpringBoot.mapper.ErpProduceMapper;
import com.SpringBoot.service.IErpProduceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 材料Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class ErpProduceServiceImpl extends ServiceImpl<ErpProduceMapper, ErpProduce> implements IErpProduceService
{
    @Autowired
    private ErpProduceMapper erpProduceMapper;

    /**
     * 查询材料
     * 
     * @param id 材料ID
     * @return 材料
     */
    @Override
    public ErpProduce selectErpProduceById(Long id)
    {
        ErpProduce erpProduce = this.getBaseMapper().selectById(id);
        return erpProduce;
    }

    /**
     * 查询材料列表
     * 
     * @param erpProduce 材料
     * @return 材料
     */
    @Override
    public List<ErpProduce> selectErpProduceList(ErpProduce erpProduce)
    {
        return erpProduceMapper.selectErpProduceList(erpProduce);
    }

    /**
     * 新增材料
     * 
     * @param erpProduce 材料
     * @return 结果
     */
    @Override
    public int insertErpProduce(ErpProduce erpProduce)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpProduce.setCreateBy(user.getName());
        erpProduce.setCreateTime(new Date());
        return this.getBaseMapper().insert(erpProduce);
    }

    /**
     * 修改材料
     * 
     * @param erpProduce 材料
     * @return 结果
     */
    @Override
    public int updateErpProduce(ErpProduce erpProduce)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpProduce.setUpdateBy(user.getName());
        erpProduce.setUpdateTime(new Date());
        return this.getBaseMapper().updateById(erpProduce);
    }

    /**
     * 删除材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpProduceByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除材料信息
     * 
     * @param id 材料ID
     * @return 结果
     */
    @Override
    public int deleteErpProduceById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }
}
