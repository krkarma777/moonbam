package com.moonBam.controller.adminpage.member;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AdminDeletedMemberDTO;
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
		System.out.println("in adminpage.post.SuspendUser");
		List<String> suspendList = Arrays.asList(userArr);
		int n = mservice.suspendUser(suspendList);
		
		System.out.println(n+"개의 사용자 정지");
		
		List<AdminReportDTO> list = rservice.SearchReport(null);
		
		mav.addObject("list",list);
		mav.setViewName("redirect:/AdminPage/AdminMemberReported");
		
		return mav;
	}
	
	//신고회원 강퇴기능
	@RequestMapping("/AdminPage/KickUser")
	public ModelAndView kickUser(@RequestParam String[] userArr, ModelAndView mav) {
		System.out.println("in adminpage.post.DeletePost");
		List<String> deletelist = Arrays.asList(userArr);
		
		System.out.println("강퇴 대상자 명단");
		for(String target : deletelist) {
			System.out.println(target);
		}
		System.out.println("==========");
		
		System.out.println("서비스레이어에 강퇴 대상자 명단 전달");
		int n = mservice.kickUser(deletelist);
		
		
		System.out.println(n+"개의 사용자 강퇴");
		
		List<AdminReportDTO> list = rservice.SearchReport(null);
		
		mav.addObject("list",list);
		mav.setViewName("redirect:/AdminPage/AdminMemberReported");
		
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

		List<AdminRestrictedMemberDTO> list = mservice.getRestrictedMemberList();
		System.out.println(list);
		mav.addObject("list", list);
		mav.setViewName("/AdminPage/AdminPageMemRestricted");
		return mav;
	}
	
	//이용제한 사용자 이용제한 해제
	@RequestMapping("/AdminPage/releaseUser")
	public ModelAndView releaseUser(@RequestParam String userid, ModelAndView mav) {
		System.out.println("in adminpage.member.releaseUser");

		int n = 0;
		
		
		mservice.releaseUser(userid);
		
		
		if(n == 0) {
			System.out.println("정지 해제");
		}else {
			System.out.println("오류");
		}
		mav.setViewName("redirect:/AdminPage/RestrictedMemberList");
		
		return mav;
	}
	
	
	
	//삭제된 회원 데이터 조회
	@GetMapping("/AdminPage/toAdminPageDeletedMember")
	public ModelAndView toAdminPageMonitoring(String SearchValue, String criteria, ModelAndView mav) {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("SearchValue", SearchValue);
		map.put("criteria", criteria);
		
		List<AdminDeletedMemberDTO> list = mservice.getDeletedMemberList(map);
		
		mav.addObject("list", list);
		System.out.println("리스트 jsp 전달");
		System.out.println(list);
		mav.setViewName("/AdminPage/AdminPageDeletedMember");
		return mav;
	}
	
	
	//삭제대기중인 데이터 완전삭제
//	@Scheduled(cron = "0 0 0 1 * *")
//	@Scheduled(cron = "0 1 * * * *")
	public void cleanDeleteMember() {
		int n = 0;
		List<String> list = mservice.getDeletelist();
		
		n = mservice.cleanDeletedMember(list);
		System.out.println("완전삭제된 회원의 데이터 : " + n + "건");
		
		int n2 = 0;
		n2 = mservice.cleanRestrictedMember();
		System.out.println("이용제한 회원 테이블에서 "+n2+"개의 데이터 삭제");
	}
	
	
	
}
