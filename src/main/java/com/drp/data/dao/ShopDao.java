package com.drp.data.dao;

import com.drp.data.entity.Shop;
import com.drp.util.Page;
import com.drp.util.PageParam;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface ShopDao extends BaseDao<Shop> {

    /**
     * 查询列表
     * @param pageParam
     * @return
     */
    Page<Shop> find(PageParam pageParam);
}