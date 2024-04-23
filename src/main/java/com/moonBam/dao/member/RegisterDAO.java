package com.moonBam.dao.member;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.MemberDTO;

import java.util.Map;

@Repository
public class RegisterDAO {

	@Autowired
	SqlSessionTemplate session;
	
	//회원가입
	public int insertNewMember(MemberDTO dto) {
		int num = session.insert("com.config.MemberMapper.insertNewMember", dto);
		return num;
	}

	//아이디 중복 검사 에이젝스
	public boolean isUserIdDuplicate(String userId) {
		int num = session.selectOne("com.config.MemberMapper.isUserIdDuplicate", userId);
		return num > 0;
	}

	//닉네임 중복 검사 에이젝스
	public boolean isUserNicknameDuplicate(String nickname) {
		int num = session.selectOne("com.config.MemberMapper.isUserNicknameDuplicate", nickname);
		return num > 0;
	}

	//핸드폰 번호 중복 검사 에이젝스
	public boolean isUserPNDuplicate(Map<String, String> dataForFindExistPN) {
		int num = session.selectOne("com.config.MemberMapper.isUserPNDuplicate", dataForFindExistPN);
		return num > 0;
	}

	//이메일 중복 검사 에이젝스
	public boolean isUserEmailDuplicate(Map<String, String> dataForFindExistEmail) {
		int num = session.selectOne("com.config.MemberMapper.isUserEmailDuplicate", dataForFindExistEmail);
		return num > 0;
	}

	public MemberDTO findDTOByUserId(String userId) {
		return session.selectOne("com.config.MemberMapper.findDTOByUserId", userId);
	}
}