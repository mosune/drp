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
public class AdminUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id; //
	private String account; //登录账号
	private String password; //登录密码
	private Integer shopId; //门店Id
	private String name; //姓名
	private String mobile; //电话
	private String roleId; //角色
	private String status; //状态：是否可用
	private String createBy; //
	private Date createTime; //
	private String updateBy; //
	private Date updateTime; //
	private String salt;
	
	public AdminUser() {
		super();
	}

	public AdminUser(String id) {
		this.id = id;
	}

}