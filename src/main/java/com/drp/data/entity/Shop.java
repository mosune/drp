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
public class Shop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer shopNum; // 门店编号
	private String name; //门店名称
	private String phone; //电话
	private String area; //地区
	private String address; //地址
	private String remark; //描述
	private String status; //状态：是否可用
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	
	public Shop() {
		super();
	}

	public Shop(Integer shopNum) {
		this.shopNum = shopNum;
	}

}