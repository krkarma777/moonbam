package com.moonBam.controller.adminpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMenubarController {

	//처음 접속시
	@RequestMapping(value = "/AdminPage")
	public String AdminMain() {
		System.out.println("in adminpage.MainController : AdminMain()");
		return "AdminPage/AdminPageMain";
	}
	
	//좌측 메뉴 이동 url
	
	@RequestMapping(value = "/AdminPage/toStatistics")
	public String AdmintoStatistics() {
		System.out.println("in adminpage.MainController : AdmintoStatistics()");
		return "/AdminPage/AdminPageStatStat";
	}
	
	@RequestMapping(value = "/AdminPage/toStatistics2")
	public String AdmintoStatistics2() {
		System.out.println("in adminpage.MainController : AdmintoStatistics2()");
		return "/AdminPage/AdminPageStatStat2";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPagePostRprtedPost")
	public String AdmintoAdminPagePostRprtedPost() {
		System.out.println("in adminpage.MainController : AdmintoAdminPagePostRprtedPost()");
		return "/AdminPage/AdminPagePostRprtedPost";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPagePostRule")
	public String AdmintoAdminPagePostRule() {
		System.out.println("in adminpage.MainController : AdmintoAdminPagePostRule()");
		return "/AdminPage/AdminPagePostRule";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPagePostRestricted")
	public String AdmintoAdminPagePostExp() {
		System.out.println("in adminpage.MainController : AdmintoAdminPagePostExp()");
		return "/AdminPage/AdminPagePostRestricted";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPageMemRprtedMem")
	public String AdmintoAdminPageMemRprtedMem() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMemRprtedMem()");
		return "/AdminPage/AdminPageMemRprtedMem";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPageMemGrade")
	public String AdmintoAdminPageMemGrade() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMemGrade");
		return "/AdminPage/AdminPageMemGrade";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPageMemRule")
	public String AdmintoAdminPageMemRule() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMemRule()");
		return "/AdminPage/AdminPageMemRule";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPageMemRestricted")
	public String AdmintoAdminPageMemRestricted() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMemRestricted()");
		return "/AdminPage/AdminPageMemRestricted";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPageAnnounce")
	public String AdmintoAdminPageAnnounce() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageAnnounce()");
		return "redirect:/AdminPage/AdminPageAnnounce";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPageEvent")
	public String AdmintoAdminPageEvent() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageEvent()");
		return "/AdminPage/AdminPageEvent";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPageAPI")
	public String AdmintoAdminPageAPI() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageAPI");
		return "/AdminPage/AdminPageAPI";
	}
	
	@RequestMapping(value = "/AdminPage/toAdminPageMonitoring")
	public String AdmintoAdminPageMonitoring() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMonitoring()");
		return "/AdminPage/AdminPageMonitoring";
	}
	
	
	
	
}
