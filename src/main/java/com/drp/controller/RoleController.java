package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Role;
import com.drp.service.MenuService;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drp.service.RoleService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("menus", menuService.getParentList());
		return mv;
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
		Page<Role> page = roleService.find(pageParam);
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
	public JSONObject add(Role role) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(role.getName())) {
			result.put("msg", "名称不能为空");
			return result;
		}
		return roleService.add(role);
	}

	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("update.do")
	public JSONObject update(Role role) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(role.getName())) {
			result.put("msg", "名称不能为空");
			return result;
		}
		return roleService.update(role);
	}

	/**
	 * 删除
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("delete.do")
	public JSONObject delete(Integer id) {
		JSONObject result = new JSONObject();
		if (id == null) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		return roleService.delete(id);
	}

	/**
	 * 删除
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("getData.do")
	public JSONObject getData(Integer id) {
		JSONObject result = new JSONObject();
		if (id == null) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		return roleService.getData(id);
	}

	/**
	 * 删除
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("updateStatus.do")
	public JSONObject updateStatus(Integer id) {
		JSONObject result = new JSONObject();
		if (id == null) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		return roleService.updateStatus(id);
	}

	/**
	 * 查询岗位列表
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("getList.do")
	public JSONObject getList() {
		JSONObject result = new JSONObject();
		result.put("list", roleService.getList());
		return result;
	}

	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("savePower.do")
	public JSONObject savePower(int id, String power) {
		return roleService.savePower(id, power);
	}
}