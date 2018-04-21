package com.drp.data.dao;

import com.drp.data.entity.Goods;
import com.drp.util.Page;
import com.drp.util.PageParam;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface GoodsDao extends BaseDao<Goods> {

    /**
     * 获取分页数据
     * @param pageParam
     * @return
     */
    Page<Goods> find(PageParam pageParam);

    Goods findGoods(Integer id);
}