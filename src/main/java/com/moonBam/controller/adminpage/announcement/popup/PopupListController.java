package com.moonBam.controller.adminpage.announcement.popup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.service.adminpage.announcement.AnnouncementService;

// main to here
@Controller
public class PopupListController {
	@Autowired
	AnnouncementService service;

	@RequestMapping("/popup")
	public ModelAndView pupup(@RequestParam int num) {
		System.out.println("PopupListController.pupup()");

		List<Integer> list = new ArrayList<Integer>();
		list = service.popupNnumList(
			
				new HashMap<String, String>() {
		            {
		                put("popup", "on");
		                put("category", "전체");
		            }
		     });
		
		
		System.out.println(list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("main");
		return mav;

	}
}
