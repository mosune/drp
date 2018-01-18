package com.drp.data.dao.impl;

import com.drp.data.dao.UserDao;
import com.drp.data.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FileName: UserDaoImpl
 *
 * @author gcg
 * @create 2017/12/29 14:28
 * Description: user dao impl
 * History:
 **/
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public List<User> list() {
        return this.getSqlSession().selectList(getSqlName("list"));
    }

    public User findUserByName(String userName) {
        return this.getSqlSession().selectOne(getSqlName("findUserByName"), userName);
    }

    public Set<String> findRoleByName(String userName) {
        List<String> list = this.getSqlSession().selectList(getSqlName("findRoleByName"), userName);
        return new HashSet<String>(list);
    }

}
