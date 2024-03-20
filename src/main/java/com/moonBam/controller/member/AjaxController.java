package com.moonBam.controller.member;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
		
	
	//게시판 추천수 증감
	@PostMapping("/updateDBoardRecommendNum")
	public int updateDBoardRecommendNum(int boardNum, String recommendVal, HttpServletRequest request) {
		
		DebugBoardDTO dto = dServ.viewDBoardContent(boardNum);
		int recommendNum = dto.getRecommendNum();
		int num = 0;
		
		System.out.println(recommendVal);
		if(recommendVal.equals("like")) {
			num = 1;
		} else if(recommendVal.equals("dislike")) {
			num = -2;
		} else if(recommendVal.equals("normal")){
			num = 1;
		}
		recommendNum += num;

		
		// 추천|비추|일반 상태 ***************************************
		ServletContext application = request.getServletContext();
		String id = (String) application.getAttribute("save");
		HashMap<Integer, String> numNrecommend = new HashMap<>();
			numNrecommend.put(boardNum, recommendVal);
		HashMap<String, Object> idNnumNrecommend = new HashMap<>();
			idNnumNrecommend.put(id, numNrecommend);
			
		application.setAttribute("idNnumNrecommend", idNnumNrecommend);
		// 추천|비추|일반 상태 ***************************************

		
		
		
		HashMap<String, Integer> map = new HashMap<>();
			map.put("boardNum", boardNum);
			map.put("recommendNum", recommendNum);
		
		dServ.updateDBoardRecommendNum(map);
		return recommendNum;
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
