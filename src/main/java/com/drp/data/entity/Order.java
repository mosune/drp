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
	private BigDecimal totalPrice; //总金额
	//状态：待入库：01，已入库：02，采购已取消：03,采购退货：04，采购退货成功：05，退货取消：06，
	// 待出库：11，已出库：12，销售已取消：13,销售退货：14，销售退货成功：15，销售退货取消：16
	private Integer status;
	private Date createTime; //
	private Date outTime; //
	private Date inTime; //
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