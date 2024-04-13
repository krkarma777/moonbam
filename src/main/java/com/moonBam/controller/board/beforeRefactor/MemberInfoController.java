package com.moonBam.controller.board.beforeRefactor;

import com.moonBam.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberInfoController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/memberInfo")
	public String memberInfo(@RequestParam("userId") String userId, Model model) {
		model.addAttribute("member", postService.selectMember(userId));
		return "board/memberInfo";
	}
}
