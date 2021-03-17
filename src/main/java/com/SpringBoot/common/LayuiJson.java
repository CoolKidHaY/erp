package com.SpringBoot.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LayuiJson<T> implements Serializable{

	private Integer code ;
	
	private String msg;

	/**
	 * 总页数
	 */
	private Long count;

	/**
	 * 数据
	 */
	private List<T> data;

	public LayuiJson(List<T> data, Long count, Integer code){
		this.data = data;
		this.count = count;
		this.code = code;
	}

}
