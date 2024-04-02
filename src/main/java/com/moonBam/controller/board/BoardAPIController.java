package com.moonBam.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PageDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.board.PostPageDTO;
import com.moonBam.dto.board.PostUpdateRequestDTO;
import com.moonBam.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class BoardAPIController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated PostDTO postDTO,
                                    BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "입력 값에 오류가 있습니다.", "errors", errors));
        }

        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요한 서비스입니다."));
        }

        postDTO.setUserId(loginUser.getUsername());
        postDTO.setNickname(loginUser.getNickname());
        Long postID = postService.save(postDTO);
        return ResponseEntity.ok(Map.of("postID", postID)); // 동적으로 ajax success function에서 redirect 시킬 용도
    }

    @PatchMapping("{postId}")
    public ResponseEntity<?> update(@RequestBody @Validated PostUpdateRequestDTO postUpdateRequestDTO, @PathVariable("postId") Long postId,
                                    BindingResult bindingResult, HttpSession session) {

        PostDTO postDTO = postService.findById(postId);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "입력 값에 오류가 있습니다.", "errors", errors));
        }

        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요한 서비스입니다."));
        }

        if (!loginUser.getUsername().equals(postDTO.getUserId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "글을 수정할 권한이 없습니다."));
        }

        postService.update(postUpdateRequestDTO);

        return ResponseEntity.ok(Map.of("postID", postId));
    }

    private Map<String, String> getErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> delete(@PathVariable("postId") Long postId, HttpSession session) {
        PostDTO postDTO = postService.findById(postId);
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요한 서비스입니다."));
        }

        if (postDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "글이 존재하지않습니다."));
        }

        if (!loginUser.getUsername().equals(postDTO.getUserId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "글을 삭제할 권한이 없습니다."));
        }

        postService.delete(postId);

        return ResponseEntity.ok(Map.of("message", "삭제가 완료되었습니다."));
    }
    
    
    @GetMapping("/{postId}")
    public ResponseEntity<?> findOne(@PathVariable("postId") Long postId, HttpSession session){
    	PostPageDTO pDTO = postService.selectPagePost(postId);
    	
    	if (postId == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "존재하지 않는 게시글입니다."));
    	}
    	
    	MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
        boolean isAuthorized = loginUser.getUsername().equals(pDTO.getUserId());
    	
    	return ResponseEntity.ok(Map.of("pDTO", pDTO, "isAuthorized", isAuthorized));
    }
    
    //boardId에 따라 postList 가져오기
    @PatchMapping
    public ResponseEntity<?> findAll(
    		@PathVariable("postBoard") String postBoard,
    		@RequestParam(value = "curPage", defaultValue = "1") int curPage,
            @RequestParam(value = "postCategoryId", required = false) String postCategoryId,
            @RequestParam(value = "searchParam", required = false) String searchParam,
            @RequestParam(value = "sortParam", required = false) String sortParam, Model model
    		) {
    	
        int perPage = 20;
        int offset = (curPage - 1) * perPage;

        HashMap<String, Object> map = new HashMap<>();
        map.put("postBoard", postBoard);
        map.put("offset", offset);
        map.put("perPage", perPage);
        map.put("curPage", curPage);
        map.put("postCategoryId", postCategoryId);
        map.put("searchParam", searchParam);
        map.put("sortParam", sortParam);

        // 검색 조건 추가
        // searchBoard(paramMap, map);

        // 정렬 추가
        // sortIndex(paramMap, map);

        // 페이지 정보 가져오기
        PageDTO<PostPageDTO> pageDTO = postService.getPostsByPage(map);

        List<PostPageDTO> hotList = postService.selectAll((HashMap<String, String>) Map.of("board", postBoard, "postCount", "5"));

        List<PostPageDTO> popularListAll = postService.popularPostTwoDays((HashMap<String, String>) Map.of("postCount", "10"));
        
		final String category = postBoard.contains("movie") ? "movie" : 
            postBoard.contains("tv") ? "tv" : 
            postBoard.contains("book") ? "book" : "";
		
		List<PostPageDTO> popularListCategory = postService.popularPostTwoDays(
				(HashMap<String, String>) Map.of("board", category, "board2", category+"Info", "board3", category+"Meet", "postCount", "10"));
    	    	
    	return ResponseEntity.ok(Map.of("pageDTO", pageDTO, "postBoard", postBoard, "hotList", hotList, 
    			"popularListAll", popularListAll, "popularListCategory",popularListCategory));
	}
}

