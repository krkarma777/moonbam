package com.moonBam.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.AdminCounterDTO;

@Repository
public class AdminStatisticsDAO {

	@Autowired
	SqlSessionTemplate session;
	
	//통계위한 인원 집계
	public int countVisitor(int num) {
		int n = 0;
		System.out.println("in StstisticsDAO.countVisitor");
		System.out.println("집계 인원 : " + num);
		
		n = session.update("AdminStatisticsMapper.countVisitor", num);
		//System.out.println(n);
		
		if (n == 1) {
			System.out.println("정상적으로 집계됨");
		}else {
			System.out.println("비정상처리됨");
		}
		
		return n;
	}

	//금일 집계 시작
	public int countStartToday() {
		int n = session.insert("AdminStatisticsMapper.startToday");
		
		return n;
	}
	
	//접속자통계 가져오기
	
	public List<AdminCounterDTO> getCount(){
		List<AdminCounterDTO> list = new ArrayList<>();
		
		System.out.println("in AdminStatisticsDAO.getCount");
		list = session.selectList("AdminStatisticsMapper.getCount");
		System.out.println(list);
		
		return list;
	}
}
