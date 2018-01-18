package com.drp.service;

import com.drp.data.entity.User;

import java.util.List;
import java.util.Set;

/**
 * FileName: UserService
 *
 * @author gcg
 * @create 2017/12/25 14:27
 * Description: user service
 * History:
 **/
public interface UserService {

    /**
     * 查询用户列表
     * @return 用户集合
     */
    List<User> getList();

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户
     */
    User findUserByName(String userName);

    /**
     * 通过用户名查询职位code
     * @param userName 用户名
     * @return 职位code集合
     */
    Set<String> findRoleByName(String userName);

    /**
     * 通过用户名查询权限集合
     * @param userName 用户名
     * @return 权限集合
     */
    Set<String> findPerByName(String userName);
}
