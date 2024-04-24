package com.moonBam.controller.adminpage.announcement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AnnouncementDTO;
import com.moonBam.service.adminpage.announcement.AnnouncementService;
import com.moonBam.util.pageCalculation;

@Controller
public class ListAnnouncementController {
	@Autowired
	AnnouncementService service;
	//ListAnnouncementController to AdminPage/AdminPageAnnounce
	
	@GetMapping("AdminPage/AdminPageAnnounce")
	public ModelAndView listAnnouncement(@RequestParam(required = false) String currentPage, @RequestParam(required = false) String word) {
		System.out.println("ListAnnouncementController.listAnnouncement()");
		// currentPage, word null 값 처리 및 초기화, boot에서 change String to int
		int currentNum = (currentPage != null) ? Integer.parseInt(currentPage) : 1;
		word = (word != null) ? word : "";

		// 한페이지에 출력할 글 개수
		int listSize = 10;

		// get list number
		int start = (currentNum - 1) * listSize + 1;
		int end = currentNum * listSize;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("word", word);
		map.put("start", start);
		map.put("end", end);
		
		//	여기서 현재 페이지 마지막 페이지 전달
		List<AnnouncementDTO> list = service.listAnnouncement(map);
		// get lastPage
		int lastPage = service.lastPage(word);
		// pageNation set,
		pageCalculation pc = new pageCalculation(currentNum, lastPage);
		int [] pageArray = pc.getPages();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("word", word);
		mav.addObject("current", currentNum);
		mav.addObject("last", lastPage);
		mav.addObject("pageArray", pageArray);
		mav.setViewName("AdminPage/AdminPageAnnounce");
		return mav;
	}
}
