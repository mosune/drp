package com.drp.util;

import javax.servlet.http.HttpServletRequest;

/**
 * FileName: WebUtil
 *
 * @author gcg
 * @create 2018/01/04 9:27
 * Description: web util
 * History:
 **/
public class WebUtil {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }

}
