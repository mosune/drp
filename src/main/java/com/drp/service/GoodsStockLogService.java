package com.drp.service;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.GoodsStockLog;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface GoodsStockLogService {

	/**
	 * 保存
	 * @param goodsStockLog
	 * @return
	 */
	Object save(GoodsStockLog goodsStockLog);
	
	/**
	 * 删除（按主键）
	 * @param goodsStockLog
	 * @return
	 */
	int delete(GoodsStockLog goodsStockLog);

	/**
	 * 修改（按主键）
	 * @param goodsStockLog
	 * @return
	 */
	int update(GoodsStockLog goodsStockLog);

	/**
	 * 获取（按主键）
	 * @param goodsStockLog
	 * @return
	 */
	GoodsStockLog get(GoodsStockLog goodsStockLog);

}