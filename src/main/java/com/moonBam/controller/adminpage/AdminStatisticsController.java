package com.moonBam.controller.adminpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.moonBam.dto.AdminCounterDTO;
import com.moonBam.service.adminpage.AdminStatisticsService;

@Controller
@EnableScheduling
public class AdminStatisticsController {
	
	@Autowired
	AdminCounter counter;
	
	@Autowired
	AdminStatisticsService asService;
	
	public AdminStatisticsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	//00시 새로운 레코드 insert
	@Scheduled(cron = "0 0 0 * * *")
	public void countStartToday() {
		int n = asService.countStartToday();
		if(n ==0) {
			System.out.println("금일 집계 시작");
		}
	}

	@Scheduled(fixedRate = 5000) //5초마다 DB업데이트
	public void updateCounter() {
		int n = counter.getNum();
		int num = asService.countVisitor(n);
		if(num == 1) {
			counter.setNum(0);
		}
	}
	
	//최근 30일 데이터 가져오기
	public List<AdminCounterDTO> getCount(){
		System.out.println("in AdminStatisticsController");
		List<AdminCounterDTO> list = asService.getCount();
		
		return list;
	}
	
	
}
