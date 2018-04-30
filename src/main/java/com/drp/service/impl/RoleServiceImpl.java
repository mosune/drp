package com.drp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.dao.AdminUserDao;
import com.drp.data.dao.MenuDao;
import com.drp.data.entity.AdminUser;
import com.drp.data.entity.Menu;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Role;
import com.drp.data.dao.RoleDao;
import com.drp.service.RoleService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private MenuDao menuDao;

	@Override
	public Object save(Role role) {
		return this.roleDao.insert(role);
	}

	@Override
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
		return result;
	}

	@Override
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
	public JSONObject add(Role role) {
		role.setStatus("0");
		role.setCreateBy(UserUtil.getCurUserId());
		role.setCreateTime(new Date());
		role.setUpdateBy(UserUtil.getCurUserId());
		role.setUpdateTime(new Date());
		roleDao.insert(role);
		return new JSONObject();
	}

	@Override
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
		return result;
	}

	@Override
	public List<Role> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		return roleDao.getList(map);
	}

	@Override
	public JSONObject getData(Integer id) {
		JSONObject result = new JSONObject();
		Role role = new Role(id);
		role = roleDao.get(role);
		result.put("role", role);
		List<Menu> list = menuDao.getParentMenu();
		result.put("menuList", list);
		return result;
	}

}