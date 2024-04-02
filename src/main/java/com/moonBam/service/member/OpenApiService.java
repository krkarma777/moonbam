package com.moonBam.service.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.member.OpenApiDAO;
import com.moonBam.dto.MemberDTO;

@Service
public class OpenApiService {

	@Autowired
	OpenApiDAO dao;

	public void insertAPIMember(MemberDTO dto) {
		dao.insertAPIMember(dto);
	}

	public MemberDTO selectOneAPIMember(String username) {
		MemberDTO dto = dao.selectOneAPIMember(username);
		return dto;
	}

	public void updateAPIMemberNickname(Map<String, String> map) {
		dao.updateAPIMemberNickname(map);
	}

	public void updateAPIMemberGoogleConnected(String username) {
		dao.updateAPIMemberGoogleConnected(username);
	}

	public void updateAPIMemberNaverConnected(String username) {
		dao.updateAPIMemberNaverConnected(username);
	}

	public void updateAPIMemberKakaoConnected(String username) {
		dao.updateAPIMemberKakaoConnected(username);
		
	}
	
}
