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
	public int IDDelete(String username) {
		int num = dao.IDDelete(username);
	return num;
	}
	
	//아이디 찾기
	public MemberDTO findUsername(Map<String, String> map) {
		MemberDTO dto = dao.findUsername(map);
		return dto;
	}

	//로그인
	public MemberDTO login(String username, String password) {
		Map<String, String> idPW = new HashMap<String, String>();
			idPW.put("username", username);
			idPW.put("password", password);
		MemberDTO dto = dao.login(idPW);
		return dto;
	}
	
	//로그인 에이젝스
	public boolean loginPossible(String username, String password) {
		Map<String, String> dataForLogin = new HashMap<String, String>();
			dataForLogin.put("username", username);
			dataForLogin.put("password", password);
		return dao.loginPossible(dataForLogin);
	}

	//전체 비밀번호 - 닉네임으로 찾기
	public boolean findPWbyNickname(String userAnswer, String username) {
		HashMap<String, String> nicknameMap = new HashMap<String, String>();
			nicknameMap.put("nickname", userAnswer);
			nicknameMap.put("username", username);
		return dao.findPWbyNickname(nicknameMap);
	}
	
	//전체 비밀번호 - 이메일로 찾기
	public boolean findPWbyEmail(String userAnswer, String username) {
		String[] emailParts = userAnswer.split("@");
		HashMap<String, String> emailMap = new HashMap<String, String>();
			emailMap.put("restoreUserEmailId", emailParts[0]);
			emailMap.put("restoreUserEmailDomain", emailParts[1]);
			emailMap.put("username", username);
		return dao.findPWbyEmail(emailMap);
	}

	//전체 비밀번호 출력용
	public MemberDTO selectMemberData(String username) {
		MemberDTO dto = dao.selectMemberData(username);
		return dto;
	}

	public void updatePassword(Map<String, String> map) {
		dao.updatePassword(map);
	}

}