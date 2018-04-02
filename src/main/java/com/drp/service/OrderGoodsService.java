package com.drp.service;

import com.drp.data.entity.OrderGoods;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
public interface OrderGoodsService {

	/**
	 * 保存
	 * @param orderGoods
	 * @return
	 */
	Object save(OrderGoods orderGoods);
	
	/**
	 * 删除（按主键）
	 * @param orderGoods
	 * @return
	 */
	int delete(OrderGoods orderGoods);

	/**
	 * 修改（按主键）
	 * @param orderGoods
	 * @return
	 */
	int update(OrderGoods orderGoods);

	/**
	 * 获取（按主键）
	 * @param orderGoods
	 * @return
	 */
	OrderGoods get(OrderGoods orderGoods);
	
}