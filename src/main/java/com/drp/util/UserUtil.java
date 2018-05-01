package com.drp.util;

import com.drp.data.entity.AdminUser;
import org.apache.shiro.SecurityUtils;

import java.util.Date;

/**
 * user工具类
 *
 * @author gcg
 * @create 2018-04-01 18:32
 **/
public class UserUtil {

    private UserUtil(){}

    /**
     * 获取userId
     */
    public static String getCurUserId() {
        return ((AdminUser) SecurityUtils.getSubject().getPrincipal()).getId();
    }

    /**
     * 获取当前人姓名
     * @return
     */
    public static String getCurName() {
        return ((AdminUser) SecurityUtils.getSubject().getPrincipal()).getName();
    }

    /**
     * 获取当前人shopId
     * @return
     */
    public static Integer getCurShopId() {
        return ((AdminUser) SecurityUtils.getSubject().getPrincipal()).getShopId();
    }

    /**
     * 获取当前人账户名
     * @return
     */
    public static String getCurAccount() {
        return ((AdminUser) SecurityUtils.getSubject().getPrincipal()).getAccount();
    }

    /**
     * 获取当前人
     * @return
     */
    public static AdminUser getCurUser() {
        return ((AdminUser) SecurityUtils.getSubject().getPrincipal());
    }

}
