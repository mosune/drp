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
	
	private Integer id; //
	private Integer shopId; //门店Id
	private String name; //名称
	private String remark; //描述
	private Integer status; //状态：0，1
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	
	public Category() {
		super();
	}

	public Category(Integer id) {
		this.id = id;
	}

}