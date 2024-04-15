package com.moonBam.controller.member;

import com.moonBam.dto.AnonymousBoardDTO;
import com.moonBam.dto.AnonymousCommentDTO;
import com.moonBam.dto.AnonymousReplyDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class AjaxController {

	@Autowired
	LoginService lServ;

	@Autowired
	RegisterService rServ;
	
	@Autowired
	SecurityController sc;

	@Autowired
	AnonymousBoardService dServ;
	
	@Autowired
	AnonymousCommentService anonymousCommentService;
	
	@Autowired
	AnonymousReplyService anonymousReplyService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	MemberService memberService;
	
	//***************************************************************************************************************
	//***************************************************로 그 인*******************************************************
	//***************************************************************************************************************

	//메인에서 로그인 여부 확인 에이젝스
	@PostMapping("AjaxCheckIDPW")
	public String AjaxCheckIDPW(String userId, String userPw) {
		MemberDTO memberDTO = memberService.findByUserId(userId);
		if (memberDTO == null) {
			return "loginFail";
		}
		String userPwOrigin = memberService.findByUserId(userId).getUserPw();
		if (!bCryptPasswordEncoder.matches(userPw, userPwOrigin)) {
			return "loginFail";
		}
		return "loginSuccess";
	}
	
	//메인에서 이메일 중복 확인 에이젝스
	@PostMapping("AjaxCheckEmail")
	public String AjaxCheckEmail(String userId) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		boolean cantRegister = rServ.RegisterPossible(userId);
		String mesg = "RegisterSuccess";
		if (cantRegister) {
			mesg = "RegisterFail";                
        }
		return mesg;
	}
	
	//전체 비밀번호 찾기에서 질문에 따른 대답 확인 에이젝스
	@PostMapping("AjaxMatchQnA")
	public String AjaxMatchQnA(String userInfo, String answer, String userId) {
		
		boolean can_All_PW = false;
		String mesg = "correct_Answer";
																        //디버그 코드*****************************
																        System.out.println(userInfo);
																        System.out.println(answer);
																        System.out.println(userId);
																        //*************************************
        
		// 선택된 질문에 따라 사용되는 Method 변경**************************
		if (userInfo.equals("nickname")) {
		can_All_PW = lServ.findPWbyNickname(answer, userId);
		} 
		if (userInfo.equals("restoreUserEmail")) {
		can_All_PW = lServ.findPWbyEmail(answer, userId);
		}
		// 선택된 질문에 따라 사용되는 Method 변경**************************
		
																		//디버그 코드*****************************                 
																		System.out.println(can_All_PW);    
																		//*************************************
																		
	    //사용자 ID와 질문과 답변이 일치하지 않을 경우, ajax출력																			
		if (can_All_PW == false){
	        mesg = "wrong_Answer";
	    }						

		//사용자 ID와 질문과 답변이 일치할 경우, ajax출력
		return mesg;
			
	}
	
	
	
	//***************************************************************************************************************
	//***************************************************회원 가입*******************************************************
	//***************************************************************************************************************
	
	//회원가입 자식창에서 아이디 중복 에이젝스
	@PostMapping("/AjaxIDDuplicate")
	public String AjaxIDDuplicate(String userId) {
		boolean isDuplicate = rServ.isUserIdDuplicate(userId);
		String mesg = "notDuplicate";
		if (isDuplicate) {
			mesg = "duplicate"; 
        } 
		return mesg;
	}
	
	//회원가입 자식창에서 닉네임 중복 에이젝스
	@PostMapping("/AjaxNicknameDuplicate")
	public String AjaxNicknameDuplicate(String nickname) {
		boolean isDuplicate = rServ.isUserNicknameDuplicate(nickname);
		String mesg = "notDuplicate";
		if (isDuplicate) {
			mesg = "duplicate"; 
        } 
		return mesg;
	}
		
	//***************************************************************************************************************
	//***************************************************게 시 판*******************************************************
	//***************************************************************************************************************
		
	
	//익명 유저가 게시판에 추천을 했는지 여부를 쿠키로 저장
	@PostMapping("/increaseDBoardRecommendNum")
	public int increaseDBoardRecommendNum(String userKey, int boardNum, String recommendVal, HttpServletRequest request, HttpServletResponse response) {
		
		AnonymousBoardDTO dto = dServ.viewDBoardContent(boardNum);
		int recommendNum = dto.getRecommendNum();
		
		//페이지에서 불러온 현재 접속한 유저Key | 페이지 번호 | 좋아요 상태
		//System.out.println("userKey: " + userKey);
		//System.out.println("boardNum: "+ boardNum);
		//System.out.println("recommendVal: "+recommendVal);
		
		String LikeCookieKey = "K"+userKey+"N"+boardNum+"Like";
		//System.out.println(LikeCookieKey);
		
		if(recommendVal.equals("like")) {
			dServ.increaseDBoardRecommendNum(boardNum);
			dto = dServ.viewDBoardContent(boardNum);
			recommendNum = dto.getRecommendNum();
			
			Cookie key= new Cookie(LikeCookieKey, recommendVal);
			key.setMaxAge(60*60*24);
			response.addCookie(key);
			//System.out.println("userKey에 따른 페이지 좋아요 Cookie 생성");
		}
		
		return recommendNum;
	}
	
	//익명 유저가 게시판에 비추천을 했는지 여부를 쿠키로 저장
	@PostMapping("/decreaseDBoardRecommendNum")
	public int decreaseDBoardRecommendNum(String userKey, int boardNum, String disrecommendVal, HttpServletRequest request, HttpServletResponse response) {
		
		AnonymousBoardDTO dto = dServ.viewDBoardContent(boardNum);
		int disRecommendNum = dto.getDisRecommendNum();
		
		//페이지에서 불러온 현재 접속한 유저Key | 페이지 번호 | 좋아요 상태
		//System.out.println("userKey: " + userKey);
		//System.out.println("boardNum: "+ boardNum);
		//System.out.println("disrecommendVal: "+disrecommendVal);
		
		String disLikeCookieKey = "K"+userKey+"N"+boardNum+"disLike";
		//System.out.println(disLikeCookieKey);
		
		if(disrecommendVal.equals("dislike")) {
			dServ.decreaseDBoardRecommendNum(boardNum);
			dto = dServ.viewDBoardContent(boardNum);
			disRecommendNum = dto.getDisRecommendNum();
			
			Cookie key= new Cookie(disLikeCookieKey, disrecommendVal);
			key.setMaxAge(60*60*24);
			response.addCookie(key);
			//	System.out.println("userKey에 따른 페이지 싫어요 Cookie 생성");
		}
		
		return disRecommendNum;
	}
	
	
	//글 수정 / 글 삭제 시 비밀번호 확인
	@PostMapping("/checkPostPW")
	public String checkPostPW(int boardNum, String password) {
		AnonymousBoardDTO dto = dServ.viewDBoardContent(boardNum);
		if(dto.getPassword().equals(password)) {
			return "yes";
		} else {
			return "no";
		}
	}
	
	
	
	//***************************************************************************************************************
	//****************************************************댓 글*******************************************************
	//***************************************************************************************************************
	
	//해당 게시판의 댓글 출력
	@PostMapping("/allComments")
    public List<AnonymousCommentDTO> getComments(int boardNum) throws ParseException {
		List<AnonymousCommentDTO> comments = anonymousCommentService.getAllComments(boardNum);
		
		//리스트의 날짜 형식 변경
		for (AnonymousCommentDTO anonymousCommentDTO : comments) {
			anonymousCommentDTO.setCommentEdittedDate(chooseDateForm(anonymousCommentDTO.getCommentEdittedDate()));
		}
		
		return comments;
    }

	//댓글 추가
	@PostMapping("/addComment")
    public AnonymousCommentDTO addComment(AnonymousCommentDTO dto) {
		
		Date nowDate = new Date();
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String now = format.format(nowDate);
		    dto.setCommentPostDate(now);
		    dto.setCommentEdittedDate(now);
		    
		anonymousCommentService.addComment(dto);
		
        return dto;
    }
	
	//댓글 삭제
	@DeleteMapping("/deleteComment")
    public boolean deleteComment(int anonymousCommentNum, String commentPassword) {
		
		Map<String, Object > map = new HashMap<>();
			map.put("anonymousCommentNum", anonymousCommentNum);
			map.put("commentPassword", commentPassword);
		
		int num = anonymousCommentService.deleteComment(map);
		
		if (num == 0) {
			return false;
		} else {
			return true;
		}
    }
	
	
	//해당 게시판의 대댓글 출력
	@PostMapping("/allReplies")
    public List<AnonymousReplyDTO> getAllReplys(int anonymousCommentNum) throws ParseException {
		
		List<AnonymousReplyDTO> replies = anonymousReplyService.getAllReplys(anonymousCommentNum);
		
		//리스트의 날짜 형식 변경
		for (AnonymousReplyDTO anonymousReplyDTO : replies) {
			anonymousReplyDTO.setReplyEdittedDate(chooseDateForm(anonymousReplyDTO.getReplyEdittedDate()));
		}
		
		return replies;
    }
	
	
	//대댓글 추가
	@PostMapping("/addReply")
    public AnonymousReplyDTO addReply(AnonymousReplyDTO dto) {
		
		Date nowDate = new Date();
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String now = format.format(nowDate);
		    dto.setReplyPostDate(now);
		    dto.setReplyEdittedDate(now);
		    
		anonymousReplyService.addReply(dto);
		
        return dto;
    }
	
	//대댓글 삭제
	@DeleteMapping("/deleteReply")
    public boolean deleteReply(int anonymousReplyNum, String replyPassword) {
		
		Map<String, Object > map = new HashMap<>();
			map.put("anonymousReplyNum", anonymousReplyNum);
			map.put("replyPassword", replyPassword);
		
		int num = anonymousReplyService.deleteReply(map);
		
		if (num == 0) {
			return false;
		} else {
			return true;
		}
    }
	
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
