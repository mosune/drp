package com.drp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.dao.AdminUserDao;
import com.drp.data.dao.OperationLogDao;
import com.drp.data.dao.RelationDao;
import com.drp.data.entity.AdminUser;
import com.drp.data.entity.OperationLog;
import com.drp.data.entity.Relation;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Role;
import com.drp.data.dao.RoleDao;
import com.drp.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private AdminUserDao adminUserDao;

	@Autowired
	private RelationDao relationDao;

	@Autowired
	private OperationLogDao operationLogDao;

	@Override
	public Object save(Role role) {
		return this.roleDao.insert(role);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject delete(Integer id) {
		JSONObject result = new JSONObject();
		Role role = new Role(id);
		Role oldRole = roleDao.get(role);
		if (oldRole == null) {
			result.put("msg", "角色不存在");
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_id", id);
		List<AdminUser> list = adminUserDao.getList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			result.put("msg", "角色还存在用户，不能删除");
			return result;
		}
		roleDao.delete(role);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("删除角色" + oldRole.getName());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject update(Role role) {
		JSONObject result = new JSONObject();
		Role oldRole = roleDao.get(role);
		if (oldRole == null) {
			result.put("msg", "角色不存在");
			return result;
		}
		oldRole.setName(role.getName());
		oldRole.setUpdateBy(UserUtil.getCurUserId());
		oldRole.setUpdateTime(new Date());
		roleDao.update(oldRole);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("修改角色" + oldRole.getName());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	public Role get(Role role) {
		return this.roleDao.get(role);
	}

	@Override
	public Page<Role> find(PageParam pageParam) {
		return roleDao.find(pageParam);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject add(Role role) {
		role.setStatus("0");
		role.setCreateBy(UserUtil.getCurUserId());
		role.setCreateTime(new Date());
		role.setUpdateBy(UserUtil.getCurUserId());
		role.setUpdateTime(new Date());
		roleDao.insert(role);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("添加角色" + role.getName());
		operationLogDao.insert(operationLog);
		return new JSONObject();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateStatus(Integer id) {
		JSONObject result = new JSONObject();
		Role role = new Role(id);
		role = roleDao.get(role);
		if (role == null) {
			result.put("msg", "角色不存在");
			return result;
		}
		if (role.getStatus().equals("0")) role.setStatus("1");
		else role.setStatus("0");
		roleDao.update(role);

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("添加角色状态" + role.getName());
		operationLogDao.insert(operationLog);
		return result;
	}

	@Override
	public List<Role> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "0");
		List<Role> list = roleDao.getList(map);
		Iterator<Role> it = list.iterator();
		while (it.hasNext()) {
			Role role = it.next();
			if (role.getId() == 1) {
				it.remove();
				break;
			}
		}
		return list;
	}

	@Override
	public JSONObject getData(Integer id) {
		JSONObject result = new JSONObject();
		Role role = new Role(id);
		role = roleDao.get(role);
		result.put("role", role);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject savePower(int id, String power) {
		JSONObject result = new JSONObject();
		power = power.substring(0, power.length() - 1);
		String[] menus = power.split(",");
		relationDao.deleteByRoleId(id);
		for (String m : menus) {
			Relation relation = new Relation();
			relation.setRoleId(id);
			relation.setMenuId(Integer.valueOf(m));
			relationDao.insert(relation);
		}

		OperationLog operationLog = new OperationLog();
		operationLog.setAdminUserId(UserUtil.getCurUserId());
		operationLog.setCreateBy(UserUtil.getCurUserId());
		operationLog.setCreateTime(new Date());
		operationLog.setShopId(UserUtil.getCurShopId());
		operationLog.setDescCode("修改角色权限" + id);
		operationLogDao.insert(operationLog);
		return result;
	}

}