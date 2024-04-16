package com.moonBam.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.LoginService;


@Controller
public class TestController {

	@Autowired
	LoginService serv;

	// 멤버 리스트 찾기
	@GetMapping("/memberList")
	public ModelAndView memberList() {
		ModelAndView mav = new ModelAndView();
		List<MemberDTO> list = serv.selectAll();
		mav.addObject("memberList", list);
		mav.setViewName("member/Test/test_view_list");

		return mav;
	}

	// 멤버 삭제
	@GetMapping("/IDDelete")
	@ResponseBody
	public void IDDelete(String userId) {
		serv.IDDelete(userId);
	}
}
