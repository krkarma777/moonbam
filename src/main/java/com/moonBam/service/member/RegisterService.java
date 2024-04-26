package com.moonBam.service.member;

import com.moonBam.dao.member.RegisterDAO;
import com.moonBam.dto.MemberDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {

	@Autowired
	RegisterDAO dao;
	
	//회원가입
	public int insertNewMember(MemberDTO dto) {
		int num = dao.insertNewMember(dto);
		return num;
	}

	//아이디 중복 검사 에이젝스
	public boolean isUserIdDuplicate(String userId) {
		return dao.isUserIdDuplicate(userId);
	}

	//닉네임 중복 검사 에이젝스
	public boolean isUserNicknameDuplicate(String nickname) {
		return dao.isUserNicknameDuplicate(nickname);
	}

	//핸드폰 번호 중복 검사 에이젝스
	public boolean isUserPNDuplicate(String userPhoneNum1, String userPhoneNum2, String userPhoneNum3) {
		Map<String, String> dataForFindExistPN = new HashMap<String, String>();
			dataForFindExistPN.put("userPhoneNum1", userPhoneNum1);
			dataForFindExistPN.put("userPhoneNum2", userPhoneNum2);
			dataForFindExistPN.put("userPhoneNum3", userPhoneNum3);
		return dao.isUserPNDuplicate(dataForFindExistPN);
	}

	//이메일 중복 검사 에이젝스
	public boolean isUserEmailDuplicate(String userEmailId, String userEmailDomain) {
		Map<String, String> dataForFindExistEmail = new HashMap<String, String>();
			dataForFindExistEmail.put("userEmailId", userEmailId);
			dataForFindExistEmail.put("userEmailDomain", userEmailDomain);
		return dao.isUserEmailDuplicate(dataForFindExistEmail);
	}

	public MemberDTO findDTOByUserId(String userId) {
		return dao.findDTOByUserId(userId);
	}

}