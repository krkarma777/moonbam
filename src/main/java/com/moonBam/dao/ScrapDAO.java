package com.moonBam.dao;

import com.moonBam.dto.board.ScrapDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

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

	public List<ScrapDTO> findAll(String userId) {
		return session.selectList("ScrapMapper.findAll",userId);
	}

	public int delete(Long id) {
		return session.delete("ScrapMapper.delete",id);
	}

	public ScrapDTO findById(Long scrapId) {
		return session.selectOne("ScrapMapper.findById",scrapId);
	}

	public List<ScrapDTO> findAllByPostId(Long postId) {
		return session.selectList("ScrapMapper.findAllByPostId",postId);
	}
}
