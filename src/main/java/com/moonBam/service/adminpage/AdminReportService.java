package com.moonBam.service.adminpage;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.AdminReportDAO;
import com.moonBam.dto.AdminReportDTO;

@Service
public class AdminReportService {
	
	@Autowired
	AdminReportDAO dao;

	public List<AdminReportDTO> SearchReport(HashMap<String, String> map) {
		System.out.println("in AdminReportService.SearchReport");
		
		List<AdminReportDTO>list = null;
		list = dao.SearchReport(map);
		
		return list;
	}

	public List<AdminReportDTO> ReportedMemList(HashMap<String, String> map) {
		List<AdminReportDTO>list = null;
		list = dao.ReportedMemList(map);
		return list;
	}

	public int delReportedPost(List<String> list) {
		int n = 0;
		//TODO transaction
		n = dao.delReportedPost(list);
		return n;
	}

}
