package com.moonbam.dao;

import com.moonbam.dto.board.ScrapDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ScrapDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public int insert(HashMap<String, String> map) {
		int n = session.insert("ScrapMapper.insert",map);
		return n;
	}

	public ScrapDTO checkScrap(HashMap<String, String> map) {
		ScrapDTO scrapDTO = session.selectOne("ScrapMapper.checkScrap",map);
		return scrapDTO;
	}

}
