package com.SpringBoot.bean;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("bus_provider")
public class Provider {

    private Integer id;

    private String providername;

    private String zip;

    private String address;

    private String telephone;

    private String connectionperson;

    private String phone;

    private String bank;

    private String account;

    private String email;

    private String fax;

    private Integer available;

}
