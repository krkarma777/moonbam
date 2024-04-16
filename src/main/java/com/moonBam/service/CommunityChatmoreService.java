package com.moonBam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.CommunityChatmoreDAO;
import com.moonBam.dto.AdminReportDTO;
import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.ReportDTO;

@Service
public class CommunityChatmoreService {
	
	////  
	
	@Autowired
	CommunityChatmoreDAO chatMoreDao;
	
	public List<String> ChatMemberIdByChatNum(int chatNum){
		return chatMoreDao.ChatMemberIdByChatNum(chatNum);
	}

	public MemberDTO memberByChatMemberId(String userId) {
		// TODO Auto-generated method stub
		return chatMoreDao.memberByChatMemberId(userId);
	}

	public String ChatLeaderIdByChatNum(int chatNum) {
		// TODO Auto-generated method stub
		return chatMoreDao.ChatLeaderIdByChatNum(chatNum);
	}

	public void ChatmoreReportPostInsert(AdminReportDTO adminReportDTO) {
		// TODO Auto-generated method stub
		chatMoreDao.ChatmoreReportPostInsert(adminReportDTO);
	}
	
	
	

}
