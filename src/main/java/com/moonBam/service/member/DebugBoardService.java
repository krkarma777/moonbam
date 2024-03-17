package com.moonBam.service.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.member.DebugBoardDAO;
import com.moonBam.dto.DebugBoardDTO;

@Service
public class DebugBoardService {

	@Autowired
	DebugBoardDAO dao;

	public List<DebugBoardDTO> viewDBoardList() {
		List<DebugBoardDTO> list = dao.viewDBoardList();
		return list;
	}

	public DebugBoardDTO viewDBoardContent(int boardNum) {
		DebugBoardDTO dto = dao.viewDBoardContent(boardNum);
		return dto;
	}

	public int updateDBoardViewCount(int boardNum) {
		int result = dao.updateDBoardViewCount(boardNum);
		return result;
	}

	public int insertPost(DebugBoardDTO dto) {
		int num = dao.insertPost(dto);
		return num;
	}
	
	
}
