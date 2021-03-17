package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Salesback;
import com.SpringBoot.mapper.SalesbackMapper;
import com.SpringBoot.service.SalesbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesbackServiceImpl extends ServiceImpl<SalesbackMapper, Salesback> implements SalesbackService {
	
	@Autowired
	private SalesbackMapper salesbackMapper;


	public List<Salesback> select(Integer customerid, Integer goodsid, Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Salesback> select = salesbackMapper.select(customerid, goodsid, index, limit);
		return select;
	}


	public void delete(Integer id) {
		// TODO 自动生成的方法存根
		salesbackMapper.delete(id);
	}


	public void insert(Integer customerid, String paytype, Date salesbacktime, Double salebackprice,
			String operateperson, Integer number, String remark, Integer goodsid) {
		// TODO 自动生成的方法存根
		salesbackMapper.insert(customerid, paytype, salesbacktime, salebackprice, operateperson, number, remark, goodsid);
	}

}
