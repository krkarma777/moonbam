package com.moonBam.service.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.member.MemberDAO;
import com.moonBam.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;

	public void update(String userName, String nickname, String userPhoneNum1, String userPhoneNum2,
			String userPhoneNum3) {
		dao.updateUser(userName,  nickname,  userPhoneNum1,  userPhoneNum2,
				 userPhoneNum3);
		
	}

	public MemberDTO select(String userId) {
		// TODO Auto-generated method stub
		return null;
	}



}