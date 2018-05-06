package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.AdminUser;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    /**
     * 首页列表
     * @param limit
     * @param offset
     * @return
     */
    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping("list.do")
    public JSONObject list(int limit, int offset, String nameLike) {
        JSONObject result = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", nameLike);
        PageParam pageParam = new PageParam(offset, limit, map);
        Page<AdminUser> page = adminUserService.find(pageParam);
        result.put("total", page.getTotal());
        result.put("rows", page.getRows());
        return result;
    }

    /**
     * 添加
     * @return
     */
    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping("add.do")
    public JSONObject add(AdminUser adminUser) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(adminUser.getName())) {
            result.put("msg", "名称不能为空");
            return result;
        }
        return adminUserService.add(adminUser);
    }

    /**
     * 修改
     * @return
     */
    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping("update.do")
    public JSONObject update(AdminUser adminUser) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(adminUser.getName())) {
            result.put("msg", "名称不能为空");
            return result;
        }
        return adminUserService.update(adminUser);
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping("delete.do")
    public JSONObject delete(String id) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(id)) {
            result.put("msg", "数据错误，请刷新重试");
            return result;
        }
        return adminUserService.delete(id);
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping("getData.do")
    public JSONObject getData(String id) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(id)) {
            result.put("msg", "数据错误，请刷新重试");
            return result;
        }
        AdminUser adminUser = new AdminUser(id);
        adminUser = adminUserService.get(adminUser);
        result.put("user", adminUser);
        return result;
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping("updateStatus.do")
    public JSONObject updateStatus(String id) {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(id)) {
            result.put("msg", "数据错误，请刷新重试");
            return result;
        }
        return adminUserService.updateStatus(id);
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @ResponseBody
    @RequestMapping("updatePwd.do")
    public JSONObject updatePwd(String oldPassword, String newPassword, String confirmPassword) {
        return adminUserService.updatePwd(oldPassword, newPassword, confirmPassword);
    }
	
}