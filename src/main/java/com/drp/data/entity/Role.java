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
	private String name; //岗位名称
	private String status; //状态：VALID／INVALID
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //

	public Role() {
		super();
	}

	public Role(Integer id) {
		this.id = id;
	}

}