package com.moonBam.dao.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.MemberDTO;

import java.util.List;
import java.util.Map;

@Repository
public class LoginDAO {

	@Autowired
	SqlSessionTemplate session;
	
	// 스프링시큐리티용 - 아이디로 회원 정보 찾기
	public MemberDTO userDetail(String userId) {
		MemberDTO dto= session.selectOne("userDetail", userId);
		return dto;
	}
	
	//디버그용 - 회원 정보 찾기
	public List<MemberDTO> selectAll() {
		List<MemberDTO> list = session.selectList("selectAll");
		return list;
	}
	
	//디버그용 - 회원 삭제
	public int IDDelete(String userId) {
		int num = session.delete("IDDelete", userId);
	return num;
	}	
	
	//아이디 찾기
	public MemberDTO findDTOBySecretCode(String secretCode) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.findDTOBySecretCode", secretCode);
		return dto;
	}
	
	//비밀번호 찾기
	public MemberDTO mailingPW(Map<String, String> map) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.mailingPW", map);
		return dto;
	}

	//로그인***************************************************************************************************************
	public MemberDTO login(Map<String, String> idPW) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.login", idPW);
		return dto;
	}
	
	//로그인 에이젝스
	public boolean loginPossible(Map<String, String> dataForLogin) {
		int num = session.selectOne("com.config.MemberMapper.loginPossible", dataForLogin);
		return num > 0;
	}

	//비밀번호 업데이트
	public void updatePassword(Map<String, String> map) {
		session.selectOne("com.config.MemberMapper.updatePassword", map);
	}
	
	//닉네임 찾기
	public String nicknameByUserId(String userId) {
		String nickname = session.selectOne("nicknameByUserId", userId);
		return nickname;
	}
}