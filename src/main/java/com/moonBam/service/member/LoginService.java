package com.moonBam.service.member;


import com.moonBam.dao.member.LoginDAO;
import com.moonBam.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

	@Autowired
	LoginDAO dao;

	//디버그용 - 회원 정보 찾기
	public List<MemberDTO> selectAll() {
		List<MemberDTO> list = dao.selectAll();
	return list;
	}
	
	//디버그용 - 회원 삭제
	public int IDDelete(String userId) {
		int num = dao.IDDelete(userId);
	return num;
	}
	
	//아이디 찾기
	public MemberDTO findUserId(String userName, String ssn1, String ssn2) {
		HashMap<String, String> dataForFindUserId = new HashMap<String, String>();
			dataForFindUserId.put("userName", userName);
			dataForFindUserId.put("ssn1", ssn1);
			dataForFindUserId.put("ssn2", ssn2);
		MemberDTO dto = dao.findUserId(dataForFindUserId);
		return dto;
	}

	//비밀번호 찾기
	public MemberDTO findUserPW(String userId, String userName, String ssn1, String ssn2) {
		Map<String, String> dataForFindUserPW = new HashMap<String, String>();
			dataForFindUserPW.put("userId", userId);
			dataForFindUserPW.put("userName", userName);
			dataForFindUserPW.put("ssn1", ssn1);
			dataForFindUserPW.put("ssn2", ssn2);
		MemberDTO dto = dao.findUserPW(dataForFindUserPW);
		return dto;
	}

	//로그인
	public MemberDTO login(String userId, String userPw) {
		Map<String, String> idPW = new HashMap<String, String>();
			idPW.put("userId", userId);
			idPW.put("userPw", userPw);
		MemberDTO dto = dao.login(idPW);
		return dto;
	}
	
	//로그인 에이젝스
	public boolean loginPossible(String userId, String userPw) {
		Map<String, String> dataForLogin = new HashMap<String, String>();
			dataForLogin.put("userId", userId);
			dataForLogin.put("userPw", userPw);
		return dao.loginPossible(dataForLogin);
	}

	//전체 비밀번호 - 닉네임으로 찾기
	public boolean findPWbyNickname(String userAnswer, String userid) {
		HashMap<String, String> nicknameMap = new HashMap<String, String>();
			nicknameMap.put("nickname", userAnswer);
			nicknameMap.put("userid", userid);
		return dao.findPWbyNickname(nicknameMap);
	}
	
	//전체 비밀번호 - 이메일로 찾기
	public boolean findPWbyEmail(String userAnswer, String userid) {
		String[] emailParts = userAnswer.split("@");
		HashMap<String, String> emailMap = new HashMap<String, String>();
			emailMap.put("userEmailId", emailParts[0]);
			emailMap.put("userEmailDomain", emailParts[1]);
			emailMap.put("userid", userid);
		return dao.findPWbyEmail(emailMap);
	}

	//전체 비밀번호 - 핸드폰 번호로 찾기
	public boolean findPWbyPhoneNum(String userAnswer, String userid) {
		Map<String, String> phoneNumMap = new HashMap<String, String>();
			phoneNumMap.put("userPhoneNum1", userAnswer.substring(0, 3));
			phoneNumMap.put("userPhoneNum2", userAnswer.substring(3, 7));
			phoneNumMap.put("userPhoneNum3", userAnswer.substring(7));
			phoneNumMap.put("userid", userid);
		return dao.findPWbyPhoneNum(phoneNumMap);
	}

	//전체 비밀번호 출력용
	public MemberDTO selectMemberData(String userId) {
		MemberDTO dto = dao.selectMemberData(userId);
		return dto;
	}

}