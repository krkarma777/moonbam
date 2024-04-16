package com.moonBam.controller.community;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.CommunityPageDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.CommunityHomeService;

@Controller
public class CommunityHomeController {
	@Autowired
    CommunityHomeService cService;
	
	@PostMapping("/communitySearch")
	public String communitySearch(Model model, String searchCategory, String searchValue, String curPage) {
		System.out.println("in CommunityHomeController communitySearch()");
		if(curPage==null) {
			curPage="1";
		}
		
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
	public String myChatList(HttpSession session, Model model, String searchCategory, String searchValue, String curPage) {
		System.out.println("in CommunityHomeController myChatList()");
		MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
		
		if(null==dto) {
			return "/Login";
		}else {
			if(curPage==null) {
				curPage="1";
			}
			
			String userid = dto.getUserId();
			
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
