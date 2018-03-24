package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Shop;
import com.drp.data.dao.ShopDao;
import com.drp.service.ShopService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Override
	public Object save(Shop shop) {
		return this.shopDao.insert(shop);
	}

	@Override
	public int delete(Shop shop) {
		return this.shopDao.delete(shop);
	}

	@Override
	public int update(Shop shop) {
		return this.shopDao.update(shop);
	}

	@Override
	public Shop get(Shop shop) {
		return this.shopDao.get(shop);
	}
	
}