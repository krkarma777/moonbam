package com.moonBam.controller.board;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.board.PostUpdateRequestDTO;
import com.moonBam.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.stream.Collectors;

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

        postDTO.setUserId(loginUser.getUserId());
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
    public ResponseEntity<?> delete(@PathVariable("postId") Long postId, HttpSession session) {
        PostDTO postDTO = postService.findById(postId);
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

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
}

