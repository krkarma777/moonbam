package com.moonBam.controller.adminpage.member;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AdminReportDTO;
import com.moonBam.dto.AdminRestrictedMemberDTO;
import com.moonBam.service.adminpage.AdminMemberService;
import com.moonBam.service.adminpage.AdminReportService;

@Controller
public class AdminPageMemberController {

	@Autowired
	AdminMemberService mservice;

	@Autowired
	AdminReportService rservice;

	//신고회원 조회
	@RequestMapping("/AdminPage/AdminMemberReported")
	public ModelAndView SearchReportedMember(String SearchValue, String criteria, ModelAndView mav) {
		//System.out.println("in AdminPageMemberController:SearchReportedMember()");
		HashMap<String,String> map = new HashMap<>();
		map.put("SearchValue", SearchValue);
		map.put("criteria", criteria);

		
		List<AdminReportDTO> list = rservice.ReportedMemList(map);

		mav.addObject("list", list);
		mav.setViewName("/AdminPage/AdminPageMemRprtedMem");
		return mav;
	}
	
	//신고회원 정지
	@RequestMapping("/AdminPage/SuspendUser")
	public ModelAndView SuspendUser(@RequestParam String[] userArr, ModelAndView mav) {
		System.out.println("in adminpage.post.DeletePost");
		List<String> deletelist = Arrays.asList(userArr);
		int n = rservice.delReportedPost(deletelist);
		System.out.println(n+"개의 사용자 정지");
		
		List<AdminReportDTO> list = rservice.SearchReport(null);
		
		mav.addObject("list",list);
		mav.setViewName("/AdminPage/AdminPageReportedPost");
		
		return mav;
	}
	
	//신고회원 강퇴기능
	@RequestMapping("/AdminPage/KickUser")
	public ModelAndView KickUser(@RequestParam String[] userArr, ModelAndView mav) {
		System.out.println("in adminpage.post.DeletePost");
		List<String> deletelist = Arrays.asList(userArr);
		int n = rservice.delReportedPost(deletelist);
		System.out.println(n+"개의 사용자 정지");
		
		List<AdminReportDTO> list = rservice.SearchReport(null);
		
		mav.addObject("list",list);
		mav.setViewName("/AdminPage/AdminPageReportedPost");
		
		return mav;
	}

	//이용제한 사용자 리스트 조회
	@RequestMapping(value = "/AdminPage/RestrictedMemberList")
	public ModelAndView getRestrictedMemberList(ModelAndView mav, String SearchValue, String criteria) {

		System.out.println("in AdminMemberController.getRestrictedMemberList()");

		HashMap<String, String> map = new HashMap<>();
		map.put("SearchValue", SearchValue);
		map.put("criteria", criteria);
		System.out.println(map);

//		System.out.println("간다");
		List<AdminRestrictedMemberDTO> list = mservice.getRestrictedMemberList();
		System.out.println(list);
		mav.addObject("list", list);
		mav.setViewName("/AdminPage/AdminPageMemRestricted");
		return mav;
	}
	
	//이용제한 사용자 이용제한 해제
	
	//삭제된 회원 데이터 조회
	@GetMapping("/AdminPage/toAdminPageDeletedMember")
	public String toAdminPageMonitoring() {
		return "/AdminPage/AdminPageDeletedMember";
	}
	
	//삭제된 회원 데이터 완전삭제
	
}
