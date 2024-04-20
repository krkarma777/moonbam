package com.moonBam.service;

import com.moonBam.dao.ScrapDAO;
import com.moonBam.dto.board.ScrapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ScrapService {
	
	@Autowired
	ScrapDAO dao;

	public void insert(HashMap<String, String> map) {
		dao.insert(map);
	}

	public ScrapDTO checkScrap(HashMap<String, String> map) {
        return dao.checkScrap(map);
	}

	public List<ScrapDTO> findAll(String userId) {
		return dao.findAll(userId);
	}

	public int delete(Long id) {
		return dao.delete(id);
	}

	public ScrapDTO findById(Long scrapId) {
		return dao.findById(scrapId);
	}

	public List<ScrapDTO> findAllByPostId(Long postId) {
		return dao.findAllByPostId(postId);
	}
}
