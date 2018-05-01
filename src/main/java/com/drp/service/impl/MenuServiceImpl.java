package com.drp.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Menu;
import com.drp.data.dao.MenuDao;
import com.drp.service.MenuService;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public Object save(Menu menu) {
		return this.menuDao.insert(menu);
	}

	@Override
	public int delete(Menu menu) {
		return this.menuDao.delete(menu);
	}

	@Override
	public int update(Menu menu) {
		return this.menuDao.update(menu);
	}

	@Override
	public Menu get(Menu menu) {
		return this.menuDao.get(menu);
	}

	@Override
	public List<Menu> getParentList() {
		List<Menu> menus = menuDao.getParentMenu();
		Iterator<Menu> it = menus.iterator();
		while (it.hasNext()) {
			Menu menu = it.next();
			if (menu.getId() == 1) {
				it.remove();
				break;
			}
		}
		return menus;
	}

}