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

	public List<DebugBoardDTO> viewDBoardList(String orderBy) {
		List<DebugBoardDTO> list = dao.viewDBoardList(orderBy);
		return list;
	}

	public DebugBoardDTO viewDBoardContent(int boardNum) {
		DebugBoardDTO dto = dao.viewDBoardContent(boardNum);
		return dto;
	}

	public void updateDBoardViewCount(int boardNum) {
		dao.updateDBoardViewCount(boardNum);
	}

	public void insertPost(DebugBoardDTO dto) {
		dao.insertPost(dto);
	}

	public void updateDBoard(DebugBoardDTO dto) {
		dao.updateDBoard(dto);
		
	}

	public void deleteDBoard(int boardNum) {
		dao.deleteDBoard(boardNum);
		
	}

	public void updateDBoardRecommendNum(HashMap<String, Integer> map) {
		dao.updateDBoardRecommendNum(map);
		
	}
	
	
}
