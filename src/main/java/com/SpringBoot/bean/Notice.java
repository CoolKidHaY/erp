package com.SpringBoot.bean;

import java.io.Serializable;
import java.util.Date;
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
@TableName("sys_notice")
public class Notice {

	private Integer id;

    private String title;

    private String content;

    private Date createtime;

    private String opername;

	
}
