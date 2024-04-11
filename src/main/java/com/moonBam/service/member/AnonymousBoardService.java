package com.moonBam.service.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.member.AnonymousBoardDAO;
import com.moonBam.dto.AnonymousBoardDTO;

@Service
public class AnonymousBoardService {

	@Autowired
	AnonymousBoardDAO dao;

	public List<AnonymousBoardDTO> viewDBoardList(String orderBy) {
		List<AnonymousBoardDTO> list = dao.viewDBoardList(orderBy);
		return list;
	}

	public AnonymousBoardDTO viewDBoardContent(int boardNum) {
		AnonymousBoardDTO dto = dao.viewDBoardContent(boardNum);
		return dto;
	}

	public void updateDBoardViewCount(int boardNum) {
		dao.updateDBoardViewCount(boardNum);
	}

	public void insertPost(AnonymousBoardDTO dto) {
		dao.insertPost(dto);
	}

	public void updateDBoard(AnonymousBoardDTO dto) {
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

	public AnonymousBoardDTO prevPost(int boardNum) {
		AnonymousBoardDTO dto = dao.prevPost(boardNum);
		return dto;
	}

	public AnonymousBoardDTO nextPost(int boardNum) {
		AnonymousBoardDTO dto = dao.nextPost(boardNum);
		return dto;
	}

	public List<AnonymousBoardDTO> searchList(HashMap<String, String> map) {
		List<AnonymousBoardDTO> list = dao.searchList(map);
		return list;
	}


}
