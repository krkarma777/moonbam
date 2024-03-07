package com.moonBam.service;

import com.moonBam.dao.ScrapDAO;
import com.moonBam.dto.board.ScrapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ScrapService {
	
	@Autowired
	ScrapDAO dao;


	public void insert(HashMap<String, String> map) {
		dao.insert(map);
	}

	public ScrapDTO checkScrap(HashMap<String, String> map) {
		ScrapDTO scrapDTO = dao.checkScrap(map);
		return scrapDTO;
		
	}



	

}
