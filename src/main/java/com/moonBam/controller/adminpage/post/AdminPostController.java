package com.moonBam.controller.adminpage.post;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AdminDeletedPostDTO;
import com.moonBam.dto.AdminReportDTO;
import com.moonBam.service.adminpage.AdminDeletedPostService;
import com.moonBam.service.adminpage.AdminReportService;

@Controller
public class AdminPostController {

	@Autowired
	AdminReportService rservice;
	
	@Autowired
	AdminDeletedPostService dpservice;
	
	
	//신고글 리스트 조회
	@RequestMapping(value = "/AdminPage/AdminPostReported")
//	@ResponseBody
	public ModelAndView searchReportedPost(String SearchValue, String criteria, ModelAndView mav) {
		
		System.out.println("in adminpage.post.searchReportedPost");
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("SearchValue", SearchValue);
		
		List<AdminReportDTO> list = rservice.SearchReport(map);
		System.out.println(map);
		System.out.println(list);
		mav.addObject("list",list);
		mav.setViewName("/AdminPage/AdminPageReportedPost");
		return mav;
	}
	
	//신고글 Report 테이블에서 삭제 및 삭제된 글 테이블로 이동 
	@RequestMapping(value = "/AdminPage/DeletePost")
	public ModelAndView DeletePost(@RequestParam String[] postArr, ModelAndView mav) {
		
		System.out.println("in adminpage.post.DeletePost");
		System.out.println("어드민 페이지 신고글 삭제 기능");
		System.out.println("1. 삭제할 글의 id list로 출력");
		
		List<String> deletelist = Arrays.asList(postArr);
		System.out.println(deletelist);
		int n = rservice.delReportedPost(deletelist);
		System.out.println(n+"개의 신고글 삭제처리");
		
		List<AdminReportDTO> list = rservice.SearchReport(null);
		
		mav.addObject("list",list);
		mav.setViewName("/AdminPage/AdminPageReportedPost");
		
		return mav;
	}
	
	
	
	//삭제된 게시글 조회
	@RequestMapping(value = "/AdminPage/AdminPageDeletedPost")
	public ModelAndView getDeletedPostList(String SearchValue, String Criteria, ModelAndView mav) {
		
		System.out.println("1. 검색조건 입력");
		HashMap<String, String> map = new HashMap<>();
		map.put("searchValue", SearchValue);
		map.put("criteria", Criteria);
		
		System.out.println("2. 서비스 레이어에 검색조건 전달");
		List<AdminDeletedPostDTO> list = dpservice.getDeletedPostList(map);
		System.out.println("jsp 페이지로 전달할 list");
		System.out.println(list);
		
		mav.addObject("list", list);
		mav.setViewName("/AdminPage/AdminPageDeletedPost");
		
		return mav;
	}
	
	//삭제대기 게시글 완전삭제
	@Scheduled(cron = "0 0 0 1 * *")
	public void cleanDeletedPost() {
		dpservice.cleanDeletedPost();
	}
	
	//삭제대기 게시글에서 복원
	@RequestMapping(value = "/AdminPage/restoreDeletedPost")
	public ModelAndView restoreDeletedPost(ModelAndView mav, String postid) {
		System.out.println("in AdminPostController.restoreDeletedPost");
		System.out.println(postid);
		int n = dpservice.restoreDeletedPost(postid);
		
		if(n ==1) {
			System.out.println("성공적으로 복원되었습니다");
		}else {
			System.out.println("복원실패");
		}
		
		mav.setViewName("redirect:/AdminPage/AdminPageDeletedPost");
		
		return mav;
	}
	
}
