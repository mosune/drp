package com.drp.data.dao.impl;

import com.drp.data.dao.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * FileName: BaseDaoImpl
 *
 * @author gcg
 * @create 2017/12/29 14:39
 * Description: base dao impl
 * History:
 **/
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    public static final String SQL_SELECT = "select";
    public static final String SQL_SELECT_BY = "selectBy";
    public static final String SQL_SELECT_PAGE = "findPage";
    public static final String SQL_SELECT_PAGECOUNT = "findPageCount";
    public static final String SQL_INSERT = "insert";
    public static final String SQL_DELETE = "delete";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_BATCH_INSERT = "batchInsert";

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @SuppressWarnings("unchecked")
    public T get(T entity) {
        if (entity == null) {
            return null;
        }
        Object result = this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT), entity);
        if (result == null) {
            return null;
        }
        return (T) result;
    }

    public int insert(T entity) {
        if (entity == null) {
            throw new RuntimeException("T is null");
        }
        int result = this.getSqlSession().insert(this.getSqlName(SQL_INSERT), entity);
        return result;
    }

    public int insert(List<T> list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        this.getSqlSession().insert(this.getSqlName(SQL_BATCH_INSERT), list);
        return list.size();
    }

    public int update(T entity) {
        if (entity == null) {
            throw new RuntimeException("");
        }
        int result = this.getSqlSession().update(this.getSqlName(SQL_UPDATE), entity);
        return result;
    }

    public int update(List<T> list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        int row, result = 0;
        for (T t : list) {
            row = this.update(t);
            result += row;
        }
        return result;
    }

    public int delete(T entity) {
        if (entity == null) {
            throw new RuntimeException("");
        }

        int result = this.getSqlSession().delete(this.getSqlName(SQL_DELETE), entity);

        return result;
    }

    /**
     * 拼接查询字符串
     * @param name 对应id
     * @return 查询字符串
     */
    String getSqlName(String name) {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName());
        sb.append(".");
        sb.append(name);
        return sb.toString();
    }

}
