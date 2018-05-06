package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.dto.OperationLogDto;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drp.service.OperationLogService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/operationLog")
public class OperationLogController extends BaseController {

	@Autowired
	private OperationLogService operationLogService;
	
	@RequestMapping("/index.do")
	public String index() {
		return "/log/index";
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
	public JSONObject list(int limit, int offset) {
		JSONObject result = new JSONObject();
		PageParam pageParam = new PageParam(offset, limit, null);
		Page<OperationLogDto> page = operationLogService.find(pageParam);
		result.put("total", page.getTotal());
		result.put("rows", page.getRows());
		return result;
	}
	
}