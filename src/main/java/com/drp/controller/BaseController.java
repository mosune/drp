package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.util.WebUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: BaseController
 *
 * @author gcg
 * @create 2018/01/04 9:28
 * Description: base controller
 * History:
 **/
public class BaseController {

    /**
     * 针对无权限的操作
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public void authorizationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (WebUtil.isAjaxRequest(request)) {
            // 输出JSON
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("code", "-998");
            map.put("message", "无权限");
            map.put("status", 403);
            writeJson(map, response);
        } else {
            response.sendRedirect("/tally/authorization.jsp");
        }
    }

    private void writeJson(Map<String,Object> map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus((Integer) map.get("status"));
            out = response.getWriter();
            out.write(new JSONObject(map).toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
