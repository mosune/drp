package com.drp.data.dao;

import java.util.List;
import java.util.Map;

/**
 * FileName: BaseDao
 *
 * @author gcg
 * @create 2017/12/29 14:38
 * Description: base dao
 * History:
 **/
public interface BaseDao<T> {

    /**
     * 根据条件获取一个元素
     * @param paramMap
     * @return
     */
    T get(T entity);

    /**
     * 插入记录
     * @param entity
     */
    int insert(T entity);

    /**
     * 插入记录（批量）
     * @param list
     * @return
     */
    /*int insert(List<T> list);*/

    /**
     * 更新记录
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 更新记录（批量）
     * @param list
     * @return
     */
    int update(List<T> list);

    /**
     * 删除记录
     * @param entity
     * @return
     */
    int delete(T entity);

    /**
     * 通过参数查询列表
     * @param map
     * @return
     */
    List<T> getList(Map<String, Object> map);

}
