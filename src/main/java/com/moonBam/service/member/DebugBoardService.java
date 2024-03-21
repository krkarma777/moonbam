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

	public void increaseDBoardRecommendNum(int boardNum) {
		dao.increaseDBoardRecommendNum(boardNum);
	}
	
	public void decreaseDBoardRecommendNum(int boardNum) {
		dao.decreaseDBoardRecommendNum(boardNum);
	}

	public DebugBoardDTO prevPost(int boardNum) {
		DebugBoardDTO dto = dao.prevPost(boardNum);
		return dto;
	}

	public DebugBoardDTO nextPost(int boardNum) {
		DebugBoardDTO dto = dao.nextPost(boardNum);
		return dto;
	}

	public List<DebugBoardDTO> searchList(HashMap<String, String> map) {
		List<DebugBoardDTO> list = dao.searchList(map);
		return list;
	}


}
