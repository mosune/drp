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
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //
	private Integer shopId; //门店Id
	private String name; //岗位名称
	private String type; //岗位类型
	private String status; //状态：VALID／INVALID
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	private Date sysCreateTime; //
	private Date sysUpdateTime; //
	
	public Role() {
		super();
	}

	public Role(Integer id) {
		this.id = id;
	}

}