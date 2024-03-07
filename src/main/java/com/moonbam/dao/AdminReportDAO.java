package com.moonbam.dao;



import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AdminRprtdDTO;

@Repository
public class AdminReportDAO {

	@Autowired
	SqlSessionTemplate session;
	

	public List<AdminRprtdDTO> SearchReport(HashMap<String, String> map) {
		System.out.println("in AdminReportDAO.SearchReport map:");
		System.out.println(map);
		List<AdminRprtdDTO> list = session.selectList("AdminReportMapper.SearchPost", map);
		System.out.println(list);
		return list;
	}

	public List<AdminRprtdDTO> ReportedMemList(HashMap<String, String> map) {
		System.out.println("in dao map:" + map);
		List<AdminRprtdDTO>list = session.selectList("AdminReportMapper.ReportedMemberList", map);
		
		System.out.println("in dao :" + list);
		return list;
	}

	public int delReportedPost(List<String> list) {
		
		int n = session.delete("AdminReportMapper.delReportedPost", list);
		return n;
	}

	
}
