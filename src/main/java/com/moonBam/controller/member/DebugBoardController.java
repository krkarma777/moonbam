package com.moonBam.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	//게시판 글 목록 보기
	@GetMapping("/viewDBoardList")
	public ModelAndView viewDBoardList() {
		List<DebugBoardDTO> list = serv.viewDBoardList();
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("member/Test/viewDBoardList");
		return mav;
	}
	
	//게시판 글 쓰기
	@GetMapping("/newPost")
	public ModelAndView newPost() {
		ModelAndView mav = new ModelAndView();
			mav.setViewName("member/Test/newPost");
		return mav;
	}
	
	//게시판 글 등록
	@PostMapping("/insertPost")
	public ModelAndView insertPost(DebugBoardDTO dto) {
		// 현재 날짜와 시간을 dto에 입력
	    Date now = new Date();
	    dto.setPostDate(now);
		
		serv.insertPost(dto);
		List<DebugBoardDTO> list = serv.viewDBoardList();
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("member/Test/viewDBoardList");
		return mav;
	}
	
	//게시판 글 보기
	@PostMapping("/viewDBoardContent/{bNum}")
	public ModelAndView viewDBoardContent(@PathVariable("bNum") int boardNum) {
		
		//게시판 글 조회수 올리기
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);
		
		int updatedViewCount = dto.getViewCount()+1;
		
		HashMap<String, Integer> map = new HashMap<>();
			map.put("boardNum", boardNum);
			map.put("viewCount", updatedViewCount);
		
		serv.updateDBoardViewCount(map);					//조회수 증가 안 됨  - 이유 모름
		
		dto = serv.viewDBoardContent(boardNum);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("member/Test/viewDBoardContent");
		return mav;
	}
	
	
	//게시판 글 수정(PRG패턴)
	@PostMapping("/modifyPost/{bNum}")
	public String modifyPost(@PathVariable("bNum") int boardNum, HttpSession session) {
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);
		session.setAttribute("dto", dto);
		return "redirect:/modifyPost";
	}
	
	@GetMapping("/modifyPost")
	public ModelAndView vmodifyPost(HttpSession session) {
		DebugBoardDTO dto = (DebugBoardDTO) session.getAttribute("dto");
		ModelAndView mav = new ModelAndView();
			mav.addObject("dto", dto);
			session.removeAttribute("dto");
			mav.setViewName("member/Test/modifyPost");
		return mav;
	}
	
	//게시판 글 수정하기
	@PostMapping("/updateDBoard")
	public ModelAndView updateDBoard(DebugBoardDTO dto) {
		// 현재 날짜와 시간을 dto에 입력
	    Date now = new Date();
	    dto.setEdittedDate(now);
	    serv.updateDBoard(dto);
	    
	    ModelAndView mav = new ModelAndView("member/Test/viewDBoardContent");
	    mav.addObject("dto", dto);
	    return mav;
	}
	
	//게시판 글 삭제하기	
	@GetMapping("/deletePost/{nn}/{bNum}")
	public ModelAndView deletePost(@PathVariable("nn") String nickname, @PathVariable("bNum") int boardNum) {
		serv.deleteDBoard(boardNum);
		return viewDBoardList();
	}
	
	
}
