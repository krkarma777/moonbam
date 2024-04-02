package com.moonBam.controller.board.beforeRefactor;


import com.moonBam.dto.board.ScrapDTO;
import com.moonBam.service.ScrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BoardScrapController {

	@Autowired
	ScrapService scrapService;
	
	
	@PostMapping("/scrap")
	public ResponseEntity<?> scrap(@RequestParam("postId")String postId,
								   @RequestParam("userId")String userId) {
		HashMap<String,String> map = new HashMap();
		map.put("postId", postId);
		map.put("userId", userId);
		
		ScrapDTO scrapDTO = scrapService.checkScrap(map);
		 
		 try {
		    	if(scrapDTO == null) {
		    		scrapService.insert(map);
		    		 // 스크랩 성공 응답
			        return ResponseEntity.status(200).body(Map.of("success",true,"message","스크랩 성공!"));
		    	}else {
		    		return ResponseEntity.ok().body(Map.of("success",false,"message","이미 스크랩되었습니다."));
		    	}
		    } catch (Exception e) {
		       return ResponseEntity.badRequest().body(Map.of("success",false,"message","스크랩 실패."));
		    }
		    
		 

		
	}
	
	


}
