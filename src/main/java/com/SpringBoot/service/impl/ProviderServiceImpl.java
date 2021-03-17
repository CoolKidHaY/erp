package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Provider;
import com.SpringBoot.mapper.ProviderMapper;
import com.SpringBoot.service.ProviderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {

	@Autowired
	private ProviderMapper providerMapper;
	

	public void insert(String providername, String zip, String address, String telephone, String connectionperson,
			String phone, String bank, String account, String email, String fax, Integer available) {
		providerMapper.insert(providername, zip, address, telephone, connectionperson, phone, bank, account, email, fax, available);
	}


	public void update(Integer id, String providername, String zip, String address, String telephone,
			String connectionperson, String phone, String bank, String account, String email, String fax,
			Integer available) {
		providerMapper.update(id, providername, zip, address, telephone, connectionperson, phone, bank, account, email, fax, available);
	}


	public void delete(Integer id) {
		providerMapper.delete(id);
	}


	public List<Provider> select(String providername, String connectionperson, String phone, Integer index,
			Integer limit) {
		List<Provider> select = providerMapper.select(providername, connectionperson, phone, index, limit);
		return select;
	}


	public List<Provider> selectAvailable() {
		// TODO 自动生成的方法存根
		List<Provider> selectAvailable = providerMapper.selectAvailable();
		return selectAvailable;
	}

}
