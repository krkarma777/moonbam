package com.moonBam.controller.adminpage.announcement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AnnouncementDTO;
import com.moonBam.service.adminpage.announcement.AnnouncementService;
import com.moonBam.util.TimeParsing;

@Controller
public class AnnouncementController {
	@Autowired
	AnnouncementService service;

	String nextPage = "redirect:toAdminPageAnnounce";
	
	// write
	// / 추가함
	@PostMapping("AdminPage/WriteAnnouncementController")
	public String WriteAnnouncement(@RequestParam(required = false) String word) {
		System.out.println("WriteAnnouncementController.WriteAnnouncementController()");
		String nextPage = "AdminPage/content/announce/writeAnnouncement";
		return nextPage;
	}

	// insert
	// / 추가함
	@RequestMapping("AdminPage/InsertAnnouncementController")

	public String InsertAnnouncementController(String annoTitle, String annoText, String dateTimePicker, String popup, @RequestParam(required = false) MultipartFile img) {

		System.out.println("AnnouncementController.InsertAnnouncementController()");
		
	
		String annoWriter = "관리자"; 
		TimeParsing tp = new TimeParsing();
		String[] dates = tp.tp2Arr(dateTimePicker); // 시작일 종료일 분리
		AnnouncementDTO dto = new AnnouncementDTO(0, annoTitle, annoText, annoWriter, dates[0], dates[1], popup);
		int done = service.insertAnnouncement(dto);
		return nextPage;
	}

	// view one
	@RequestMapping("AdminPage/ViewAnnouncementController")
	public ModelAndView View(String annoNum) {
		System.out.println("AnnouncementController.View()");
		AnnouncementDTO dto = service.oneAnnouncement(annoNum);
		// setting times
		dto.setTimes();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("AdminPage/content/announce/viewAnnouncement");
		return mav;
	}

	// update
	@RequestMapping("AdminPage/RetrieveAnnouncementController")
	public ModelAndView Retrieve(String annoNum, Model m) {
		System.out.println("AnnouncementController.RetrieveAnnouncementController()");
		AnnouncementDTO dto = service.oneAnnouncement(annoNum);
		ModelAndView mav = new ModelAndView();
		// 날짜 설정
		dto.setTimes();
		mav.addObject("dto", dto);
		mav.setViewName("AdminPage/content/announce/updateAnnouncement");
		return mav;
	}

	@RequestMapping("AdminPage/UpdateAnnouncementController")
	public String update(int annoNum, String annoTitle, String annoText, String annoWriter, String popup,
			@RequestParam String dateTimePicker) {
		System.out.println("AnnouncementController.UpdateAnnouncementController()");
		String[] dates = dateTimePicker.split(" - "); // 시작일 종료일 분리
		AnnouncementDTO dto = new AnnouncementDTO(annoNum, annoTitle, annoText, annoWriter, dates[0], dates[0], popup);
		service.updateAnnouncement(dto);
		 return nextPage; 
	}

	// Delete
	@RequestMapping("AdminPage/DeleteAnnouncementController")
	public String Delete(@RequestParam String annoNum) {
		System.out.println("DeleteAnnouncementController");
		service.deleteAnnouncement(annoNum);
		return nextPage;
	}
}
