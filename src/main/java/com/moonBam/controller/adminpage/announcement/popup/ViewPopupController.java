package com.moonBam.controller.adminpage.announcement.popup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AnnouncementDTO;
import com.moonBam.service.adminpage.announcement.AnnouncementService;

@RestController
public class ViewPopupController {

	@Autowired
	AnnouncementService service;
	@RequestMapping("/ViewPopupController")
	public ModelAndView ViewPopup(@RequestParam String num) {
		System.out.println("ViewPopupController.ViewPopup()");
		AnnouncementDTO dto =  service.oneAnnouncement(num);
		System.out.println(dto);
		String nextPage= "AdminPage/content/announce/popup/popup";
		ModelAndView mav = new ModelAndView(nextPage);
		mav.addObject("dto",dto);
	
		
		return mav;
	}
}
