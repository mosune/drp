package com.drp.service.impl;

import com.drp.util.Page;
import com.drp.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.AdminUser;
import com.drp.data.dao.AdminUserDao;
import com.drp.service.AdminUserService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;

	@Override
	public Object save(AdminUser adminUser) {
		return this.adminUserDao.insert(adminUser);
	}

	@Override
	public int delete(AdminUser adminUser) {
		return this.adminUserDao.delete(adminUser);
	}

	@Override
	public int update(AdminUser adminUser) {
		return this.adminUserDao.update(adminUser);
	}

	@Override
	public AdminUser get(AdminUser adminUser) {
		return this.adminUserDao.get(adminUser);
	}

	@Override
	public Page<AdminUser> find(PageParam pageParam) {
		return adminUserDao.find(pageParam);
	}

}