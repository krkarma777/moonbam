package com.moonBam.dao.member;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.MemberDTO;

@Repository
public class OpenApiDAO {

	@Autowired
	SqlSessionTemplate session;

	public void insertAPIMember(MemberDTO dto) {
		session.insert("insertAPIMember", dto);
	}

	public MemberDTO selectOneAPIMember(String userId) {
		MemberDTO dto = session.selectOne("selectOneAPIMember", userId);
		return dto;
	}

	public void updateAPIMemberNickname(Map<String, String> map) {
		session.update("updateAPIMemberNickname", map);
	}

	public void updateAPIMemberGoogleConnected(String userId) {
		session.update("updateAPIMemberGoogleConnected", userId);
	}

	public void updateAPIMemberNaverConnected(String userId) {
		session.update("updateAPIMemberNaverConnected", userId);
	}

	public void updateAPIMemberKakaoConnected(String userId) {
		session.update("updateAPIMemberKakaoConnected", userId);
	}
	
	
}
