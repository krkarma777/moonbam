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
	
	//게시판 글 목록 보기
	@GetMapping("/viewDBoardList/{orderBy}")
	public ModelAndView viewDBoardList(@PathVariable("orderBy") String orderBy) {
		List<DebugBoardDTO> list = serv.viewDBoardList(orderBy);
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("member/Test/viewDBoardList");
		return mav;
	}
	
	//게시판 글 쓰기 화면으로 이동
	@GetMapping("/newPost")
	public ModelAndView newPost() {
		ModelAndView mav = new ModelAndView();
			mav.setViewName("member/Test/newPost");
		return mav;
	}
	
	//게시판 글 등록(PRG 패턴을 통해 중복 등록 방지)
	@PostMapping("/insertPost")
	public ModelAndView insertPost(DebugBoardDTO dto) {
		System.out.println(dto);
		
		// 현재 날짜와 시간을 dto에 입력
	    Date now = new Date();
	    dto.setPostDate(now);
		
		serv.insertPost(dto);
		List<DebugBoardDTO> list = serv.viewDBoardList("boardNum");
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("redirect:/viewDBoardList");
		return mav;
	}
	
	//게시판 글 보기
	@GetMapping("/viewDBoardContent/{bNum}")
	public ModelAndView viewDBoardContent(@PathVariable("bNum") int boardNum) {
		
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);

		//게시판 글 조회수 올리기
		serv.updateDBoardViewCount(boardNum);					//조회수 증가 안 됨  - 이유 모름

		System.out.println(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("member/Test/viewDBoardContent");
		return mav;
	}
	
	//게시판 글 수정하기	전 비밀번호 확인
	@GetMapping("/checkUpdatePost/{bNum}")
	public ModelAndView checkUpdatePost(@PathVariable("bNum") int boardNum) {
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);
	    ModelAndView mav = new ModelAndView("member/Test/checkUpdatePost");
	    	mav.addObject("dto", dto);
	    return mav;
	}
		
	//게시판 글 수정화면으로 이동
	@PostMapping("/checkUpdatePost/modifyPost")
	public ModelAndView modifyPost(int boardNum) {
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("member/Test/modifyPost");
	return mav;
	}

	//게시판 글 수정하기
	@PostMapping("/checkUpdatePost/updateDBoard")
	public String updateDBoard(DebugBoardDTO dto) {

		// 현재 날짜와 시간을 dto에 입력
	    Date now = new Date();
	    dto.setEdittedDate(now);
	    serv.updateDBoard(dto);
	    return "redirect:/viewDBoardContent/"+dto.getBoardNum();
	}
	
	//게시판 글 삭제 전 비밀번호 확인
	@GetMapping("/checkDeletePost/{bNum}")
	public ModelAndView checkDeletePost(@PathVariable("bNum") int boardNum) {
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);
		ModelAndView mav = new ModelAndView("member/Test/checkDeletePost");
	    mav.addObject("dto", dto);
	    return mav;
	}
		
	//게시판 글 삭제하기	
	@PostMapping("/deletePost")
	public ModelAndView deletePost(String nickname, int boardNum) {
		serv.deleteDBoard(boardNum);
		return viewDBoardList("boardNum");
	}
	
	
}
