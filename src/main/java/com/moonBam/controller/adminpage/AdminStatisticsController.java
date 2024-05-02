package com.moonBam.controller.adminpage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.AdminCounterDTO;
import com.moonBam.dto.AdminReportDTO;
import com.moonBam.service.adminpage.AdminReportService;
import com.moonBam.service.adminpage.AdminStatisticsService;

@Controller
public class AdminStatisticsController {
	
	@Autowired
	AdminCounter counter;
	
	@Autowired
	AdminStatisticsService asService;
	
	@Autowired
	AdminReportService rService;
	
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

	//@Scheduled(fixedRate = 5000) //5초마다 DB업데이트
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
	
	public AdminReportDTO getUndone(){
		List<AdminReportDTO> list2 = rService.getUndone();
		System.out.println("in AdminStatisticsController");
		System.out.println(list2);
		AdminReportDTO rDTO = list2.get(0);
		
		return rDTO;
	}
	
	
}
