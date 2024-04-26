package com.moonBam.service.adminpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.AdminStatisticsDAO;
import com.moonBam.dto.AdminCounterDTO;

@Service
public class AdminStatisticsService {
	
	@Autowired
	AdminStatisticsDAO asDAO;
	
	public int countVisitor(int num) {
		int n = 0;
		n = asDAO.countVisitor(num);
		return n;
	}

	public int countStartToday() {
		int n = asDAO.countStartToday();
		return n;
	}
	
	public List<AdminCounterDTO> getCount() {
		
		System.out.println("in AdminStatisticsService.getCount");
		List<AdminCounterDTO> list = asDAO.getCount();
	
		return list;
	}
}
