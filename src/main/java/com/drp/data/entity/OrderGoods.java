package com.drp.data.entity;

import lombok.Data;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:21
 */
@Data
public class OrderGoods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id; //
	private Integer orderId; //申请单Id
	private Integer goodsId; //采购数量
	private BigDecimal quantity; //金额
	private Date createTime; //
	private String createBy; //
	private String updateBy; //
	private Date updateTime; //
	
	public OrderGoods() {
		super();
	}

	public OrderGoods(String id) {
		this.id = id;
	}

}