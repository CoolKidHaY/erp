package com.SpringBoot.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.SpringBoot.bean.ErpProduceRecord;
import com.SpringBoot.bean.User;
import com.SpringBoot.mapper.ErpProduceRecordMapper;
import com.SpringBoot.service.IErpProduceRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 生产记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-15
 */
@Service
public class ErpProduceRecordServiceImpl extends ServiceImpl<ErpProduceRecordMapper, ErpProduceRecord> implements IErpProduceRecordService
{
    @Autowired
    private ErpProduceRecordMapper erpProduceRecordMapper;

    /**
     * 查询生产记录
     * 
     * @param id 生产记录ID
     * @return 生产记录
     */
    @Override
    public ErpProduceRecord selectErpProduceRecordById(Long id)
    {
        return this.getBaseMapper().selectById(id);
    }

    /**
     * 查询生产记录列表
     * 
     * @param erpProduceRecord 生产记录
     * @return 生产记录
     */
    @Override
    public List<ErpProduceRecord> selectErpProduceRecordList(ErpProduceRecord erpProduceRecord)
    {
        return erpProduceRecordMapper.selectErpProduceRecordList(erpProduceRecord);
    }

    /**
     * 新增生产记录
     * 
     * @param erpProduceRecord 生产记录
     * @return 结果
     */
    @Override
    public int insertErpProduceRecord(ErpProduceRecord erpProduceRecord)
    {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpProduceRecord.setCreateBy(user.getName());
        erpProduceRecord.setCreateTime(new Date());
        // 生成材料编号
        StringBuilder sb = new StringBuilder("SC");
        String id = IdUtil.objectId();
        sb.append(id);
        erpProduceRecord.setCode(sb.toString());
        return this.getBaseMapper().insertErpProduceRecord(erpProduceRecord);
    }

    /**
     * 修改生产记录
     * 
     * @param erpProduceRecord 生产记录
     * @return 结果
     */
    @Override
    public int updateErpProduceRecord(ErpProduceRecord erpProduceRecord)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpProduceRecord.setUpdateBy(user.getName());
        erpProduceRecord.setUpdateTime(new Date());
        return this.getBaseMapper().updateById(erpProduceRecord);
    }

    /**
     * 删除生产记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpProduceRecordByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除生产记录信息
     * 
     * @param id 生产记录ID
     * @return 结果
     */
    @Override
    public int deleteErpProduceRecordById(Long id)
    {

        return this.getBaseMapper().deleteById(id);
    }
}
