package com.drp.service.impl;

import com.drp.data.dao.RoleDao;
import com.drp.data.dao.UserDao;
import com.drp.data.entity.User;
import com.drp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FileName: UserServiceImpl
 *
 * @author gcg
 * @create 2017/12/25 14:31
 * Description: user service implement
 * History:
 **/
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public List<User> getList() {
        return this.userDao.list();
    }

    public User findUserByName(String userName) {
        return this.userDao.findUserByName(userName);
    }

    public Set<String> findRoleByName(String userName) {
        return this.userDao.findRoleByName(userName);
    }

    public Set<String> findPerByName(String userName) {
        List<Integer> roleIds = this.roleDao.getRoleIdByUserName(userName);
        List<Object> list = this.roleDao.getPerByRoleIds(roleIds);
        Set<String> set = new HashSet<String>();
        for (Object object : list) { // 通过循环添加集合元素
            set.add((String) object);
        }
        return set;
    }

}
