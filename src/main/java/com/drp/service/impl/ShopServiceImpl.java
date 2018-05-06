package com.drp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.dao.AdminUserDao;
import com.drp.data.dao.OperationLogDao;
import com.drp.data.entity.AdminUser;
import com.drp.data.entity.OperationLog;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Shop;
import com.drp.data.dao.ShopDao;
import com.drp.service.ShopService;
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
@Service("shopService")
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Autowired
	private AdminUserDao adminUserDao;

	@Autowired
	private OperationLogDao operationLogDao;

	@Override
	public Object save(Shop shop) {
		return this.shopDao.insert(shop);
	}

	@Override
	public JSONObject delete(Integer shopNum) {
		JSONObject result = new JSONObject();
		Shop shop = new Shop(shopNum);
		shop = shopDao.get(shop);
		if (shop == null) {
			result.put("msg", "该门店不存在");
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_id", shopNum);
		List<AdminUser> list = adminUserDao.getList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			result.put("msg", "门店下还存在用户，不能删除");
			return result;
		}
		shopDao.delete(shop);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("删除门店" + shop.getName());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject update(Shop shop) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_num", shop.getShopNum());
		List<Shop> list = shopDao.getList(map);
		if (CollectionUtils.isEmpty(list)) {
			result.put("msg", "该门店不存在");
			return result;
		}
		Shop oldShop = list.get(0);
		oldShop.setName(shop.getName());
		oldShop.setPhone(shop.getPhone());
		oldShop.setArea(shop.getArea());
		oldShop.setAddress(shop.getAddress());
		oldShop.setRemark(shop.getRemark());
		oldShop.setStatus("ON");
		oldShop.setUpdateBy(UserUtil.getCurUserId());
		oldShop.setUpdateTime(new Date());
		shopDao.update(oldShop);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("修改门店" + shop.getName());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	public Shop get(Shop shop) {
		return this.shopDao.get(shop);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject add(Shop shop) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_num", shop.getShopNum());
		List<Shop> list = shopDao.getList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			if (shop.getShopNum() != null) {
				result.put("msg", "该门店已存在");
				return result;
			}
		}
		shop.setCreateBy(UserUtil.getCurUserId());
		shop.setCreateTime(new Date());
		shop.setStatus("ON");
		shop.setUpdateBy(UserUtil.getCurUserId());
		shop.setUpdateTime(new Date());
		shopDao.insert(shop);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("添加门店" + shop.getName());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	public Page<Shop> find(PageParam pageParam) {
		return shopDao.find(pageParam);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateStatus(Integer shopNum) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_num", shopNum);
		List<Shop> list = shopDao.getList(map);
		if (CollectionUtils.isEmpty(list)) {
			result.put("msg", "该门店不存在");
			return result;
		}
		Shop shop = list.get(0);
		if (shop.getStatus().equals("ON")) shop.setStatus("OFF");
		else shop.setStatus("ON");
		shop.setUpdateTime(new Date());
		shop.setUpdateBy(UserUtil.getCurUserId());
		shopDao.update(shop);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("修改门店状态" + shop.getName());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	public List<Shop> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "ON");
		return shopDao.getList(map);
	}

}