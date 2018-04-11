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
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id; //
	private String userId; // 采购人id
	private Integer shopId; //门店Id
	private String number; //单号
	private Integer quantity; //采购数量
	private BigDecimal totalPrice; //总金额
	private Integer status; //状态：待出库，待入库，已入库，已取消，已出库
	private Date createTime; //
	private Date outTime; //
	private Date inTime; //
	private String remark; //描述
	private String createBy; //
	private String updateBy; //
	private Date updateTime; //
	
	public Order() {
		super();
	}

	public Order(String id) {
		this.id = id;
	}

}