package com.drp.data.dao.impl;

import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.drp.data.entity.Shop;
import com.drp.data.dao.ShopDao;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("shopDao")
public class ShopDaoImpl extends BaseDaoImpl<Shop> implements ShopDao {

    @Override
    public Page<Shop> find(PageParam pageParam) {
        Page<Shop> page = new Page();
        page.setRows(getSqlSession().<Shop>selectList(getSqlName("selectPage"), pageParam.getMap(), new RowBounds(pageParam.getOffset(), pageParam.getPageSize())));
        Integer count = getSqlSession().selectOne(getSqlName("getCount"), pageParam.getMap());
        page.setTotal(count == null ? 0 : count);
        return page;
    }
}