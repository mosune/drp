package com.drp.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 分页参数
 * @author gcg
 * 
 * 2017年4月14日 下午4:15:04
 */
@Data
public class PageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1734516368014351348L;

	private int pageNo; // 当前页码
	private int pageSize; // 页大小
	private int offset; //分页记录偏移量
	private Map<String, Object> map; // 查询参数

	public PageParam() {
	}

	public PageParam(int offset, int pageSize, Map<String, Object> map) {
		this.pageSize = pageSize;
		this.offset = offset;
		this.pageNo = offset / pageSize + 1;
		this.map = map;
	}

}
