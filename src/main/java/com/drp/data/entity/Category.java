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
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id; //
	private Integer shopId; //门店Id
	private Integer sequence; //
	private String name; //名称
	private String desc; //描述
	private Integer level; //级别
	private Integer status; //状态：0，1
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	
	public Category() {
		super();
	}

	public Category(String id) {
		this.id = id;
	}

}