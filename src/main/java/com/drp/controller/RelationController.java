package com.drp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.drp.service.RelationService;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/relation")
public class RelationController extends BaseController {

	@Autowired
	private RelationService relationService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/relation/index");
		return mv;
	}
	
}