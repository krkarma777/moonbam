package com.moonBam.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.DebugBoardDTO;
import com.moonBam.service.member.DebugBoardService;

@Controller
public class DebugBoardController {

	@Autowired
	DebugBoardService serv;
	
	/*
	 * viewDBoardList			? 에이젝스?
	 * viewDBoardContent
	 * insertDBoard				
	 * updateDBoard				에이젝스
	 * deleteDBoard				에이젝스
	 * updateDBoardViewCount	에이젝스
	 */
	
	@GetMapping("/viewDBoardList")
	public ModelAndView viewDBoardList() {
		List<DebugBoardDTO> list = serv.viewDBoardList();
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("member/Test/viewDBoardList");
		return mav;
	}
	
	@GetMapping("/viewDBoardContent/{bNum}")
	public ModelAndView viewDBoardContent(@PathVariable("bNum") int boardNum) {
		
		serv.updateDBoardViewCount(boardNum);					//조회수 증가 안 됨  - 이유 모름
		
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("member/Test/viewDBoardContent");
		return mav;
	}
	
	@PostMapping("/insertPost")
	public ModelAndView insertPost(DebugBoardDTO dto) {
		// 현재 날짜와 시간을 dto에 입력
	    Date now = new Date();
	    dto.setPostDate(now);
		
		int num = serv.insertPost(dto);
		System.out.println(num);
		List<DebugBoardDTO> list = serv.viewDBoardList();
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("member/Test/viewDBoardList");
		return mav;
	}
	
	@GetMapping("/newPost")
	public ModelAndView newPost() {
		ModelAndView mav = new ModelAndView();
			mav.setViewName("member/Test/newPost");
		return mav;
	}
}
