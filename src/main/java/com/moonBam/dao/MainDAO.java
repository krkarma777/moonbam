package com.moonBam.dao;


import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.MoviePageDTO;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MainDAO {
	@Autowired
	SqlSessionTemplate session;
	
	public List<ContentDTO> selectTop() {
		List<ContentDTO> movieTopList = session.selectList("ContentMapper.selectTop");
		return movieTopList;
	}

	public List<ContentDTO> selectGenreTop(String genre) {
		List<ContentDTO> genreMovieTopList = session.selectList("selectGenreTop", genre);
		return genreMovieTopList;
	}

	public MoviePageDTO searchMovieList(HashMap<String, String> map, String curPage) {
		MoviePageDTO mpDTO = new MoviePageDTO();
		int perPage = mpDTO.getPerPage();
		int offset = (Integer.parseInt(curPage)-1)*perPage;
		
		List<ContentDTO> movieList = session.selectList("searchMovieList", map, new RowBounds(offset, perPage));
		
		mpDTO.setCurPage(Integer.parseInt(curPage));
		mpDTO.setList(movieList);
		mpDTO.setTotalCount(totalCount(session, map));
		
		return mpDTO;
	}

	private int totalCount(SqlSessionTemplate session, HashMap<String, String> map) {
		return session.selectOne("movieTotalCount", map);
	}
}
