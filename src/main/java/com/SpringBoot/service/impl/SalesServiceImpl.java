package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Sales;
import com.SpringBoot.mapper.SalesMapper;
import com.SpringBoot.service.SalesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService {
	
	@Autowired
	private SalesMapper salesMapper;
	

	public List<Sales> select(Integer customerid, Integer goodsid, Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Sales> select = salesMapper.select(customerid, goodsid, index, limit);
		return select;
	}


	public void insert(Integer customerid, String paytype, Date salestime, String operateperson, Integer number,
			String remark, Double saleprice, Integer goodsid) {
		// TODO 自动生成的方法存根
		salesMapper.insert(customerid, paytype, salestime, operateperson, number, remark, saleprice, goodsid);
	}


	public void update(Integer id, Integer customerid, String paytype, Date salestime, String operateperson,
			Integer number, String remark, Double saleprice, Integer goodsid) {
		// TODO 自动生成的方法存根
		salesMapper.update(id, customerid, paytype, salestime, operateperson, number, remark, saleprice, goodsid);
	}


	public void delete(Integer id) {
		// TODO 自动生成的方法存根
		salesMapper.delete(id);
	}


	public Sales selectById(Integer id) {
		// TODO 自动生成的方法存根
		return salesMapper.selectById(id);
	}


	public void updateNumber(Integer id, Integer number) {
		// TODO 自动生成的方法存根
		salesMapper.updateNumber(id, number);
	}


}
