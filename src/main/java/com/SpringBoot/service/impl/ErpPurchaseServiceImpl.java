package com.SpringBoot.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.SpringBoot.bean.*;
import com.SpringBoot.common.Constast;
import com.SpringBoot.mapper.ErpPurchaseMapper;
import com.SpringBoot.service.*;
import com.SpringBoot.utils.InStockUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 采购Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-28
 */
@Service
public class ErpPurchaseServiceImpl extends ServiceImpl<ErpPurchaseMapper, ErpPurchase> implements IErpPurchaseService
{
    @Autowired
    private ErpPurchaseMapper erpPurchaseMapper;

    @Autowired
    private IErpPurchaseRecordService iErpPurchaseRecordService;

    @Autowired
    private IErpFactoryRecordService iErpFactoryRecordService;

    @Autowired
    private IErpFactoryService iErpFactoryService;

    @Autowired
    private IErpFactoryMaterialService iErpFactoryMaterialService;

    /**
     * 查询采购
     * 
     * @param id 采购ID
     * @return 采购
     */
    @Override
    public ErpPurchase selectErpPurchaseById(Long id)
    {
        ErpPurchase erpPurchase = this.getBaseMapper().selectById(id);
        // 获取采购单号，查询采购项
        String code = erpPurchase.getOrderNo();
        List<ErpPurchaseRecord> list = iErpPurchaseRecordService.selectErpPurchaseRecordByCode(code);
        erpPurchase.setItemList(list);
        return erpPurchase;
    }

    /**
     * 查询采购列表
     * 
     * @param erpPurchase 采购
     * @return 采购
     */
    @Override
    public List<ErpPurchase> selectErpPurchaseList(ErpPurchase erpPurchase)
    {
        return erpPurchaseMapper.selectErpPurchaseList(erpPurchase);
    }

    /**
     * 新增采购
     * 
     * @param erpPurchase 采购
     * @return 结果
     */
    @Override
    @Transactional
    public int insertErpPurchase(ErpPurchase erpPurchase)
    {
        // 查找创建人
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpPurchase.setCreateBy(user.getName());
        erpPurchase.setUpdateBy(user.getName());
        erpPurchase.setCreateTime(new Date());
        erpPurchase.setUpdateTime(new Date());
        // 生成采购单号
        StringBuilder sb = new StringBuilder("CG");
        String id = IdUtil.objectId();
        sb.append(id);
        erpPurchase.setOrderNo(sb.toString());
        // 计算总价
        BigDecimal totalPrice = new BigDecimal(0);
        // 保存采购的采购项
        for (ErpPurchaseRecord erpPurchaseRecord : erpPurchase.getItemList()) {
            erpPurchaseRecord.setPCode(sb.toString());
            totalPrice = totalPrice.add(erpPurchaseRecord.getTotalPrice());
            iErpPurchaseRecordService.insertErpPurchaseRecord(erpPurchaseRecord);
        }
        erpPurchase.setMoney(totalPrice);
        return this.getBaseMapper().insert(erpPurchase);
    }

    /**
     * 修改采购
     * 
     * @param erpPurchase 采购
     * @return 结果
     */
    @Override
    @Transactional
    public int updateErpPurchase(ErpPurchase erpPurchase)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpPurchase.setUpdateBy(user.getName());
        erpPurchase.setUpdateTime(new Date());
        // 遍历采购项，并存入
        List<ErpPurchaseRecord> itemList = erpPurchase.getItemList();
        boolean b = iErpPurchaseRecordService.updateBatchById(itemList);
        if (b){
            return this.getBaseMapper().updateById(erpPurchase);
        }
        return 0;
    }

    /**
     * 修改采购
     *
     * @param erpPurchase 采购
     * @return 结果
     */
    @Override
    public int checkErpPurchase(ErpPurchase erpPurchase)
    {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        erpPurchase.setUpdateBy(user.getName());
        erpPurchase.setUpdateTime(new Date());
        return this.getBaseMapper().updateById(erpPurchase);
    }

    /**
     * 删除采购对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteErpPurchaseByIds(String ids)
    {
        return this.getBaseMapper().deleteBatchIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 删除采购信息
     * 
     * @param id 采购ID
     * @return 结果
     */
    @Override
    public int deleteErpPurchaseById(Long id)
    {
        return this.getBaseMapper().deleteById(id);
    }

    /**
     * 入库
     * @param pCode  采购单号
     * @param fId   仓库id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int factory(String pCode, Long fId) throws Exception {
        // 查询出采购和采购记录
        ErpPurchase erpPurchase = this.query().eq("order_no", pCode).one();
        List<ErpPurchaseRecord> purchaseRecordList = iErpPurchaseRecordService.query().eq("p_code", pCode).list();
        // 查询出仓库信息
        ErpFactory erpFactory = iErpFactoryService.selectErpFactoryById(fId);

        // 构建入库记录,并保存
        ErpFactoryRecord factoryRecord = new ErpFactoryRecord();
        factoryRecord.setCId(fId);
        factoryRecord.setCName(erpFactory.getName());
        factoryRecord.setType(Constast.FACTORY_INPUT);
        factoryRecord.setCode(pCode);
        factoryRecord.setState("PUTTING_SUCCESS");
        factoryRecord.setOperBeginTime(new Date());
        int i = iErpFactoryRecordService.insertErpFactoryRecord(factoryRecord);

        // 将采购的材料加入仓库库存中
        Long increaseCapacity = 0l;  //仓库新增存储
        for (ErpPurchaseRecord record : purchaseRecordList) {
            ErpFactoryMaterial factoryMaterial = iErpFactoryMaterialService.query().eq("code", record.getMCode()).one();
            if (factoryMaterial != null){
                // 不为null代表仓库里有这个材料，只需要添加数量即可
                Long total = factoryMaterial.getTotal();
                Long num = record.getNum();
                factoryMaterial.setTotal( num + total);
                factoryMaterial.setState(InStockUtils.returnMaterialStockCapacity(num + total));
                increaseCapacity += num;
                iErpFactoryMaterialService.updateErpFactoryMaterial(factoryMaterial);
            } else {
                // 为空代表没有存储此商品
                factoryMaterial = new ErpFactoryMaterial();
                factoryMaterial.setCode(record.getMCode());
                factoryMaterial.setName(record.getMName());
                factoryMaterial.setFId(fId);
                factoryMaterial.setType(0);
                factoryMaterial.setTotal(record.getNum());
                factoryMaterial.setState(InStockUtils.returnMaterialStockCapacity(record.getNum()));
                factoryMaterial.setUnitPrice(record.getUnitPrice());
                increaseCapacity += record.getNum();
                iErpFactoryMaterialService.insertErpFactoryMaterial(factoryMaterial);
            }
        }
        // 修改仓库已使用容量
        int row = iErpFactoryService.updateUsedCapacity(fId, increaseCapacity);
        if (row == -1){
            throw new Exception("仓库容量不足");
        }
        // 修改采购单号的状态为入库
        boolean update = this.update().eq("order_no", pCode).set("state", "PUTTING_SUCCESS").update();
        return i;
    }
}
