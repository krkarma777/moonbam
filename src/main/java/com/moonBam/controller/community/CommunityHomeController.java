package com.moonBam.controller.community;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.moonBam.dto.CommunityPageDTO;
import com.moonBam.service.CommunityHomeService;

@Controller
public class CommunityHomeController {
	@Autowired
    CommunityHomeService cService;
	
	@PostMapping("/communitySearch")
	public String communitySearch(Model model, String searchCategory, String searchValue, String curPage) {
		System.out.println("in CommunityHomeController communitySearch()");
		System.out.println(searchCategory+" "+searchValue+" "+curPage);
		if(curPage==null) {
			curPage="1";
		}
		
		CommunityPageDTO cpDTO= cService.chatRoomList(searchCategory, searchValue, curPage);
		
    	model.addAttribute("cpDTO", cpDTO);
    	
    	String category = "community";
    	model.addAttribute("category", category);
    	List<String> categoryList = new ArrayList<>();;
		categoryList.add("영화");
		categoryList.add("독서");
		categoryList.add("음악");
		model.addAttribute("categoryList", categoryList);
		
		System.out.println("미지 복구 테스트 0415");
		
		return "community/communityHome";
		
	}
	
}
