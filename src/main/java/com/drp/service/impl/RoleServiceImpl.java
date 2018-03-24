package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Role;
import com.drp.data.dao.RoleDao;
import com.drp.service.RoleService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Object save(Role role) {
		return this.roleDao.insert(role);
	}

	@Override
	public int delete(Role role) {
		return this.roleDao.delete(role);
	}

	@Override
	public int update(Role role) {
		return this.roleDao.update(role);
	}

	@Override
	public Role get(Role role) {
		return this.roleDao.get(role);
	}
	
}