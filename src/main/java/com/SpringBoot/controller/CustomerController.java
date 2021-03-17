package com.SpringBoot.controller;

import java.util.List;

import com.SpringBoot.service.impl.CustomerServiceImpl;
import com.SpringBoot.vo.CustomerVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.bean.Customer;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@RequestMapping("loadAllCustomer")
	public LayuiJson<Customer> loadAllCustomer(CustomerVo customerVo){
        Page<Customer> page = customerServiceImpl.select(customerVo);
        LayuiJson<Customer> resultData = new LayuiJson<>(page.getRecords(),page.getTotal(), 0 );
		return resultData;
	}
	
	@RequestMapping("loadAllCustomerForSelect")
	public DataGridView loadAllCustomerForSelect(){
		
		List<Customer> list = customerServiceImpl.selectName();
		return new DataGridView(list);
	}
	
	
	@RequestMapping("updateCustomer")
    public ResultObj updateCustomer(Integer id, String customername, String zip, String address, String telephone,
			String connectionpersion, String phone, String bank, String account, String email, String fax,
			Integer available){
        try {

            customerServiceImpl.update(id, customername, zip, address, telephone,
            		connectionpersion, phone, bank, account, email, fax, available);
            
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
	
	@RequestMapping("addCustomer")
    public ResultObj addCustomer(String customername, String zip, String address, String telephone, String connectionpersion,
			String phone, String bank, String account, String email, String fax, Integer available){
        try {
            customerServiceImpl.insert(customername, zip, address, telephone, connectionpersion,
            		phone, bank, account, email, fax, available);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
	
	@RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(Integer id){
        try {
            customerServiceImpl.delete(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
