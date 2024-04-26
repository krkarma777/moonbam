package com.moonBam.service.adminpage.announcement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dao.AnnouncementDAO;
import com.moonBam.dto.AnnouncementDTO;

@Repository
public class AnnouncementService {

	@Autowired
	public AnnouncementDAO dao;
	

	// pop

	// get popup num list
	public List<Integer> popupNnumList(HashMap<String, String> hashMap) {
		System.out.println("AnnouncementService.popupList()");
		List<Integer>list = dao.popupNumList(hashMap);
		return list;
	}

	// pop	

	/*
	 * public List<AnnouncementDTO> popupList(String popup) {
	 * System.out.println("AnnouncementService.popupList()");
	 * List<AnnouncementDTO>list = dao.popupList(popup); return list; }
	 */

	// pop
	// last Page Number
	public int lastPage(String word) {
		System.out.println("AnnouncementService.lastPage()");
		int done = dao.lastPage(word);
		return done;
	}
	
	// announcement
	// list
	public List<AnnouncementDTO> listAnnouncement(Map<String, Object> map) {
		System.out.println("AnnouncementService.listAnnouncement()");
		List<AnnouncementDTO> list = new ArrayList<AnnouncementDTO>();
		list = dao.listAnnouncement(map);
		return list;
	}
	
	public AnnouncementDTO oneAnnouncement(String annoNum) {
		System.out.println("AnnouncementService.oneAnnouncement()");
		AnnouncementDTO dto = dao.oneAnnouncement(annoNum);
		return dto;
	}
	
	public int insertAnnouncement(AnnouncementDTO dto) {
		System.out.println("AnnouncementService.insertAnnouncement()");
		int done = dao.insertAnnouncement(dto);
		return done;
	}
	public void deleteAnnouncement(String num) {
		System.out.println("AnnouncementService.deleteAnnouncement()");
		 dao.deleteAnnouncement(num);
	}
	public void updateAnnouncement(AnnouncementDTO dto) {
		System.out.println("AnnouncementService.updateAnnouncement()");
		 dao.updateAnnouncement(dto);
	}
}
	
