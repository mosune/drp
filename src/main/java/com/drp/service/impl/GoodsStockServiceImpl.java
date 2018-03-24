package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.GoodsStock;
import com.drp.data.dao.GoodsStockDao;
import com.drp.service.GoodsStockService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("goodsStockService")
public class GoodsStockServiceImpl implements GoodsStockService {

	@Autowired
	private GoodsStockDao goodsStockDao;

	@Override
	public Object save(GoodsStock goodsStock) {
		return this.goodsStockDao.insert(goodsStock);
	}

	@Override
	public int delete(GoodsStock goodsStock) {
		return this.goodsStockDao.delete(goodsStock);
	}

	@Override
	public int update(GoodsStock goodsStock) {
		return this.goodsStockDao.update(goodsStock);
	}

	@Override
	public GoodsStock get(GoodsStock goodsStock) {
		return this.goodsStockDao.get(goodsStock);
	}

}