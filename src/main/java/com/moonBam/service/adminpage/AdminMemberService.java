package com.moonBam.service.adminpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.AdminMemberDAO;
import com.moonBam.dao.member.MemberDAO;
import com.moonBam.dto.AdminDeletedMemberDTO;
import com.moonBam.dto.AdminMemberDTO;
import com.moonBam.dto.AdminRestrictedMemberDTO;
import com.moonBam.dto.MemberDTO;

@Service
public class AdminMemberService {
	
	@Autowired
	AdminMemberDAO mdao;
	
	@Autowired
	MemberDAO memberDAO;

	
	
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
	
	//신고된 회원 강퇴
	public int kickUser(List<String> deletelist) {
		System.out.println("in AdminReportService.kickUser");
		System.out.println("서비스 레이어에서 강퇴 대상자 명단 수신");
		System.out.println(deletelist);
		System.out.println("==============================");
		
		System.out.println("강퇴 대상자 정보 요청하여 리스트로 만들기");
		
		List<MemberDTO> mlist = new ArrayList<>();
		for(String target : deletelist) {
			MemberDTO mdto = memberDAO.findByUserId(target);
			mlist.add(mdto);
		}
		
		System.out.println("강퇴 대상자 정보 확인");
		for(MemberDTO member : mlist) {
			System.out.println(member);
		}
		System.out.println("==================");
		
		System.out.println("강퇴 대상자를 삭제된 회원 데이터로 insert");
		System.out.println("강퇴 대상자를 dao로 전달");
		int n1 = 0;
		n1 = mdao.saveInDeletedlist(deletelist);
		System.out.println(n1 + "명 삭제된 데이터에 insert");

		System.out.println("대상자를 기존 memberDB에서 삭제");
		System.out.println("dao에 대상자 전달");
		int n2 = 0;

		for(String target : deletelist) {
			int result = mdao.deleteFromMemberDB(target);
			n2+=result;
		}
		
		System.out.println(n2 + "개의 행 기존 회원DB에서 삭제");
		
		return n1;
	}

	public List<AdminDeletedMemberDTO> getDeletedMemberList(HashMap<String, String> map) {
		List<AdminDeletedMemberDTO> list = mdao.getDeletedMemberList(map);
		
		return list;
	}
	
}
