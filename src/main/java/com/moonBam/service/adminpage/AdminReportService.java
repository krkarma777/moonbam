package com.moonBam.service.adminpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public int delReportedPost(List<String> list) {
		System.out.println("in admin.ReportService.delReportedPost");
		
		System.out.println("게시물DB에서 해당되는 신고번호 리스트 수신 확인 후 리스트로 저장 ========");
//		Boolean isPostListRetrieved = false;
		System.out.println("list");
		System.out.println(list);
		
		System.out.println("리스트를 dao로 전달 후 해당되는 리스트 받아오기");
		List<PostDTO> rList = new ArrayList<PostDTO>();
		rList = rdao.getPostList(list);
		System.out.println(rList);
		
		System.out.println("3. 해당 List를 삭제된 게시물 DB로 저장");
		int n1 = ddao.insertDeletedPostsfromReport(rList);
		
		System.out.println("4. 신고처리됨 update");
		int n2 = rdao.reportDone(list);
		
		
		System.out.println("5. PostDB에서 삭제됨");
		n2 = rdao.delReportedPost(list);
		System.out.println(n2 + "개 레코드 삭제됨");
		
		return n2;
	}

	public List<AdminReportDTO> getUndone() {
		List<AdminReportDTO> list = rdao.getUndone();
		
		return list;
	}

}
