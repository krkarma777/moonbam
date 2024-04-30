package com.moonBam.service.adminpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public int kickUser(List<String> deletelist) {
		System.out.println("in AdminReportService.kickUser");
		System.out.println("서비스 레이어에서 강퇴 대상자 명단 수신");
		System.out.println(deletelist);
		System.out.println("==============================");
		
		System.out.println("강퇴 대상자 정보 확인");
		System.out.println(deletelist);
		System.out.println("==================");
		
		System.out.println("강퇴 대상자 계정 비활성화");
		System.out.println("강퇴 대상자를 dao로 전달");
		int n1 = 0;
		for(String userid : deletelist) {
			mdao.suspendUser(userid);
			n1 +=1;
		}
		System.out.println("대상자를 이용제한된 사용자 테이블에 insert");
		System.out.println("dao에 전달");
		int n2 = 0;

		for(String userid:deletelist) {
			mdao.insertDeletedUserInRestrictedDB(userid);
			n2+=1;
		}
		
		System.out.println(n2 + "개의 행 이용제한 테이블에 insert");
		
		return n1;
	}

	public List<AdminDeletedMemberDTO> getDeletedMemberList(HashMap<String, String> map) {
		List<AdminDeletedMemberDTO> list = mdao.getDeletedMemberList(map);
		
		return list;
	}

	public int cleanDeletedMember(List<String> list) {
		System.out.println("in adminmemberservice.cleandeletedmember");
		int n = 0;
		for(String userid : list) {
			n = mdao.cleanDeletedMember(userid);
		}	
		return n;
	}

	@Transactional
	public int releaseUser(String userId) {
		System.out.println("in adminmemberservice.releaseUser");
		int n = mdao.releaseUser(userId);
		System.out.println(n + "명의 사용자 이용제한 해제");
		
		int n2 = mdao.deleteFromRestrictedMember(userId);
		System.out.println(n2 + "명의 사용자 이용제한 목록에서 삭제");
		return n;
	}

	@Transactional
	public int suspendUser(List<String> suspendList) {
		System.out.println("in adminmemberservice.suspenduser");
		int n = 0;
		for(String user : suspendList) {
			mdao.insertInRestrictedUser(user);
			mdao.suspendUser(user);
			n +=1;
		}
		return n;
	}

	public List<String> getDeletelist() {
		List<String> list = mdao.getDeleteList();
		return list;
	}

	public int cleanRestrictedMember() {
		int n = mdao.cleanRestrictedMember();
		return n;
	}
	
}
