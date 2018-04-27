package com.drp.service;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Shop;
import com.drp.util.Page;
import com.drp.util.PageParam;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface ShopService {

	/**
	 * 保存
	 * @param shop
	 * @return
	 */
	Object save(Shop shop);
	
	/**
	 * 删除（按主键）
	 * @param shopNum
	 * @return
	 */
	JSONObject delete(Integer shopNum);

	/**
	 * 修改（按主键）
	 * @param shop
	 * @return
	 */
	JSONObject update(Shop shop);

	/**
	 * 获取（按主键）
	 * @param shop
	 * @return
	 */
	Shop get(Shop shop);

	/**
	 * 添加或删除门店
	 * @param shop
	 * @return
	 */
    JSONObject add(Shop shop);

	/**
	 * 列表
	 * @param pageParam
	 * @return
	 */
	Page<Shop> find(PageParam pageParam);
}