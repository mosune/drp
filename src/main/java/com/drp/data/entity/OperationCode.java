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
public class OperationCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //
	private String code; //code
	private String descCode; //操作描述code
	private String status; //状态：VALID／INVALID
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	
	public OperationCode() {
		super();
	}

	public OperationCode(Integer id) {
		this.id = id;
	}

}