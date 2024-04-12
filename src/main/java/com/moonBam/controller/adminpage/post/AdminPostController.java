package com.moonBam.controller.adminpage.post;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	//신고글 삭제
	@RequestMapping(value = "/AdminPage/DeletePost")
	public ModelAndView DeletePost(@RequestParam String[] postArr, ModelAndView mav) {
		
		System.out.println("in adminpage.post.DeletePost");
		List<String> deletelist = Arrays.asList(postArr);
		rservice.delReportedPost(deletelist);
		
		List<AdminReportDTO> list = rservice.SearchReport(null);
		
		mav.addObject("list",list);
		mav.setViewName("/AdminPage/AdminPagePostRprtedPost");
		
		return mav;
	}
	
	//삭제된 게시글 조회
	@RequestMapping(value = "/AdminPage/AdminPageDeletedPost")
	public ModelAndView getDeletedPostList(String SearchValue, String Criteria, ModelAndView mav) {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("searchValue", SearchValue);
		map.put("criteria", Criteria);
		List<AdminDeletedPostDTO> list = dpservice.getDeletedPostList(map);
		System.out.println("in controller");
		System.out.println(list);
		mav.addObject("list", list);
		mav.setViewName("/AdminPage/AdminPageDeletedPost");
		
		return mav;
	}
	
	//삭제된 댓글 조회
	@RequestMapping(value = "/AdminPage/AdminPageDeletedComment")
	public String getDeletedCommentList() {
		return "/AdminPage/AdminPageDeletedComment";
	}
	
}
