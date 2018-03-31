package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.AdminUser;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drp.service.AdminUserService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/adminUser")
public class AdminUserController extends BaseController {

	@Autowired
	private AdminUserService adminUserService;

    /**
     * 跳转首页
     * @return
     */
	@RequestMapping("/index.do")
	public String index() {
		return "/adminUser/index";
	}

	@ResponseBody
    @RequestMapping("list.do")
    public JSONObject list(int limit, int offset) {
        JSONObject result = new JSONObject();
        PageParam pageParam = new PageParam(offset, limit, new HashMap<String, Object>());
        Page<AdminUser> page = adminUserService.find(pageParam);
        result.put("total", page.getTotal());
        result.put("rows", page.getRows());
        return result;
    }
	
}