package com.SpringBoot.service;

import com.SpringBoot.vo.CustomerVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.SpringBoot.bean.Customer;



public interface CustomerService extends IService<Customer> {

    Page<Customer> select(CustomerVo customerVo);

}
