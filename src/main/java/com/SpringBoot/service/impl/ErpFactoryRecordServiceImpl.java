package com.SpringBoot.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.SpringBoot.bean.*;
import com.SpringBoot.dto.InOrOutFactoryDto;
import com.SpringBoot.mapper.ErpFactoryRecordMapper;
import com.SpringBoot.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 仓库操作记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
@Service
public class ErpFactoryRecordServiceImpl extends ServiceImpl<ErpFactoryRecordMapper, ErpFactoryRecord> implements IErpFactoryRecordService
{
    @Autowired
    private ErpFactoryRecordMapper erpFactoryRecordMapper;

    @Autowired
    private IErpMaterialService erpMaterialService;

    @Autowired
    private IErpProduceService erpProduceService;

    @Autowired
    private IErpPurchaseService iErpPurchaseService;

    @Autowired
    private IErpPurchaseRecordService iErpPurchaseRecordService;

    /**
     * 查询仓库操作记录
     * 
     * @param id 仓库操作记录ID
     * @return 仓库操作记录
     */
    @Override
    public ErpFactoryRecord selectErpFactoryRecordById(Long id)
    {
        return this.getBaseMapper().selectById(id);
    }

    /**
     * 查询仓库操作记录列表
     * 
     * @param erpFactoryRecord 仓库操作记录
     * @return 仓库操作记录
     */
    @Override
    public Page<ErpFactoryRecord> selectErpFactoryRecordList(ErpFactoryRecord erpFactoryRecord)
    {
        QueryWrapper<ErpFactoryRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(erpFactoryRecord.getCName()), "c_name", erpFactoryRecord.getCName())
                .eq(StrUtil.isNotBlank(erpFactoryRecord.getType()), "type", erpFactoryRecord.getType())
                .eq(StrUtil.isNotBlank(erpFactoryRecord.getState()), "state", erpFactoryRecord.getState())
                .orderByDesc("oper_begin_time");
        return this.getBaseMapper().selectPage(new Page<>(erpFactoryRecord.getPageNum(), erpFactoryRecord.getPageSize()), queryWrapper);
    }

    /**
     * 新增仓库操作记录
     * 
     * @param erpFactoryRecord 仓库操作记录
     * @return 结果
     */
    @Override
    public int insertErpFactoryRecord(ErpFactoryRecord erpFactoryRecord)
    {
        return this.getBaseMapper().insert(erpFactoryRecord);
    }

    /**
     * 修改仓库操作记录
     * 
     * @param erpFactoryRecord 仓库操作记录
     * @return 结果
     */
    @Override
    public int updateErpFactoryRecord(ErpFactoryRecord erpFactoryRecord)
    {
        return this.getBaseMapper().updateById(erpFactoryRecord);
    }

    /**
     * 删除仓库操作记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpFactoryRecordByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除仓库操作记录信息
     * 
     * @param id 仓库操作记录ID
     * @return 结果
     */
    @Override
    public int deleteErpFactoryRecordById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }

    /**
     * 根据记录id查询记录详情
     * @param id
     * @return
     */
    @Override
    public InOrOutFactoryDto selectErpFactoryRecordDetailById(Long id) {
        InOrOutFactoryDto record = new InOrOutFactoryDto();
        // 查询出入库记录
        ErpFactoryRecord erpFactoryRecord = this.getBaseMapper().selectById(id);
        // 将查询的记录值赋值给record
        BeanUtil.copyProperties(erpFactoryRecord, record, CopyOptions.create().ignoreNullValue());
        // todo 这儿应该是一个采购记录和销售记录
        String code = erpFactoryRecord.getCode();
        if (code.startsWith("CG")){
            // 采购数据
            ErpPurchase erpPurchase = iErpPurchaseService.query().eq("order_no", erpFactoryRecord.getCode()).one();
            // 采购项
            List<ErpPurchaseRecord> purchaseRecordList = iErpPurchaseRecordService.query().eq("p_code", erpFactoryRecord.getCode()).list();
            erpPurchase.setItemList(purchaseRecordList);
            record.setErpPurchase(erpPurchase);
        } else if (code.startsWith("XS")){
            // todo 销售
        } else {
            // todo 生产
        }
        return record;
    }
}
