package com.drp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.drp.service.OrderGoodsService;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:23
 */
@Controller
@RequestMapping("/orderGoods")
public class OrderGoodsController extends BaseController {

	@Autowired
	private OrderGoodsService orderGoodsService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/orderGoods/index");
		return mv;
	}
	
}