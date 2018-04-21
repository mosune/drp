package com.drp.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Goods;
import com.drp.data.entity.GoodsStock;
import com.drp.util.Page;
import com.drp.util.PageParam;

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


	/**
	 * 获取首页列表
	 * @param pageParam
	 * @return
	 */
	Page<GoodsStock> find(PageParam pageParam);

	/**
	 * 采购单入库确认
	 * @param orderId
	 * @return
	 */
	JSONObject purchaseIn(String orderId,String type);
}