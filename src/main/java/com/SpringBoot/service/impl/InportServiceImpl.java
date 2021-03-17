package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Inport;
import com.SpringBoot.mapper.InportMapper;
import com.SpringBoot.service.InportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService {

	@Autowired
	InportMapper inportMapper;
	
	
	public InportMapper getinportMapper() {
		return inportMapper;
	}

	public void setinportMapper(InportMapper inportMapper) {
		this.inportMapper = inportMapper;
	}



	public void insert(String paytype, Date inporttime, String operateperson, Integer number, String remark,
			Double inportprice, Integer providerid, Integer goodsid) {
		inportMapper.insert(paytype, inporttime, operateperson, number, remark, inportprice, providerid, goodsid);
	}


	public void update(Integer id, String paytype, Date inporttime, Integer number, String remark, Double inportprice,
			Integer providerid, Integer goodsid, String operateperson) {
		
		inportMapper.update(id, paytype, inporttime, number, remark, inportprice, providerid, goodsid,operateperson);
		
	}


	public void delete(Integer id) {
		inportMapper.delete(id);
	}


	public List<Inport> select(Integer providerid, Integer goodsid, Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Inport> select = inportMapper.select(providerid, goodsid, index, limit);
		return select;
	}


	public Inport selectById(Integer id) {
		// TODO 自动生成的方法存根
		return inportMapper.selectById(id);
	}

	


}
