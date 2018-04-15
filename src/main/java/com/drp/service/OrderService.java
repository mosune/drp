package com.drp.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Order;
import com.drp.util.Page;
import com.drp.util.PageParam;

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

	/**
	 * 获取列表
	 * @param pageParam
	 * @return
	 */
    Page<Order> find(PageParam pageParam);

	/**
	 * 添加订货单
	 * @param json
	 * @return
	 */
	JSONObject addOrder(JSONArray json);
}