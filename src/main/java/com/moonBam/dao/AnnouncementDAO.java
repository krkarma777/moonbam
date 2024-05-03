package com.moonBam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.moonBam.dto.AnnouncementDTO;

@RestController
public class AnnouncementDAO {
	
	@Autowired
	public SqlSessionTemplate session;
	
	public List<Integer> popupNumList(HashMap<String, String> hashMap) {
		System.out.println("AnnouncementDAO.popupNumList()");
		List<Integer> list = session.selectList("popupNumList", hashMap);
		return list;
	}

	public List<AnnouncementDTO> popupList(String popup) {
		System.out.println("AnnouncementDAO.popupList()");
		List<AnnouncementDTO> list = session.selectList("popupList", popup);
		return list;
	}

	public int lastPage(String word) {
		System.out.println("AnnouncementDAO.lastPage()");
		int done = session.selectOne("lastPage", word);
		return done;
	}

	public List<AnnouncementDTO> listAnnouncement(Map<String, Object> map) {
		System.out.println("AnnouncementDAO.listAnnouncement()");
		List<AnnouncementDTO> list = session.selectList("listAnnouncement", map);
		return list;
	}

	public AnnouncementDTO oneAnnouncement(String annoNum) {
		System.out.println("AnnouncementDAO.oneAnnouncement()");
		AnnouncementDTO dto = session.selectOne("oneAnnouncement", annoNum);
		return dto;
	}
	public int insertAnnouncement(AnnouncementDTO dto) {
		System.out.println("AnnouncementDAO.insertAnnouncement()");
		int done = session.insert("insertAnnouncement", dto);
		return done;
	}
	
	
	
	
	
	public AnnouncementDAO() {
		super();
		System.out.println("AnnouncementDAO.AnnouncementDAO()");
	}

	public SqlSessionTemplate getSession() {
		return session;
	}

	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}

	public int deleteAnnouncement(String	 num) {
		System.out.println("AnnouncementDAO.deleteAnnouncement()");
		int done = session.delete("deleteAnnouncement", num);
		return done;
	}

	public void updateAnnouncement(AnnouncementDTO dto) {
		System.out.println("AnnouncementDAO.updateAnnouncement()");
		int done = session.update("updateAnnouncement", dto);
		
	}
	
	
	
	
}
