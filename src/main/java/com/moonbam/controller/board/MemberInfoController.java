package com.moonbam.controller.board;


import com.moonbam.dto.MemberDTO;
import com.moonbam.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberInfoController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/memberInfo")
	public ModelAndView memberInfo(@RequestParam("userId") String userId) {
		ModelAndView mav = new ModelAndView();
		MemberDTO member = postService.selectMember(userId);
		mav.addObject("member", member);
		mav.setViewName("board/memberInfo");
		return mav;
	}

}
