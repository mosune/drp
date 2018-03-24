package com.drp.service;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.Goods;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface GoodsService {

	/**
	 * 保存
	 * @param goods
	 * @return
	 */
	Object save(Goods goods);
	
	/**
	 * 删除（按主键）
	 * @param goods
	 * @return
	 */
	int delete(Goods goods);

	/**
	 * 修改（按主键）
	 * @param goods
	 * @return
	 */
	int update(Goods goods);

	/**
	 * 获取（按主键）
	 * @param goods
	 * @return
	 */
	Goods get(Goods goods);

}