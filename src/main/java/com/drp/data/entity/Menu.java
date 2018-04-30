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
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //
	private String name; //名称
	private String url; //
	private Integer level; //等级
	private Integer parent; //上级

	public Menu() {
		super();
	}

	public Menu(Integer id) {
		this.id = id;
	}

}