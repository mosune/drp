package com.drp.data.entity;

import lombok.Data;

import java.io.Serializable;

import java.lang.String;
import java.util.Date;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:07
 */
@Data
public class Relation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //
	private String menuId; //菜单id
	private String roleId; //角色id

	public Relation() {
		super();
	}

	public Relation(Integer id) {
		this.id = id;
	}

}