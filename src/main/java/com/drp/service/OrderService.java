package com.drp.service;

import com.drp.data.entity.Order;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
public interface OrderService {

	/**
	 * 保存
	 * @param order
	 * @return
	 */
	Object save(Order order);
	
	/**
	 * 删除（按主键）
	 * @param order
	 * @return
	 */
	int delete(Order order);

	/**
	 * 修改（按主键）
	 * @param order
	 * @return
	 */
	int update(Order order);

	/**
	 * 获取（按主键）
	 * @param order
	 * @return
	 */
	Order get(Order order);
	
}