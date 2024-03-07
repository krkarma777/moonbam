package com.moonBam.dao;


import com.moonBam.dto.ContentDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainDAO {
	@Autowired
	SqlSessionTemplate session;
	
	public List<ContentDTO> selectTop() {
		List<ContentDTO> movieTopList = session.selectList("ReviewMapper.selectTop");
		return movieTopList;
	}
}
