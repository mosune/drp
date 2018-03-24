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
public class OperationLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //
	private String adminUserId; //登录账号id
	private String descCode; //操作描述code
	private Integer shopId; //门店Id
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	
	public OperationLog() {
		super();
	}

	public OperationLog(Integer id) {
		this.id = id;
	}

}