package com.drp.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * @author gcg
 * 
 * 2017年4月14日 下午4:15:17
 */
@Data
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int total; //总记录数

	private List<T> rows; //当前页结果集

}
