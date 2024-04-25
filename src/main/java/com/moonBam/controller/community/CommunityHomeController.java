package com.moonBam.controller.community;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.CommunityPageDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.ChatRoomService;
import com.moonBam.service.CommunityHomeService;

@Controller
public class CommunityHomeController {
	@Autowired
    CommunityHomeService cService;
	
	@Autowired
	ChatRoomService crService;
	@PostMapping("/communitySearch")
	public String communitySearch(Model model, String searchCategory, String searchValue, String curPage) {
		System.out.println("in CommunityHomeController communitySearch()");
		if(curPage==null) {
			curPage="1";
		}
		System.out.println("채팅방 검색조건을 service 레이어에 전달==========");
		System.out.println("searchCategory");
		System.out.println(searchCategory);
		System.out.println("searchValue");
		System.out.println(searchValue);
		System.out.println("curPage");
		System.out.println(curPage);
		System.out.println("======================================");
		CommunityPageDTO cpDTO= cService.chatRoomList(searchCategory, searchValue, curPage);
		
    	model.addAttribute("cpDTO", cpDTO);
    	
    	String category = "community";
    	model.addAttribute("category", category);
    	
    	List<String> categoryList = new ArrayList<>();;
    	categoryList.add("전체");
		categoryList.add("영화");
		categoryList.add("독서");
		categoryList.add("음악");
		model.addAttribute("categoryList", categoryList);
		
		String communityCategory = "communitySearch";
		model.addAttribute("communityCategory", communityCategory);
		
		return "community/communityHome";
	}
	
	@RequestMapping("/myChatList")
	public String myChatList(Principal principal, Model model, String searchCategory, String searchValue, String curPage) {
		System.out.println("in CommunityHomeController myChatList()");
		String userid = principal.getName();
		
		if(null==userid) {
			return "/Login";
		}else {
			if(curPage==null) {
				curPage="1";
			}
			
			CommunityPageDTO cpDTO= cService.myChatList(searchCategory, searchValue, curPage, userid);
			
			model.addAttribute("cpDTO", cpDTO);
		
			List<String> categoryList = new ArrayList<>();;
			categoryList.add("전체");
			categoryList.add("영화");
			categoryList.add("독서");
			categoryList.add("음악");
			model.addAttribute("categoryList", categoryList);
			
			String category = "myChatList";
	    	model.addAttribute("category", category);
			
			String communityCategory = "myChatList";
			model.addAttribute("myChatList", communityCategory);
			
			return "community/communityHome";
		}
	}
	
}
