package com.moonBam.controller.member;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moonBam.dto.DebugBoardDTO;
import com.moonBam.service.member.DebugBoardService;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.RegisterService;


@RestController
public class AjaxController {

	@Autowired
	LoginService lServ;

	@Autowired
	RegisterService rServ;
	
	@Autowired
	SecurityController sc;

	@Autowired
	DebugBoardService dServ;
	
	//***************************************************************************************************************
	//***************************************************로 그 인*******************************************************
	//***************************************************************************************************************
	
	//메인에서 로그인 여부 확인 에이젝스
	@PostMapping("AjaxCheckIDPW")
	public String AjaxCheckIDPW(String userId, String userPw) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		String realUserPw = sc.encrypt(userPw);
		boolean canLogin = lServ.loginPossible(userId, realUserPw);
		String mesg = "loginSuccess";
		if (!canLogin) {
			mesg = "loginFail";                
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
		} else if (userInfo.equals("userPhoneNum")) {
		can_All_PW = lServ.findPWbyPhoneNum(answer, userId);
		} else if (userInfo.equals("userEmail")) {
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
		
	//회원가입 자식창에서 이메일 중복 에이젝스
	@PostMapping("/AjaxEmailDuplicate")
	public String AjaxEmailDuplicate(String userEmailId, String userEmailDomain) {
		boolean isDuplicate = rServ.isUserEmailDuplicate(userEmailId, userEmailDomain);
		String mesg = "notDuplicate";
		if (isDuplicate) {
			mesg = "duplicate"; 
        } 
		return mesg;
	}		
	
	//회원가입 자식창에서 핸드폰 번호 중복 에이젝스
	@PostMapping("/AjaxPhoneNumDuplicate")
	public String AjaxPhoneNumDuplicate(String userPhoneNum1, String userPhoneNum2, String userPhoneNum3) {
		boolean isDuplicate = rServ.isUserPNDuplicate(userPhoneNum1, userPhoneNum2, userPhoneNum3);
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
		
		DebugBoardDTO dto = dServ.viewDBoardContent(boardNum);
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
		
		DebugBoardDTO dto = dServ.viewDBoardContent(boardNum);
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
		DebugBoardDTO dto = dServ.viewDBoardContent(boardNum);
		if(dto.getPassword().equals(password)) {
			return "yes";
		} else {
			return "no";
		}
	}
	
	
	
}
