package com.moonBam.controller.adminpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AdminCounterDTO;
import com.moonBam.dto.AdminReportDTO;

@Controller
public class AdminMenubarController {
	
	@Autowired
	AdminCounter counter;
	
	@Autowired
	AdminStatisticsController asController;
	
	//처음 접속시화면
	@RequestMapping(value = "/AdminPage")
	public ModelAndView AdminMain(ModelAndView mav) {
		System.out.println("in adminpage.MainController : AdminMain()");
		List<AdminCounterDTO> list1 = asController.getCount();
		
		System.out.println("통계확인 페이지 전달 전의 list1");
		System.out.println(list1);
		System.out.println("======================");
		
		mav.addObject("list1", list1);
		
		AdminReportDTO rDTO = asController.getUndone();
		System.out.println("통계페이지 전달 전의 list2");
		System.out.println(rDTO);
		
		mav.addObject("rDTO", rDTO);
		
		mav.setViewName("/AdminPage/AdminPageStatStat");
		
		//미신고 처리 받아와서 list2로 
		return mav;
	}
	
	//좌측 메뉴 이동 url
	//통계1
	@RequestMapping(value = "/AdminPage/toStatistics")
	public ModelAndView AdmintoStatistics(ModelAndView mav) {
		System.out.println("in adminpage.MainController : AdmintoStatistics()");
		
		List<AdminCounterDTO> list1 = asController.getCount();
		
		System.out.println("통계확인 페이지 전달 전의 list1");
		System.out.println(list1);
		System.out.println("======================");
		
		mav.addObject("list1", list1);
		
		AdminReportDTO rDTO = asController.getUndone();
		System.out.println("통계페이지 전달 전의 list2");
		System.out.println(rDTO);
		
		mav.addObject("rDTO", rDTO);
		
		mav.setViewName("/AdminPage/AdminPageStatStat");
		//미신고 처리 받아와서 list2로 
		return mav;
	}
	
	//통계2
	@RequestMapping(value = "/AdminPage/toStatistics2")
	public String AdmintoStatistics2() {
		System.out.println("in adminpage.MainController : AdmintoStatistics2()");
		return "/AdminPage/AdminPageStatStat2";
	}
	
	//신고글관리
	@RequestMapping(value = "/AdminPage/toAdminPagePostRprtedPost")
	public String AdmintoAdminPagePostRprtedPost() {
		System.out.println("in adminpage.MainController : AdmintoAdminPagePostRprtedPost()");
		return "/AdminPage/AdminPagePostRprtedPost";
	}
	
	//채팅방 금칙어 설정
	@RequestMapping(value = "/AdminPage/toAdminPageChatRule")
	public String AdmintoAdminPagePostRule() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageChatRule()");
		return "/AdminPage/AdminPageChatRule";
	}
	
	//삭제된 글
	@RequestMapping(value = "/AdminPage/toAdminPageDeletedPost")
	public String AdmintoAdminPagePostExp() {
		System.out.println("in adminpage.MainController : AdminPageDeletedPost");
		return "/AdminPage/AdminPageDeletedPost";
	}
	
	//신고된 사용자
	@RequestMapping(value = "/AdminPage/toAdminPageMemRprtedMem")
	public String AdmintoAdminPageMemRprtedMem() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMemRprtedMem()");
		return "/AdminPage/AdminPageMemRprtedMem";
	}
	
	//회원등급관리
	@RequestMapping(value = "/AdminPage/toAdminPageMemGrade")
	public String AdmintoAdminPageMemGrade() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMemGrade");
		return "/AdminPage/AdminPageMemGrade";
	}
	
	//
	@RequestMapping(value = "/AdminPage/toAdminPageMemRule")
	public String AdmintoAdminPageMemRule() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMemRule()");
		return "/AdminPage/AdminPageMemRule";
	}
	
	//이용제한회원관리
	@RequestMapping(value = "/AdminPage/toAdminPageMemRestricted")
	public String AdmintoAdminPageMemRestricted() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageMemRestricted()");
		return "/AdminPage/AdminPageMemRestricted";
	}
	
	//공지글
	@RequestMapping(value = "/AdminPage/toAdminPageAnnounce")
	public String AdmintoAdminPageAnnounce() {
		System.out.println("in adminpage.MainController : AdmintoAdminPageAnnounce()");
		return "redirect:/AdminPage/AdminPageAnnounce";
	}
	
	//신고된 댓글 조회
	@RequestMapping(value = "/AdminPage/AdminPageReportedComment")
	public String getDeletedCommentList() {
		return "/AdminPage/AdminPageReportedComment";
	}
	
//	@RequestMapping(value = "/AdminPage/toAdminPageEvent")
//	public String AdmintoAdminPageEvent() {
//		System.out.println("in adminpage.MainController : AdmintoAdminPageEvent()");
//		return "/AdminPage/AdminPageEvent";
//	}
	
	
	
	
}
