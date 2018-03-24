package com.drp.service;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.GoodsStock;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface GoodsStockService {

	/**
	 * 保存
	 * @param goodsStock
	 * @return
	 */
	Object save(GoodsStock goodsStock);
	
	/**
	 * 删除（按主键）
	 * @param goodsStock
	 * @return
	 */
	int delete(GoodsStock goodsStock);

	/**
	 * 修改（按主键）
	 * @param goodsStock
	 * @return
	 */
	int update(GoodsStock goodsStock);

	/**
	 * 获取（按主键）
	 * @param goodsStock
	 * @return
	 */
	GoodsStock get(GoodsStock goodsStock);

}