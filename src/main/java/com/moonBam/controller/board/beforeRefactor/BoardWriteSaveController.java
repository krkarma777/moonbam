package com.moonBam.controller.board.beforeRefactor;


import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostSaveDTO;
import com.moonBam.service.PostService;
import com.moonBam.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class BoardWriteSaveController {

	@Autowired
	MemberLoginService memberLoginService;

	@Autowired
	PostService service;
	
	//임시글 저장
	@PostMapping("/save")
	public String insertPostSave(PostSaveDTO dto, Principal principal) {
		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
		String userId = loginUser.getUserId();
		dto.setUserId(userId);

		// XSS 방지를 위해 입력값에서 스크립트 태그를 제거하는 로직 추가
		String sanitizedPostText = sanitizeHtml(dto.getPostSaveText());
		dto.setPostSaveText(sanitizedPostText);

		System.out.println("임시저장 dto => "+dto);
		service.insertPostSave(dto);
		
		return "redirect:post";
	}//
	
	//임시글 불러오기
	@PostMapping("/saveSelect")
	public PostSaveDTO selectPostSave(String postSaveId) {
    	System.out.println("임시글 불러오기 id = "+postSaveId);
        return service.selectPostSave(postSaveId);
	}
	
	//임시글 삭제
	@PostMapping("/saveDelete")
	public ResponseEntity<String> deletePostSave(Long postSaveId) {
		System.out.println("삭제 => "+postSaveId);
		service.deletePostSave(postSaveId);
		
		return ResponseEntity.ok("성공했습니다.");
	}
	
	@PostMapping("/saveList")
	public ResponseEntity<?> saveList(Principal principal, Model m) {
		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
        String userId = loginUser.getUserId();
		List<PostSaveDTO> postSaveList = service.listPostSave(userId);
		System.out.println("임시저장 목록 => "+postSaveList);
		return ResponseEntity.ok(postSaveList);
	}

	private String sanitizeHtml(String input) {
		return input.replaceAll("(?i)<script.*?>.*?</script>", "") // 스크립트 태그 제거
				.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // "javascript:" URI 사용 제거
				.replaceAll("(?i)<.*?\\bon.*?>.*?</.*?>", ""); // 이벤트 핸들러 제거 (예: onclick)
	}
}//end class
