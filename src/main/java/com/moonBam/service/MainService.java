package com.moonBam.service;

import com.moonBam.dao.MainDAO;
import com.moonBam.dto.ContentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
	@Autowired
	MainDAO dao;
	
	public List<ContentDTO> selectTop() {
		List<ContentDTO> movieTopList = dao.selectTop();
		return movieTopList;
	}
}
