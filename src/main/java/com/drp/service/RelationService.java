package com.drp.service;

import java.util.List;
import java.util.Map;

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

}