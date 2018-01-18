package com.drp.data.dao.impl;

import com.drp.data.dao.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FileName: BaseDaoImpl
 *
 * @author gcg
 * @create 2017/12/29 14:39
 * Description: base dao impl
 * History:
 **/
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
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
