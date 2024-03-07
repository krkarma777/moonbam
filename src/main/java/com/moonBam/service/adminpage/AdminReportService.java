package com.moonBam.service.adminpage;

import com.moonBam.dao.AdminReportDAO;
import com.moonBam.dto.AdminRprtdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminReportService {
	
	@Autowired
	AdminReportDAO dao;

	public List<AdminRprtdDTO> SearchReport(HashMap<String, String> map) {
		System.out.println("in AdminReportService.SearchReport");
		
		List<AdminRprtdDTO>list = null;
		list = dao.SearchReport(map);
		
		return list;
	}

	public List<AdminRprtdDTO> ReportedMemList(HashMap<String, String> map) {
		List<AdminRprtdDTO>list = null;
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
