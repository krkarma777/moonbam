package com.moonBam.service.adminpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.AdminDeletedPostDAO;
import com.moonBam.dao.AdminReportDAO;
import com.moonBam.dto.AdminReportDTO;
import com.moonBam.dto.board.PostDTO;

@Service
public class AdminReportService {
	
	@Autowired
	AdminReportDAO rdao;
	
	@Autowired
	AdminDeletedPostDAO ddao;

	public List<AdminReportDTO> SearchReport(HashMap<String, String> map) {
		System.out.println("in AdminReportService.SearchReport");
		
		List<AdminReportDTO>list = null;
		list = rdao.SearchReport(map);
		
		return list;
	}

	public List<AdminReportDTO> ReportedMemList(HashMap<String, String> map) {
		
		List<AdminReportDTO>list = null;
		list = rdao.ReportedMemList(map);
		
		return list;
	}

	public int delReportedPost(List<String> list) {
		System.out.println("in admin.ReportService.delReportedPost");
		
		System.out.println("2. 게시물DB에서 해당되는 신고번호의 게시물을 List로 저장");
//		Boolean isPostListRetrieved = false;
		
		List<PostDTO> rList = new ArrayList<PostDTO>();
		rList = rdao.getPostList(list);
		System.out.println(rList);
		
		System.out.println("3. 해당 List를 삭제된 게시물 DB로 저장");
		int n1 = ddao.insertDeletedPostsfromReport(rList);
		System.out.println(n1 + "개의 레코드 정상저장");
		
		//저장이 정상적으로 이루어졌으면 게시물DB에서 해당되는 게시물 삭제
		int n2=0;
		
		n2 = rdao.delReportedPost(list);
		return n2;
	}

}
