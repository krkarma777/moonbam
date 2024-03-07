package com.moonBam.dao.member;


import com.moonBam.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LoginDAO {

	@Autowired
	SqlSessionTemplate session;
	
	//디버그용 - 회원 정보 찾기
	public List<MemberDTO> selectAll() {
		List<MemberDTO> list = session.selectList("selectAll");
		System.out.println("dao "+ list);
		return list;
	}
	
	//디버그용 - 회원 삭제
	public int IDDelete(String userId) {
		int num = session.delete("IDDelete", userId);
	return num;
	}	
	
	//아이디 찾기
	public MemberDTO findUserId(Map<String, String> dataForFindUserId) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.findUserId", dataForFindUserId);
		return dto;
	}

	//비밀번호 찾기
	public MemberDTO findUserPW(Map<String, String> dataForFindUserPW) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.findUserPW", dataForFindUserPW);
		return dto;
	}
	
	//로그인
	public MemberDTO login(Map<String, String> idPW) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.login", idPW);
		return dto;
	}
	
	//로그인 에이젝스
	public boolean loginPossible(Map<String, String> dataForLogin) {
		int num = session.selectOne("com.config.MemberMapper.loginPossible", dataForLogin);
		return num > 0;
	}

	//전체 비밀번호 - 닉네임으로 찾기
	public boolean findPWbyNickname(HashMap<String, String> nicknameMap) {
		int num = session.selectOne("com.config.MemberMapper.findPWbyNickname", nicknameMap);
		return num > 0;
	}

	//전체 비밀번호 - 이메일로 찾기
	public boolean findPWbyEmail(HashMap<String, String> emailMap) {
		int num = session.selectOne("com.config.MemberMapper.findPWbyEmail", emailMap);
		return num > 0;
	}
	
	//전체 비밀번호 - 핸드폰 번호로 찾기
	public boolean findPWbyPhoneNum(Map<String, String> phoneNumMap) {
		int num = session.selectOne("com.config.MemberMapper.findPWbyPhoneNum", phoneNumMap);
		return num > 0;
	}
	
	//전체 비밀번호 출력용
	public MemberDTO selectMemberData(String userId) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.selectMemberData", userId);
		return dto;
	}
}