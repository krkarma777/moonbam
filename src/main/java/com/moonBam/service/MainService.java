package com.moonBam.service;

import com.moonBam.dao.MainDAO;
import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.MoviePageDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MainService {
	@Autowired
	MainDAO dao;
	
	public List<ContentDTO> selectTop() {
		List<ContentDTO> movieTopList = dao.selectTop();
		return movieTopList;
	}

	public List<ContentDTO> selectGenreTop(String genre) {
		List<ContentDTO> genreMovieTopList = dao.selectGenreTop(genre);
		return genreMovieTopList;
	}

	public MoviePageDTO searchMovieList(String curPage, String searchCategory, String searchValue) {
		HashMap<String, String> map = new HashMap<>();
		map.put("searchCategory", searchCategory);
		map.put("searchValue", searchValue);
		
		MoviePageDTO mpDTO = dao.searchMovieList(map, curPage);
		
		return mpDTO;
	}
}
