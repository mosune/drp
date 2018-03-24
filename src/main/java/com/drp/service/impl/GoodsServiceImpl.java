package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Goods;
import com.drp.data.dao.GoodsDao;
import com.drp.service.GoodsService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public Object save(Goods goods) {
		return this.goodsDao.insert(goods);
	}

	@Override
	public int delete(Goods goods) {
		return this.goodsDao.delete(goods);
	}

	@Override
	public int update(Goods goods) {
		return this.goodsDao.update(goods);
	}

	@Override
	public Goods get(Goods goods) {
		return this.goodsDao.get(goods);
	}
	
}