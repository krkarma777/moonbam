package com.moonBam.service.member;


import com.moonBam.dao.member.LoginDAO;
import com.moonBam.dto.MemberDTO;

import com.moonBam.dto.member.RestoreRestrictedMember;
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
	public MemberDTO findDTOBySecretCode(String secretCode) {
		MemberDTO dto = dao.findDTOBySecretCode(secretCode);
		return dto;
	}

	//비밀번호 찾기
	public MemberDTO mailingPW(Map<String, String> map) {
		MemberDTO dto = dao.mailingPW(map);
		return dto;
	}

	//로그인***************************************************************************************
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

	//비밀번호 업데이트
	public void updatePassword(Map<String, String> map) {
		dao.updatePassword(map);
	}

	//닉네임 찾기
	public String nicknameByUserId(String userId) {
		String nickname = dao.nicknameByUserId(userId);
		return nickname;
	}

	//아이디 찾기를 위한 전체 SecretCode 추출
    public List<String> allSecretCode() {
    	List<String> list = dao.allSecretCode();
		return list;
	}

	//자진 탈퇴 회원 복구
	public RestoreRestrictedMember restoreMember(String userId) {
		return dao.restoreMember(userId);
	}

	//자진 탈퇴 회원 복구 완료
	public void updateIsEnabled(String userId) {
		dao.updateIsEnabled(userId);
	}
}