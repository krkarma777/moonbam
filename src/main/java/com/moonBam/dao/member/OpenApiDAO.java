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

	public MemberDTO selectOneAPIMember(String username) {
		MemberDTO dto = session.selectOne("selectOneAPIMember", username);
		return dto;
	}

	public void updateAPIMemberNickname(Map<String, String> map) {
		session.update("updateAPIMemberNickname", map);
		
	}

	public void updateAPIMemberGoogleConnected(String username) {
		session.update("updateAPIMemberGoogleConnected", username);
	}

	public void updateAPIMemberNaverConnected(String username) {
		session.update("updateAPIMemberNaverConnected", username);
	}

	public void updateAPIMemberKakaoConnected(String username) {
		session.update("updateAPIMemberKakaoConnected", username);
	}
	
	
}
