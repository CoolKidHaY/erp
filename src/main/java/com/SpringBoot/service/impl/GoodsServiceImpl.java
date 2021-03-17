package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Goods;
import com.SpringBoot.mapper.GoodsMapper;
import com.SpringBoot.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	

	public void insert(Integer providerid, String goodsname, String productcode, String promitcode, String description,
			String size, String produceplace, String goodspackage, Double price, Integer number, Integer dangernum,
			Integer available) {
		goodsMapper.insert(providerid, goodsname, productcode, promitcode, description, size, produceplace, goodspackage, price, number, dangernum, available);
	}

	public void delete(Integer id) {
		goodsMapper.delete(id);		
	}

	public void update(Integer id, Integer providerid, String goodsname, String productcode, String promitcode,
			String description, String size, String produceplace, String goodspackage, Double price, Integer number,
			Integer dangernum, Integer available) {
		goodsMapper.update(id, providerid, goodsname, productcode, promitcode, description, size, produceplace, goodspackage, price, number, dangernum, available);		
	}


	public void updateNumber(Integer id, Integer number) {
		goodsMapper.updateNumber(id, number);
		
	}

	public List<Goods> select(Integer providerid, String goodsname, String productcode, String promitcode,
			String description, String size, Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Goods> select = goodsMapper.select(providerid, goodsname, productcode, promitcode, description, size, index, limit);
		return select;
	}

	public List<Goods> selectGoodsName() {
		// TODO 自动生成的方法存根
		List<Goods> selectGoodsName = goodsMapper.selectGoodsName();
		return selectGoodsName;
	}

	public List<Goods> selectByProviderid(Integer providerid) {
		// TODO 自动生成的方法存根
		return goodsMapper.selectByProviderid(providerid);
	}

	public Goods selectById(Integer id) {
		// TODO 自动生成的方法存根
		return goodsMapper.selectById(id);
	}

}
