package com.drp.data.entity;

import lombok.Data;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;
import java.util.Date;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:07
 */
@Data
public class GoodsStockLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //
	private Integer shopId; //门店Id
	private String goodsId; //商品Id
	private Integer previousStock; //原始库存
	private Integer quantity; //进货
	private Integer currentStock; //当前库存
	private String type; //类型：in 进货| out 出库
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	
	public GoodsStockLog() {
		super();
	}

	public GoodsStockLog(Integer id) {
		this.id = id;
	}

}