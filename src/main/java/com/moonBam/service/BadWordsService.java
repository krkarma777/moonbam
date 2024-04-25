package com.moonBam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.BadWordsDAO;

@Service
public class BadWordsService {

	@Autowired
	BadWordsDAO bwDAO;
	
	public int insertBadWords(String badWords) {
		
		int n = 0;
		
		System.out.println("in BadWordsService");
		System.out.println("DB에 저장할 금칙어 서비스 레이어에서 수신");
		System.out.println(badWords);
		
		System.out.println("금칙어 dao레이어로 전달");
		n = bwDAO.insertBadWords(badWords);
		
		return n;
	}

	public int deleteBadWords(String badWords) {

		int n = 0;
		
		System.out.println("in BadWordsService");
		System.out.println("DB에 삭제할 금칙어 서비스 레이어에서 수신");
		System.out.println(badWords);
		
		System.out.println("삭제할 금칙어 dao레이어로 전달");
		n = bwDAO.deleteBadWords(badWords);
		
		return n;
		
	}

}
