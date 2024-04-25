package com.moonBam.dao;



import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.AdminReportDTO;
import com.moonBam.dto.board.PostDTO;



@Repository
public class AdminReportDAO {

	@Autowired
	SqlSessionTemplate session;
	

	public List<AdminReportDTO> SearchReport(HashMap<String, String> map) {
		System.out.println("in AdminReportDAO.SearchReport map:");
		System.out.println(map);
		List<AdminReportDTO> list = session.selectList("AdminReportMapper.SearchPost", map);
		System.out.println(list);
		return list;
	}

	public List<AdminReportDTO> ReportedMemList(HashMap<String, String> map) {
		System.out.println("in dao map:" + map);
		List<AdminReportDTO>list = session.selectList("AdminReportMapper.ReportedMemberList", map);
		
		System.out.println("in dao :" + list);
		return list;
	}

	public int delReportedPost(List<String> list) {
		
		int n = session.delete("AdminReportMapper.delReportedPost", list);
		return n;
	}

	public List<AdminReportDTO> getReportList(List<String> list) {
		List<AdminReportDTO> rList = session.selectList("AdminReportMapper.getReportList", list);
		return rList;
	}

	public List<PostDTO> getPostList(List<String> list) {
		System.out.println("in AdminReportDAO.getPostList");
		System.out.println("dao에서 리스트 수신 확인 후 매퍼 전달");
		System.out.println(list);
		List<PostDTO>rlist = session.selectList("AdminReportMapper.getPostList", list);
		
		System.out.println("매퍼에서 출력된 리스트 확인 후 반환============");
		System.out.println(rlist);
		System.out.println("================================");
		
		return rlist;
	}

	public int reportDone(List<String> list) {
		System.out.println("reportDone==========================");
		System.out.println("신고글 테이블에서 신고처리 yes로 바꿀 글 아이디 목록");
		System.out.println(list);
		System.out.println("사이즈 : " + list.size());
		System.out.println("====================================");
		
		int count = 1;
//		for (String reportId : list) {
			session.update("AdminReportMapper.reportDone", list);
//			count+=1;
//		}
		
		System.out.println("update처리된 행의 갯수 ====================");
		System.out.println(count);
		System.out.println("같으면 정상처리됨");
		System.out.println("=====================================");
		
		return count;
	}

	public List<AdminReportDTO> getUndone() {
		List<AdminReportDTO> list = session.selectList("AdminReportMapper.getUndone");
		System.out.println("in AdminReportDAO.getUndone : list");
		System.out.println(list);
		return list;
	}

	

	
}
