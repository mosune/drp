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
 * @date 2018-03-24 24:53:07
 */
@Data
public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id; //
	private Integer shopId; //门店Id
	private String name; //商品名称
	private String number; //批次号
	private String desc; //描述
	private String cateId; //售卖类目id
	private BigDecimal salePrice; //售卖价格
	private BigDecimal originalPrice; //成本价格
	private Integer stock; //库存
	private String url; //图片
	private Date expiryTime; //过期时间
	private String status; //上下架状态 WAIT/ON/OFF
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	
	public Goods() {
		super();
	}

	public Goods(String id) {
		this.id = id;
	}

}