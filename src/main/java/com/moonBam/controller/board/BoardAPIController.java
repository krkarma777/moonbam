package com.moonBam.controller.board;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.board.PostPageDTO;
import com.moonBam.dto.board.PostUpdateRequestDTO;
import com.moonBam.service.PostService;
import com.moonBam.service.member.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class BoardAPIController {

    private final PostService postService;

    private final MemberLoginService memberLoginService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated PostDTO postDTO,
                                    BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "입력 값에 오류가 있습니다.", "errors", errors));
        }

        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요한 서비스입니다."));
        }

        postDTO.setUserId(loginUser.getUserId());
        postDTO.setNickname(loginUser.getNickname());
        Long postID = postService.save(postDTO);
        return ResponseEntity.ok(Map.of("postID", postID)); // 동적으로 ajax success function에서 redirect 시킬 용도
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<?> update(@RequestBody @Validated PostUpdateRequestDTO postUpdateRequestDTO, @PathVariable("postId") Long postId,
                                    BindingResult bindingResult, Principal principal) {

        PostDTO postDTO = postService.findById(postId);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "입력 값에 오류가 있습니다.", "errors", errors));
        }

        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요한 서비스입니다."));
        }

        if (!loginUser.getUserId().equals(postDTO.getUserId())) {
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
    public ResponseEntity<?> delete(@PathVariable("postId") Long postId, Principal principal) {
        PostDTO postDTO = postService.findById(postId);
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요한 서비스입니다."));
        }

        if (postDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "글이 존재하지않습니다."));
        }

        if (!loginUser.getUserId().equals(postDTO.getUserId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "글을 삭제할 권한이 없습니다."));
        }

        postService.delete(postId);

        return ResponseEntity.ok(Map.of("message", "삭제가 완료되었습니다."));
    }
    
    
    @GetMapping("/{postId}")
    public ResponseEntity<?> findOne(@PathVariable("postId") Long postId, Principal principal){
    	PostPageDTO pDTO = postService.selectPagePost(postId);
    	
    	if (postId == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "존재하지 않는 게시글입니다."));
    	}

        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
        boolean isAuthorized = false;
        if (loginUser != null) {
            isAuthorized = loginUser.getUserId().equals(pDTO.getUserId());
        }
    	return ResponseEntity.ok(Map.of("pDTO", pDTO, "isAuthorized", isAuthorized));
    }
}

