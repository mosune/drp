package com.drp.service;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.GoodsStock;
import com.drp.data.entity.dto.GoodsStockDto;
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
	Page<GoodsStockDto> find(PageParam pageParam);

	/**
	 * 入库出库确认
	 * @param orderId
	 * @return
	 */
	JSONObject stock(String orderId,String type);

	/**
	 * 取消采购单
	 * @param orderId
	 * @param type
	 * @return
	 */
	JSONObject delete(String orderId, String type);
}