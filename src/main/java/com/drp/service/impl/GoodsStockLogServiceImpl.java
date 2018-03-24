package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.GoodsStockLog;
import com.drp.data.dao.GoodsStockLogDao;
import com.drp.service.GoodsStockLogService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("goodsStockLogService")
public class GoodsStockLogServiceImpl implements GoodsStockLogService {

	@Autowired
	private GoodsStockLogDao goodsStockLogDao;

	@Override
	public Object save(GoodsStockLog goodsStockLog) {
		return this.goodsStockLogDao.insert(goodsStockLog);
	}

	@Override
	public int delete(GoodsStockLog goodsStockLog) {
		return this.goodsStockLogDao.delete(goodsStockLog);
	}

	@Override
	public int update(GoodsStockLog goodsStockLog) {
		return this.goodsStockLogDao.update(goodsStockLog);
	}

	@Override
	public GoodsStockLog get(GoodsStockLog goodsStockLog) {
		return this.goodsStockLogDao.get(goodsStockLog);
	}

}