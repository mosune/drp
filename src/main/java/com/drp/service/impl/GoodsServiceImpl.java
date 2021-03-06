package com.drp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.dao.GoodsStockDao;
import com.drp.data.entity.GoodsStock;
import com.drp.util.IDUtils;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Goods;
import com.drp.data.dao.GoodsDao;
import com.drp.service.GoodsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private GoodsStockDao goodsStockDao;

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

	@Override
	public Page<Goods> find(PageParam pageParam) {
		return goodsDao.find(pageParam);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addOrUpdate(Goods goods, String originalAmount) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", goods.getName());
		map.put("delete_tag", "0");
		List<Goods> list = goodsDao.getList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			if (StringUtils.isEmpty(goods.getId()) && goods.getId().equals(list.get(0).getId())) {
				result.put("msg", "该名称商品已存在");
				return result;
			}
			if (StringUtils.isEmpty(goods.getId())) {
				result.put("msg", "该名称商品已存在");
				return result;
			}
		}
		if (StringUtils.isEmpty(goods.getId())) {
			goods.setId(IDUtils.getUUID());
			goods.setCreateBy(UserUtil.getCurUserId());
			goods.setCreateTime(new Date());
			goods.setDeleteTag("N");
			goods.setShopId(UserUtil.getCurShopId());
			goods.setStatus("OFF");
			goods.setUpdateBy(UserUtil.getCurUserId());
			goods.setUpdateTime(new Date());
			if (StringUtils.isNotEmpty(originalAmount)) {
				GoodsStock goodsStock = new GoodsStock();
				goodsStock.setId(IDUtils.getUUID());
				goodsStock.setShopId(UserUtil.getCurShopId());
				goodsStock.setGoodsId(goods.getId());
				goodsStock.setOriginalStock(Integer.valueOf(originalAmount));
				goodsStock.setInQuantity(0);
				goodsStock.setOutQuentity(0);
				goodsStock.setCurrentStock(Integer.valueOf(originalAmount));
				goodsStock.setStatus(1);
				goodsStock.setCreateBy(UserUtil.getCurUserId());
				goodsStock.setCreateTime(new Date());
				goodsStock.setUpdateBy(UserUtil.getCurUserId());
				goodsStock.setUpdateTime(new Date());
				goodsStockDao.insert(goodsStock);
			}
			goodsDao.insert(goods);
		} else {
			Goods newGoods = goodsDao.get(goods);
			newGoods.setName(goods.getName());
			newGoods.setCateId(goods.getCateId());
			newGoods.setUpdateTime(new Date());
			newGoods.setUpdateBy(UserUtil.getCurUserId());
			newGoods.setOriginalPrice(goods.getOriginalPrice());
			newGoods.setSalePrice(goods.getSalePrice());
			if (StringUtils.isNotEmpty(goods.getRemark())) newGoods.setRemark(goods.getRemark());
			if (StringUtils.isNotEmpty(originalAmount)) {
				map.clear();
				map.put("shop_id", UserUtil.getCurShopId());
				map.put("goods_id", goods.getId());
				List<GoodsStock> goodsStocks = goodsStockDao.getList(map);
				if (CollectionUtils.isEmpty(goodsStocks)) {
					GoodsStock goodsStock = new GoodsStock();
					goodsStock.setId(IDUtils.getUUID());
					goodsStock.setShopId(UserUtil.getCurShopId());
					goodsStock.setGoodsId(goods.getId());
					goodsStock.setOriginalStock(Integer.valueOf(originalAmount));
					goodsStock.setInQuantity(0);
					goodsStock.setOutQuentity(0);
					goodsStock.setCurrentStock(Integer.valueOf(originalAmount));
					goodsStock.setStatus(1);
					goodsStock.setCreateBy(UserUtil.getCurUserId());
					goodsStock.setCreateTime(new Date());
					goodsStock.setUpdateBy(UserUtil.getCurUserId());
					goodsStock.setUpdateTime(new Date());
					goodsStockDao.insert(goodsStock);
				} else {
					GoodsStock goodsStock = goodsStocks.get(0);
					goodsStock.setOriginalStock(Integer.valueOf(originalAmount));
					goodsStock.setCurrentStock(Integer.valueOf(originalAmount));
					goodsStock.setUpdateBy(UserUtil.getCurUserId());
					goodsStock.setUpdateTime(new Date());
					goodsStockDao.update(goodsStock);
				}
			}
			goodsDao.update(newGoods);
		}
		return result;
	}

	@Override
	public JSONObject deleteLoft(Goods goods) {
		JSONObject result = new JSONObject();
		goods = goodsDao.get(goods);
		if (goods == null) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		if (goods.getDeleteTag().equals("0")) {
			result.put("msg", "该数据已被删除，请刷新重试");
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_id", goods.getId());
		List<GoodsStock> list = goodsStockDao.getList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			GoodsStock goodsStock = list.get(0);
			if (goodsStock.getCurrentStock() != 0) {
				result.put("msg", "该商品还有库存，不能删除");
				return result;
			}
		}
		goods.setDeleteTag("Y");
		goods.setUpdateTime(new Date());
		goods.setUpdateBy(UserUtil.getCurUserId());
		goodsDao.update(goods);
		return result;
	}

	@Override
	public JSONObject updateStatus(Goods goods) {
		JSONObject result = new JSONObject();
		goods = goodsDao.get(goods);
		if (goods == null) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		if (goods.getStatus().equals("ON")) goods.setStatus("OFF");
		else goods.setStatus("ON");
		goods.setUpdateTime(new Date());
		goods.setUpdateBy(UserUtil.getCurUserId());
		goodsDao.update(goods);
		return result;
	}

	@Override
	public List<Goods> getGoodsList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_id", UserUtil.getCurShopId());
		map.put("delete_tag", "N");
		return goodsDao.getList(map);
	}

	@Override
	public JSONObject getdata(Goods goods) {
		JSONObject result = new JSONObject();
		result.put("goods", goodsDao.get(goods));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_id", UserUtil.getCurShopId());
		map.put("goods_id", goods.getId());
		List<GoodsStock> goodsStocks = goodsStockDao.getList(map);
		if (CollectionUtils.isNotEmpty(goodsStocks)) {
			result.put("amount", goodsStocks.get(0).getOriginalStock());
		}
		return result;
	}

}