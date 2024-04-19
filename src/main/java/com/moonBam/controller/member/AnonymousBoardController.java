package com.moonBam.controller.member;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AnonymousBoardDTO;
import com.moonBam.dto.AnonymousCommentDTO;
import com.moonBam.service.member.AnonymousBoardService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AnonymousBoardController {

	@Autowired
	AnonymousBoardService serv;
	
	@Autowired
	AjaxController ajaxController;
	
	@Autowired
	static
	PasswordEncoder encoder;
	
	//게시판 글 목록 보기
	@GetMapping("/viewDBoardList")
	public ModelAndView viewDBoardList( 
			@RequestParam(defaultValue = "1") int currentPage, 
            @RequestParam(defaultValue = "10") int perPage,
			String orderBy, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		// 전체 글 개수 가져오기 (페이지네이션에 사용)
	    List<AnonymousBoardDTO> allPosts  = serv.viewDBoardList(orderBy);
	    int totalPosts = allPosts.size();
		
		// 현재 페이지에 해당하는 글 목록 생성
	    List<AnonymousBoardDTO> list = new ArrayList<>();
	    int startIndex = (currentPage - 1) * perPage;
	    int endIndex = Math.min(startIndex + perPage, totalPosts);
	    for (int i = startIndex; i < endIndex; i++) {
	        list.add(allPosts.get(i));
	    }

		//리스트의 날짜 형식 변경
		for (AnonymousBoardDTO debugBoardDTO : list) {
			debugBoardDTO.setEdittedDate(chooseDateForm(debugBoardDTO.getEdittedDate()));
		}
		
		ModelAndView mav = new ModelAndView();
			mav.addObject("currentPage", currentPage);
		    mav.addObject("perPage", perPage);
		    mav.addObject("orderBy", orderBy);
		    mav.addObject("totalPosts", totalPosts);
			mav.addObject("list", list);
			mav.setViewName("member/Board/viewDBoardList");
		return mav;
	}
	
	
	//현재 글의 이전 글 보기
	public AnonymousBoardDTO prevPost(int boardNum) throws ParseException {
		AnonymousBoardDTO prevPost = serv.prevPost(boardNum);
		return prevPost;
	}
	
	
	//현재 글의 다음 글 보기
	public AnonymousBoardDTO nextPost(int boardNum) throws ParseException {
		AnonymousBoardDTO nextPost = serv.nextPost(boardNum);
		return nextPost;
	}
	
	
	//게시판 글 검색하기
	@PostMapping("/searchPost")
	public ModelAndView searchPost(
			@RequestParam(defaultValue = "1") int currentPage, 
            @RequestParam(defaultValue = "10") int perPage,
            String orderBy, String searchTag, String searchData) throws ParseException {
		HashMap<String, String> map = new HashMap<>();
			map.put("searchTag", searchTag);
			map.put("searchData", searchData);
			map.put("category", orderBy);
		
		List<AnonymousBoardDTO> allPosts  = serv.searchList(map);
	    int totalPosts = allPosts.size();
		
		// 현재 페이지에 해당하는 글 목록 생성
	    List<AnonymousBoardDTO> list = new ArrayList<>();
	    int startIndex = (currentPage - 1) * perPage;
	    int endIndex = Math.min(startIndex + perPage, totalPosts);
	    for (int i = startIndex; i < endIndex; i++) {
	        list.add(allPosts.get(i));
	    }

		//리스트의 날짜 형식 변경
		for (AnonymousBoardDTO debugBoardDTO : list) {
			debugBoardDTO.setEdittedDate(chooseDateForm(debugBoardDTO.getEdittedDate()));
		}
		
		ModelAndView mav = new ModelAndView();
			mav.addObject("currentPage", currentPage);
		    mav.addObject("perPage", perPage);
		    mav.addObject("orderBy", orderBy);
		    mav.addObject("totalPosts", totalPosts);
			mav.addObject("list", list);
			mav.setViewName("member/Board/viewDBoardList");
		return mav; 
	}
	
	
	//게시판 글 쓰기 화면으로 이동
	@PostMapping("/newPost")
	public ModelAndView newPost() {
		ModelAndView mav = new ModelAndView();
			mav.setViewName("member/Board/newPost");
		return mav;
	}
	
	
	//게시판 글 등록(PRG 패턴을 통해 중복 등록 방지)
	@PostMapping("/insertPost")
	public ModelAndView insertPost(AnonymousBoardDTO dto) {
		//추천|비추천 입력
		dto.setRecommendNum(0);
		dto.setDisRecommendNum(0);
		dto.setViewCount(0);
		
		
		//날짜 형식 변경
	    Date nowDate = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = format.format(nowDate);
	    dto.setPostDate(now);

		serv.insertPost(dto);
		
		List<AnonymousBoardDTO> list = serv.viewDBoardList("boardNum");
		ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("redirect:/viewDBoardList");
		return mav;
	}
	
	
	//게시판 글 보기
	@GetMapping("/viewDBoardContent")
	public ModelAndView viewDBoardContent(int boardNum, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		//쿠키에 user 식별 key 있는지 확인
		String userKey = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			//	System.out.println("userKey 탐색");
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals("userKey")) {
		        	userKey = cookie.getValue();
		            break; // 찾았으면 루프 종료
		        }
		    }
		    
			//글로 바로 들어왔을 경우, 사용자 식별을 위한 값을 쿠키에 저장
			if(userKey == "") {
				userKey = getNum(16);
				Cookie key= new Cookie("userKey", userKey);
				key.setMaxAge(60*60*24);
				response.addCookie(key);
				//System.out.println("====userKey Cookie 생성====");
			}
		}
		//System.out.println("userKey: " + userKey);
		
		
		//현재 게시판에 접속한 유저가 추천을 눌렀는지 쿠키에서 출력
		String LikeCookieKey = "K"+userKey+"N"+boardNum+"Like";
		String userBylike = "";
		cookies = request.getCookies();
		if (cookies != null) {
			//	System.out.println("CookieKey 탐색");
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals(LikeCookieKey)) {
		        	userBylike = cookie.getValue();
		            break; 
		        }
		    }
		}
		//System.out.println("recommendVal: "+ userBylike);
		
		
		//현재 게시판에 접속한 유저가 비추천을 눌렀는지 쿠키에서 출력
		String disLikeCookieKey = "K"+userKey+"N"+boardNum+"disLike";
		String userBydislike = "";
		cookies = request.getCookies();
		if (cookies != null) {
			//	System.out.println("CookieKey 탐색");
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals(disLikeCookieKey)) {
		        	userBydislike = cookie.getValue();
		            break; 
		        }
		    }
		}
		//System.out.println("disRecommendVal: "+ userBydislike);
		
		//이전글 | 다음글 가져오기
		AnonymousBoardDTO prev = prevPost(boardNum);
		if(prev != null) {							//마지막 글은 prev가 null값
			prev.setEdittedDate(chooseDateForm(prev.getEdittedDate()));
		}
		AnonymousBoardDTO next = nextPost(boardNum);
		if(next != null) {							//가장 최신 글은 next가 null값
			next.setEdittedDate(chooseDateForm(next.getEdittedDate()));
		}
		
		
		//조회수 증가
		serv.updateDBoardViewCount(boardNum);
		
		
		//날짜 형식 변경
		AnonymousBoardDTO dto = serv.viewDBoardContent(boardNum);
			dto.setEdittedDate(chooseDateForm(dto.getEdittedDate()));
			
		ModelAndView mav = new ModelAndView();
			mav.addObject("userBylike", userBylike);
			mav.addObject("userBydislike", userBydislike);
			mav.addObject("userKey", userKey);
			mav.addObject("dto", dto);
			mav.addObject("prev", prev);
			mav.addObject("next", next);
			
			mav.setViewName("member/Board/viewDBoardContent");
		return mav;
	}
	
	
	//게시판 글 수정하기	전 비밀번호 확인
	@PostMapping("/checkUpdatePost/{bNum}")
	public ModelAndView checkUpdatePost(@PathVariable("bNum") int boardNum) {
		AnonymousBoardDTO dto = serv.viewDBoardContent(boardNum);
	    ModelAndView mav = new ModelAndView("member/Board/checkUpdatePost");
	    	mav.addObject("dto", dto);
	    return mav;
	}
		
	
	//게시판 글 수정화면으로 이동
	@PostMapping("/checkUpdatePost/modifyPost")
	public ModelAndView modifyPost(int boardNum) {
		AnonymousBoardDTO dto = serv.viewDBoardContent(boardNum);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("member/Board/modifyPost");
	return mav;
	}

	
	//게시판 글 수정하기
	@PostMapping("/checkUpdatePost/updateDBoard")
	public String updateDBoard(AnonymousBoardDTO dto) {

		// 현재 날짜와 시간을 dto에 입력
		Date nowDate = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = format.format(nowDate);
	    dto.setEdittedDate(now);
	    
	    serv.updateDBoard(dto);
	    return "redirect:/viewDBoardContent?boardNum="+dto.getBoardNum();
	}
	
	
	//게시판 글 삭제 전 비밀번호 확인
	@PostMapping("/checkDeletePost/{bNum}")
	public ModelAndView checkDeletePost(@PathVariable("bNum") int boardNum) {
		AnonymousBoardDTO dto = serv.viewDBoardContent(boardNum);
		ModelAndView mav = new ModelAndView("member/Board/checkDeletePost");
	    mav.addObject("dto", dto);
	    return mav;
	}
		
	
	//게시판 글 삭제하기	
	@PostMapping("/deletePost")
	public String  deletePost(String nickname, int boardNum) {
		serv.deleteDBoard(boardNum);
		return "redirect:/viewDBoardList";
	}
	
	
	//게시판 사용자 어플리케이션용 무작위 N자리 숫자 가져오기
	public static String getNum(int num) {
	    Random r = new Random();
	    StringBuilder randomNumber = new StringBuilder();
	    for (int i = 0; i < num; i++) {
	        randomNumber.append(r.nextInt(10));
	    }
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
	    result = encoder.encode(result);
	    
	    return result; 
	}//*********************************************************************


	//글을 게시한 날짜와 오늘 날짜를 비교하는 함수
	public String chooseDateForm(String date) throws ParseException {
		
		String str = date;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date edittedDate = format.parse(str);									//등록, 수정된 날짜
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd");		//연월일 Format
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH시 mm분");			//시분 Format
		
		String todayForm = dateFormat.format(new Date());						//현재 시간의 연월일
		String edittedDateForm = dateFormat.format(edittedDate);				//등록, 수정된 날짜의 연월일
		String edittedDateTime = timeFormat.format(edittedDate);				//등록, 수정된 날짜의 시분초
		
		if(todayForm.equals(edittedDateForm)) {									//오늘이 글을 쓴 날짜일 경우
			return edittedDateTime;												//jsp에 시분초 전송
		} else {
			return edittedDateForm;												//jsp에 연월일 전송
		}
	}
}
