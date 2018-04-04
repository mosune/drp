package com.drp.data.dao;

import com.drp.data.entity.Category;
import com.drp.util.Page;
import com.drp.util.PageParam;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface CategoryDao extends BaseDao<Category> {

    /**
     * 获取分页列表
     * @param pageParam
     * @return
     */
    Page<Category> find(PageParam pageParam);

    /**
     * 获取第一级别的列表
     * @param map
     * @return
     */
    List<Category> getTopLevel(Map<String, Object> map);

    /**
     * 获取第二级列表
     * @param map
     * @return
     */
    Object getSecondLevel(Map<String, Object> map);
}