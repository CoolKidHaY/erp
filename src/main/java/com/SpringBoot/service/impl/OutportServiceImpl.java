package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Outport;
import com.SpringBoot.mapper.OutportMapper;
import com.SpringBoot.service.OutportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {

	@Autowired
	private OutportMapper outportMapper;

	public void delete(Integer id) {
		outportMapper.delete(id);
	}

	public Outport selectID(Integer id) {
		Outport outport = outportMapper.selectID(id);
		return outport;
	}


	public List<Outport> select(Integer providerid, Integer goodsid, Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Outport> select = outportMapper.select(providerid, goodsid, index, limit);
		return select;
	}

	public void insert(String paytype, Date outputtime, String operateperson, Integer number, String remark,
			Double outportprice, Integer providerid, Integer goodsid) {
		// TODO 自动生成的方法存根
		outportMapper.insert(paytype, outputtime, operateperson, number, remark, outportprice, providerid, goodsid);
	}

	

}
