package com.moonBam.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.AdminReportDTO;
import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.ReportDTO;

@Repository
public class CommunityChatmoreDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public List<String> ChatMemberIdByChatNum(int chatNum) {
		// TODO Auto-generated method stub
		
		return session.selectList("Chatmore.ChatMemberIdByChatNum", chatNum);
	}

	public MemberDTO memberByChatMemberId(String userId) {
		// TODO Auto-generated method stub
		return session.selectOne("Chatmore.memberByChatMemberId",userId);
	}

	public String ChatLeaderIdByChatNum(int chatNum) {
		// TODO Auto-generated method stub
		return session.selectOne("Chatmore.ChatLeaderIdByChatNum",chatNum);
	}

	public void ChatmoreReportPostInsert(AdminReportDTO adminReportDTO) {
		// TODO Auto-generated method stub
		session.insert("Chatmore.ChatmoreReportPostInsert",adminReportDTO);
	}
	
	

}
