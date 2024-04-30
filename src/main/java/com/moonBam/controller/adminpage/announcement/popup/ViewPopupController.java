package com.moonBam.controller.adminpage.announcement.popup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AnnouncementDTO;
import com.moonBam.service.adminpage.announcement.AnnouncementService;

@RestController
public class ViewPopupController {

	@Autowired
	AnnouncementService service;
	
	@RequestMapping("/ViewPopupController/{num}/{category}")
	public ModelAndView ViewPopup(@PathVariable("num") String num, @PathVariable("category") String category) {
		System.out.println("ViewPopupController.ViewPopup()");
		AnnouncementDTO dto =  service.oneAnnouncement(num);
		System.out.println(dto);
		String nextPage= "AdminPage/content/announce/popup/popup";
		ModelAndView mav = new ModelAndView(nextPage);
		mav.addObject("dto",dto);
		return mav;
	}
	
	@RequestMapping("/popupTest")
	public ModelAndView popupTest() {
		System.out.println("test");
		String nextPage= "AdminPage/content/announce/popup/popTest";
		ModelAndView mav = new ModelAndView(nextPage);
		return mav;
	}
}
