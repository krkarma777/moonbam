package com.moonbam.service.adminpage;

import com.moonbam.dao.AdminMemberDAO;
import com.moonbam.dto.AdminMemberDTO;
import com.moonbam.dto.AdminRestrictedMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminMemberService {
	
	@Autowired
	AdminMemberDAO mdao;

	
	
	//신고회원 조회
	public List<AdminMemberDTO> SearchMember(HashMap<String, String> map) {
		List<AdminMemberDTO> list = null;
		list = mdao.SearchMember(map);
		
		return list;
	}
	
	//이용제한회원 조회
	public List<AdminRestrictedMemberDTO> getRestrictedMemberList(){
//		System.out.println("서비스 시작함");
		List<AdminRestrictedMemberDTO> list = mdao.getRestrictedMemberList();
//		System.out.println("서비스 다녀감");
		return list;
	}
	
}
