package com.SpringBoot.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LayuiJson<T> implements Serializable{

	private Integer code ;
	
	private String msg;

	/**
	 * 总页数
	 */
	private Long total;

	/**
	 * 数据
	 */
	private List<T> rows;

	private Object otherData;

	public LayuiJson() {
	}

	public LayuiJson(Integer code, String msg, Long total, List<T> rows) {
		this.code = code;
		this.msg = msg;
		this.total = total;
		this.rows = rows;
	}

	public LayuiJson(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public LayuiJson(List<T> rows, Long total, Integer code){
		this.rows = rows;
		this.total = total;
		this.code = code;
	}

	public LayuiJson(Integer code, String msg, List<T> rows) {
		this.code = code;
		this.msg = msg;
		this.rows = rows;
	}

	/**
	 * 响应返回结果
	 *
	 * @param rows 影响行数
	 * @return 操作结果
	 */
	public static LayuiJson toAjax(int rows)
	{
		return rows > 0 ? success() : error();
	}

	/**
	 * 响应返回结果
	 *
	 * @param result 结果
	 * @return 操作结果
	 */
	public static LayuiJson toAjax(boolean result)
	{
		return result ? success() : error();
	}

	/**
	 * 返回成功
	 */
	public static LayuiJson success(Long total, List rows)
	{
		return new LayuiJson(Constast.OK, Constast.OK_MSG,total, rows);
	}

	/**
	 * 返回成功
	 */
	public static LayuiJson success(List rows)
	{
		return new LayuiJson(Constast.OK, Constast.OK_MSG, rows);
	}

	/**
	 * 返回成功
	 */
	public static LayuiJson success()
	{
		return new LayuiJson(Constast.OK, Constast.OK_MSG);
	}

	/**
	 * 返回失败消息
	 */
	public static LayuiJson error()
	{
		return new LayuiJson(Constast.ERROR, Constast.ERROR_MSG);
	}

	/**
	 * 返回成功消息
	 */
	public static LayuiJson success(String msg)
	{
		return new LayuiJson(Constast.OK, msg);
	}

	/**
	 * 返回失败消息
	 */
	public static LayuiJson error(String msg)
	{
		return new LayuiJson(Constast.ERROR, msg);
	}

	/**
	 * 返回错误码消息
	 */
	public static LayuiJson error(int code, String msg)
	{
		return new LayuiJson(code, msg);
	}


}
