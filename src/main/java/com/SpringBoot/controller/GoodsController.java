package com.SpringBoot.controller;

import java.util.List;

import com.SpringBoot.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.bean.Goods;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.GoodsService;

@RestController
@RequestMapping("goods")
public class GoodsController {
	
	@Autowired
	private GoodsServiceImpl goodsServiceImpl;
	
	@Autowired
	private LayuiJson layuiJson;
	
    @RequestMapping("loadAllGoods")
	public LayuiJson<Goods> loadAllGoods(Integer providerid,String goodsname ,String productcode,String promitcode,String description
			,String size,Integer page,Integer limit){
		
		int index=(page-1)*limit;
		List<Goods> data = goodsServiceImpl.select(providerid, goodsname, productcode, promitcode, description, size, index, limit);
		layuiJson.setCode(0);
		layuiJson.setTotal(1000l);
		layuiJson.setRows(data);
		return layuiJson;
	}
    
    
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllGoodsForSelect() {
    	List<Goods> list = goodsServiceImpl.selectGoodsName();
    	return new DataGridView(list);
    }
    
    
    @RequestMapping("loadGoodsByProviderId")
    public DataGridView loadGoodsByProviderId(Integer providerid) {
    	List<Goods> list = goodsServiceImpl.selectByProviderid(providerid);
    	return new DataGridView(list);
    }
    
    
    /**
     * 添加商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(Integer providerid, String goodsname, String productcode, String promitcode, String description,
			String size, String produceplace, String goodspackage, Double price, Integer number, Integer dangernum,
			Integer available){
        try {
            goodsServiceImpl.insert(providerid, goodsname, productcode, promitcode, description, size, produceplace, goodspackage, price, number, dangernum, available);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    
    /**
     * 修改商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(Integer id, Integer providerid, String goodsname, String productcode, String promitcode,
			String description, String size, String produceplace, String goodspackage, Double price, Integer number,
			Integer dangernum, Integer available){
        try {
            goodsServiceImpl.update(id, providerid, goodsname, productcode, promitcode, description, size, produceplace, goodspackage, price, number, dangernum, available);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    
    /**
     * 删除商品
     * @param id 商品id
     * @return
     */
    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id){
        try {
            goodsServiceImpl.delete(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
