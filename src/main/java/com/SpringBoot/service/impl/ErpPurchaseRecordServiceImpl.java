package com.SpringBoot.service.impl;

import java.util.List;

import cn.hutool.core.convert.Convert;
import com.SpringBoot.bean.ErpPurchaseRecord;
import com.SpringBoot.mapper.ErpPurchaseRecordMapper;
import com.SpringBoot.service.IErpPurchaseRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-06
 */
@Service
public class ErpPurchaseRecordServiceImpl extends ServiceImpl<ErpPurchaseRecordMapper, ErpPurchaseRecord> implements IErpPurchaseRecordService
{
    @Autowired
    private ErpPurchaseRecordMapper erpPurchaseRecordMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public ErpPurchaseRecord selectErpPurchaseRecordById(Long id)
    {
        return this.getBaseMapper().selectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param erpPurchaseRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ErpPurchaseRecord> selectErpPurchaseRecordList(ErpPurchaseRecord erpPurchaseRecord)
    {
        return erpPurchaseRecordMapper.selectErpPurchaseRecordList(erpPurchaseRecord);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param erpPurchaseRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertErpPurchaseRecord(ErpPurchaseRecord erpPurchaseRecord)
    {
        return this.getBaseMapper().insert(erpPurchaseRecord);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param erpPurchaseRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateErpPurchaseRecord(ErpPurchaseRecord erpPurchaseRecord)
    {
        return this.getBaseMapper().updateById(erpPurchaseRecord);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpPurchaseRecordByIds(String ids)
    {
        return erpPurchaseRecordMapper.deleteErpPurchaseRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteErpPurchaseRecordById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }

    /**
     * 采购单号查询采购集合
     * @param code
     * @return
     */
    @Override
    public List<ErpPurchaseRecord> selectErpPurchaseRecordByCode(String code) {
        QueryWrapper<ErpPurchaseRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_code", code);
        return this.getBaseMapper().selectList(queryWrapper);
    }
}
