package com.drp.data.dao;

import java.util.List;
import java.util.Set;

/**
 * FileName: UserDao
 *
 * @author gcg
 * @create 2017/12/25 14:46
 * Description: user mapper
 * History:
 **/
public interface UserDao {

    /**
     * 查询用户列表
     * @return 用户列表
     */
    List<User> list();

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户
     */
    User findUserByName(String userName);

    /**
     * 通过用户名查询职位集合
     * @param userName 用户名
     * @return 职位code集合
     */
    Set<String> findRoleByName(String userName);
}
