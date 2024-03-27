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

	public MemberDTO selectOneAPIMember(String userId) {
		MemberDTO dto = dao.selectOneAPIMember(userId);
		return dto;
	}

	public void updateAPIMemberNickname(Map<String, String> map) {
		dao.updateAPIMemberNickname(map);
	}
	
}
