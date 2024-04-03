package com.moonBam.dao.member;


import com.moonBam.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;

	public void updateUser(String userName, String nickname, String userPhoneNum1, String userPhoneNum2,
			String userPhoneNum3) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userName", userName);
		paramMap.put("nickname", nickname);
		paramMap.put("userPhoneNum1", userPhoneNum1);
		paramMap.put("userPhoneNum2", userPhoneNum2);
		paramMap.put("userPhoneNum3", userPhoneNum3);

		session.update("updateUser", paramMap);
		
	}
	
	
}