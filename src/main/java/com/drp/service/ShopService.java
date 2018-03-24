package com.drp.service;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.Shop;

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
	 * @param shop
	 * @return
	 */
	int delete(Shop shop);

	/**
	 * 修改（按主键）
	 * @param shop
	 * @return
	 */
	int update(Shop shop);

	/**
	 * 获取（按主键）
	 * @param shop
	 * @return
	 */
	Shop get(Shop shop);

}