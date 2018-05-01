package com.drp.service;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Relation;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface RelationService {

	/**
	 * 保存
	 * @param relation
	 * @return
	 */
	Object save(Relation relation);
	
	/**
	 * 删除（按主键）
	 * @param relation
	 * @return
	 */
	int delete(Relation relation);

	/**
	 * 修改（按主键）
	 * @param relation
	 * @return
	 */
	int update(Relation relation);

	/**
	 * 获取（按主键）
	 * @param relation
	 * @return
	 */
	Relation get(Relation relation);

	/**
	 * 通过角色id获取关系
	 * @param id
	 * @return
	 */
	JSONObject getRelation(int id);

}