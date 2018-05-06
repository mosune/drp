package com.drp.service;

import com.drp.data.entity.GoodsStockLog;
import com.drp.util.Page;
import com.drp.util.PageParam;

import java.util.HashMap;
import java.util.List;

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

	/**
	 * 查询列表
	 * @param pageParam
	 * @return
	 */
    Page<GoodsStockLog> find(PageParam pageParam);

    List<GoodsStockLog> getList(HashMap<String,Object> map);
}