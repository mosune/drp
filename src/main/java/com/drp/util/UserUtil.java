package com.drp.util;

import com.drp.data.entity.AdminUser;

import java.util.Date;

/**
 * user工具类
 *
 * @author gcg
 * @create 2018-04-01 18:32
 **/
public class UserUtil {

    private UserUtil(){};

    private static AdminUser user = null;

    static {
        user = new AdminUser();
        user.setId("1");
        user.setAccount("gcg");
        user.setCreateBy("admin");
        user.setCreateTime(new Date());
        user.setMobile("110");
        user.setName("葛大哥");
        user.setPassword("123456");
        user.setRoleId("1");
        user.setShopId(10000);
        user.setStatus("1");
        user.setUpdateBy("gcg");
        user.setUpdateTime(new Date());

    }

    /**
     * 获取userId
     */
    public static String getCurUserId() {
        return user.getId();
    }

    /**
     * 获取当前人姓名
     * @return
     */
    public static String getCurName() {
        return user.getName();
    }

    /**
     * 获取当前人shopId
     * @return
     */
    public static Integer getCurShopId() {
        return user.getShopId();
    }

    /**
     * 获取当前人账户名
     * @return
     */
    public static String getCurAccount() {
        return user.getAccount();
    }

    /**
     * 获取当前人
     * @return
     */
    public static AdminUser getCurUser() {
        return user;
    }

}
