package com.SpringBoot.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.SpringBoot.bean.ErpSalesRecord;
import com.SpringBoot.bean.User;
import com.SpringBoot.common.Constast;
import com.SpringBoot.mapper.ErpSalesRecordMapper;
import com.SpringBoot.service.IErpSalesRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 生产记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Service
public class ErpSalesRecordServiceImpl extends ServiceImpl<ErpSalesRecordMapper, ErpSalesRecord> implements IErpSalesRecordService
{
    @Autowired
    private ErpSalesRecordMapper erpSalesRecordMapper;

    /**
     * 查询生产记录
     * 
     * @param id 生产记录ID
     * @return 生产记录
     */
    @Override
    public ErpSalesRecord selectErpSalesRecordById(Long id)
    {
        return this.getBaseMapper().selectById(id);
    }

    /**
     * 查询生产记录列表
     * 
     * @param erpSalesRecord 生产记录
     * @return 生产记录
     */
    @Override
    public List<ErpSalesRecord> selectErpSalesRecordList(ErpSalesRecord erpSalesRecord)
    {
        return erpSalesRecordMapper.selectErpSalesRecordList(erpSalesRecord);
    }

    /**
     * 新增生产记录
     * 
     * @param erpSalesRecord 生产记录
     * @return 结果
     */
    @Override
    public int insertErpSalesRecord(ErpSalesRecord erpSalesRecord)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpSalesRecord.setCreateBy(user.getName());
        erpSalesRecord.setCreateTime(new Date());
        // 销售编号
        StringBuilder sb = new StringBuilder("SC");
        String id = IdUtil.objectId();
        sb.append(id);
        erpSalesRecord.setCode(sb.toString());
        // 默认待审核
        erpSalesRecord.setState(Constast.CHECKING);
        return this.getBaseMapper().insert(erpSalesRecord);
    }

    /**
     * 修改生产记录
     * 
     * @param erpSalesRecord 生产记录
     * @return 结果
     */
    @Override
    public int updateErpSalesRecord(ErpSalesRecord erpSalesRecord)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpSalesRecord.setUpdateBy(user.getName());
        erpSalesRecord.setUpdateTime(new Date());
        return this.getBaseMapper().updateById(erpSalesRecord);
    }

    /**
     * 删除生产记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpSalesRecordByIds(String ids)
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
    public int deleteErpSalesRecordById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }
}
