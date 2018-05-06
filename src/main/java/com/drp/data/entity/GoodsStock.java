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
public class GoodsStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id; //
	private Integer shopId; //门店Id
	private Integer goodsId; //商品Id
	private Integer originalStock; //原始库存
	private Integer inQuantity; //进货
	private Integer outQuentity; //出货
	private Integer currentStock; //当前库存
	private Integer status; //状态
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	
	public GoodsStock() {
		super();
	}

	public GoodsStock(String id) {
		this.id = id;
	}

}