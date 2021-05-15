package com.SpringBoot.dto;

import com.SpringBoot.bean.ErpFactoryRecord;
import com.SpringBoot.bean.ErpMaterial;
import com.SpringBoot.bean.ErpPurchase;
import lombok.Data;


/**
 * @title: ErpMaterialProductDto
 * @Author HuangYan
 * @Date: 2021/4/25 21:34
 * @Version 1.0
 * @Description: 出入库记录dto,将oid转换为一个产品或者材料实体
 */
@Data
public class InOrOutFactoryDto extends ErpFactoryRecord {

    // todo 这个应该是一个采购记录或者是销售记录，不能是采购的商品
//    private ErpMaterial material;
    private ErpPurchase erpPurchase;

}
