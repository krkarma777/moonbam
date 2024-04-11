package com.moonBam.controller.springSecurity;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.MemberDTO;


@Repository
public class SpringSecurityDAO {
	@Autowired
	SqlSessionTemplate session;
	
	public MemberDTO userDetail(String userId) {
			System.out.println("userDetail1: 들어가는 데이터 :"+ userId);
			MemberDTO dto= session.selectOne("userDetail", userId);
			System.out.println("userDetail1: 나오는 데이터: "+ dto);
		return dto;
	}
}