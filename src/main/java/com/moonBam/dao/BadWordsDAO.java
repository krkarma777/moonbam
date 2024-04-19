package com.moonBam.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BadWordsDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	public int insertBadWords(String badWords) {
		
		int n = 0;
		
		System.out.println("in BadWordsDAO");
		System.out.println("dao레이어에서 금칙어 수신");
		System.out.println(badWords);
		
		System.out.println("매퍼에 저장할 데이터 전달");
		n = session.insert("BadWordsMapper.save", badWords);
		System.out.println(n + "개의 금칙어 정상 저장");
		
		return n;
	}

	public int deleteBadWords(String badWords) {

		int n = 0;
		
		System.out.println("in BadWordsDAO");
		System.out.println("dao레이어에서 삭제할 금칙어 수신");
		System.out.println(badWords);
		
		System.out.println("매퍼에 삭제할 데이터 전달");
		n = session.delete("BadWordsMapper.delete", badWords);
		System.out.println(n + "개의 금칙어 정상 삭제");
		
		return n;
	}

}
