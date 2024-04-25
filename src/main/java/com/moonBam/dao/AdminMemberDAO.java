package com.moonBam.dao;


import com.moonBam.dto.AdminDeletedMemberDTO;
import com.moonBam.dto.AdminMemberDTO;
import com.moonBam.dto.AdminRestrictedMemberDTO;
import com.moonBam.dto.MemberDTO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class AdminMemberDAO {

	@Autowired
	SqlSessionTemplate session;

	public List<AdminMemberDTO> SearchMember(HashMap<String, String> map) {
		List<AdminMemberDTO> list = session.selectList("AdminMemberMapper.SearchMember", map);
		return list;
	}

	public List<AdminRestrictedMemberDTO> getRestrictedMemberList() {
		System.out.println("dao 시작함");
		List<AdminRestrictedMemberDTO> list = session.selectList("AdminMemberMapper.getRestrictedMemberList");
//		System.out.println("매퍼 다녀옴");
		System.out.println(list);
		return list;
	}
	
	public int saveInDeletedlist(List<MemberDTO> mlist) {
		
		System.out.println("in AdminMemberDAO");
		System.out.println("dao에서 강퇴 대상자 수신");
		System.out.println("deletelist : ");
		System.out.println(mlist);
		System.out.println("===================");
		
		System.out.println("mapper에 전달");
		int n =0;
		
		for(int i = 0; i < mlist.size(); i++) {
			System.out.println(mlist.get(i));
			session.insert("AdminMemberMapper.saveInDeletedlist", mlist.get(i));
			n += 1;
		}
		System.out.println(n+"개 대상자 삭제된 회원 데이터에 저장");
		System.out.println("==============");
		
		return n;
	}

	public int deleteFromMemberDB(String deletelist) {
		int n = session.delete("AdminMemberMapper.deleteFromMemberDB", deletelist);
		return n;
	}

	public List<AdminDeletedMemberDTO> getDeletedMemberList(HashMap<String, String> map) {
		List<AdminDeletedMemberDTO> list = session.selectList("AdminMemberMapper.getDeletedMemberList", map);
		System.out.println(list);
		return list;
	}



}
