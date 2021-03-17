package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Customer;
import com.SpringBoot.mapper.CustomerMapper;
import com.SpringBoot.service.CustomerService;
import com.SpringBoot.vo.CustomerVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * 查询顾客列表
	 * @param customerVo
	 * @return
	 */
	@Override
	public Page<Customer> select(CustomerVo customerVo) {
		QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>().like(customerVo.getCustomername()!=null,"customername", customerVo.getCustomername())
				.like( customerVo.getConnectionpersion() != null, "connectionpersion", customerVo.getConnectionpersion())
				.like(customerVo.getPhone() != null, "phone", customerVo.getPhone());
		Page<Customer> page = this.page(new Page<Customer>(customerVo.getCurrentPage(), customerVo.getPageSize()), queryWrapper);
		return page;
	}

	public List<Customer> selectName() {
		// TODO 自动生成的方法存根
		List<Customer> selectName = customerMapper.selectName();
		return selectName;
	}

	public void insert(String customername, String zip, String address, String telephone, String connectionpersion,
			String phone, String bank, String account, String email, String fax, Integer available) {
		// TODO 自动生成的方法存根
		customerMapper.insert(customername, zip, address, telephone, connectionpersion, phone, bank, account, email, fax, available);
	}

	public void update(Integer id, String customername, String zip, String address, String telephone,
			String connectionpersion, String phone, String bank, String account, String email, String fax,
			Integer available) {
		// TODO 自动生成的方法存根
		customerMapper.update(id, customername, zip, address, telephone, connectionpersion, phone, bank, account, email, fax, available);
		
	}

	public void delete(Integer id) {
		// TODO 自动生成的方法存根
		customerMapper.delete(id);
	}
	
}
