package com.drp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.dao.OperationLogDao;
import com.drp.data.entity.OperationLog;
import com.drp.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.AdminUser;
import com.drp.data.dao.AdminUserDao;
import com.drp.service.AdminUserService;
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
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;

	@Autowired
	private OperationLogDao operationLogDao;

	@Override
	public Object save(AdminUser adminUser) {
		return this.adminUserDao.insert(adminUser);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delete(String id) {
		JSONObject result = new JSONObject();
		AdminUser oldUser = new AdminUser(id);
		oldUser = adminUserDao.get(oldUser);
		if (oldUser == null) {
			result.put("msg", "账户不存在");
			return result;
		}
		// if (oldUser.getRoleId())
		adminUserDao.delete(oldUser);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("删除" + oldUser.getAccount());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject update(AdminUser adminUser) {
		JSONObject result = new JSONObject();
		AdminUser oldUser = adminUserDao.get(adminUser);
		if (oldUser == null) {
			result.put("msg", "账户不存在");
			return result;
		}
		oldUser.setShopId(adminUser.getShopId());
		oldUser.setName(adminUser.getName());
		oldUser.setMobile(adminUser.getMobile());
		oldUser.setRoleId(adminUser.getRoleId());
		oldUser.setUpdateTime(new Date());
		oldUser.setUpdateBy(UserUtil.getCurUserId());
		adminUserDao.update(oldUser);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("修改" + adminUser.getAccount());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	public AdminUser get(AdminUser adminUser) {
		return this.adminUserDao.get(adminUser);
	}

	@Override
	public Page<AdminUser> find(PageParam pageParam) {
		return adminUserDao.find(pageParam);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject add(AdminUser adminUser) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", adminUser.getAccount());
		List<AdminUser> list = adminUserDao.getList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			result.put("msg", "该账户已存在");
			return result;
		}

		adminUser.setId(IDUtils.getUUID());
		adminUser.setPassword("123456");
		adminUser = PasswordHelperUtil.encryptPassword(adminUser);
		adminUser.setStatus("0");
		adminUser.setCreateBy(UserUtil.getCurUserId());
		adminUser.setCreateTime(new Date());
		adminUser.setUpdateBy(UserUtil.getCurUserId());
		adminUser.setUpdateTime(new Date());
		adminUserDao.insert(adminUser);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("添加" + adminUser.getAccount());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateStatus(String id) {
		JSONObject result = new JSONObject();
		AdminUser oldUser = new AdminUser(id);
		oldUser = adminUserDao.get(oldUser);
		if (oldUser == null) {
			result.put("msg", "账户不存在");
			return result;
		}
		if (oldUser.getStatus().equals("0")) oldUser.setStatus("1");
		else oldUser.setStatus("0");
		adminUserDao.update(oldUser);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("修改状态" + oldUser.getAccount());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updatePwd(String oldPassword, String newPassword, String confirmPassword) {
		JSONObject result = new JSONObject();
		AdminUser adminUser = UserUtil.getCurUser();
		if (!PasswordHelperUtil.checkPassword(oldPassword, adminUser)) {
			result.put("msg", "密码错误");
			return result;
		}
		if (!newPassword.equals(confirmPassword)) {
			result.put("msg", "密码不相同");
			return result;
		}
		adminUser.setPassword(newPassword);
		adminUser = PasswordHelperUtil.encryptPassword(adminUser);
		adminUserDao.update(adminUser);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("修改密码" + adminUser.getAccount());
		operationLogDao.insert(operationLog);
		return result;
	}

}