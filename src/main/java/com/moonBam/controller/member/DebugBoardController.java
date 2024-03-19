package com.moonBam.controller.member;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
	public ModelAndView viewDBoardList(@PathVariable("orderBy") String orderBy, HttpServletRequest request) {
		
		//게시판으로 바로 들어왔을 경우, 사용자 식별을 위한 값 저장
		ServletContext application = request.getServletContext();
		String key = (String) application.getAttribute("save");
		if(key == null) {
			key = getNum();
			application.setAttribute("save", key);
		}
		
		List<DebugBoardDTO> list = serv.viewDBoardList(orderBy);
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("member/Test/viewDBoardList");
		return mav;
	}
	
	//현재 글의 이전 글 보기
	public DebugBoardDTO prevPost(int boardNum) {
		DebugBoardDTO prevPost = serv.prevPost(boardNum);
		return prevPost;
	}
	
	
	//현재 글의 다음 글 보기
	public DebugBoardDTO nextPost(int boardNum) {
		DebugBoardDTO nextPost = serv.nextPost(boardNum);
		return nextPost;
	}
	
	//게시판 글 검색하기
	@PostMapping("/searchPost")
	public ModelAndView searchPost(String searchTag, String searchData) {
		HashMap<String, String> map = new HashMap<>();
			map.put("searchTag", searchTag);
			map.put("searchData", searchData);
		List<DebugBoardDTO> list = serv.searchList(map);
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("member/Test/viewDBoardList");
		return mav; 
	}
	
	//게시판 글 쓰기 화면으로 이동
	@PostMapping("/newPost")
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
			mav.setViewName("redirect:/viewDBoardList/boardNum");
		return mav;
	}
	
	//게시판 글 보기
	@GetMapping("/viewDBoardContent/{bNum}")
	public ModelAndView viewDBoardContent(@PathVariable("bNum") int boardNum, HttpServletRequest request) {
		
		//게시판 글로 바로 들어왔을 경우, 사용자 식별을 위한 값 저장
		ServletContext application = request.getServletContext();
		String key = (String) application.getAttribute("save");
		if(key == null) {
			key = getNum();
			application.setAttribute("save", key);
		}
		ModelAndView mav = new ModelAndView();
		
		
		
		//현재 키와 application 안의 키를 비교한다(이때 application 순회를 돌아서 일치하는 키를 찾는다)
		//그 안에서 가려고 하는 페이지 (boardNum)과 일치하는 Key값을 찾는다
		//그 key를 통해 value(추천/비추천/노말) 여부를 찾는다
		//이걸 mav로 넘긴다
		
		
		
		String recommendVal = (String) application.getAttribute("recommendVal");
		System.out.println(recommendVal);					////////////////////////////////////////////
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);
		mav.addObject("recommendVal", recommendVal);		///////////////////////////////////////			
		
		
		
		
		
		//게시판 글 조회수 올리기
		serv.updateDBoardViewCount(boardNum);					//조회수 증가 안 됨  - 이유 모름

		
		
		
		
		
		//이전글 | 다음글 가져오기
		DebugBoardDTO prev = prevPost(boardNum);
		DebugBoardDTO next = nextPost(boardNum);
			
		mav.addObject("dto", dto);
		mav.addObject("prev", prev);
		mav.addObject("next", next);
		mav.setViewName("member/Test/viewDBoardContent");
		return mav;
	}
	
	//게시판 글 수정하기	전 비밀번호 확인
	@PostMapping("/checkUpdatePost/{bNum}")
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
	@PostMapping("/checkDeletePost/{bNum}")
	public ModelAndView checkDeletePost(@PathVariable("bNum") int boardNum) {
		DebugBoardDTO dto = serv.viewDBoardContent(boardNum);
		ModelAndView mav = new ModelAndView("member/Test/checkDeletePost");
	    mav.addObject("dto", dto);
	    return mav;
	}
		
	//게시판 글 삭제하기	
	@PostMapping("/deletePost")
	public String  deletePost(String nickname, int boardNum) {
		serv.deleteDBoard(boardNum);
		return "redirect:/viewDBoardList/"+boardNum;
	}
	
	//게시판 사용자 어플리케이션용 무작위 16자리 숫자 가져오기
	public String getNum() {
	    Random r = new Random();
	    StringBuilder randomNumber = new StringBuilder();
	    for (int i = 0; i < 16; i++) {
	        randomNumber.append(r.nextInt(10));
	    }
	    System.out.println(randomNumber.toString());
	    return randomNumber.toString();
	}

	//게시판 사용자 어플리케이션용 암호화 IP 가져오기(현재 미사용***************************)
	public static String getIp(){
	    String result = null;
	    try {
	        result = InetAddress.getLocalHost().getHostAddress();
	    } catch (UnknownHostException e) {
	        result = "";
	    }
	    result = SecurityController.encrypt(result);
	   return result; 
	}//*********************************************************************
	
}
