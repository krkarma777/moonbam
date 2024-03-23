package com.moonBam.controller.board;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "입력 값에 오류가 있습니다.", "errors", errors));
        }

        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요한 서비스입니다."));
        }

        postDTO.setUserId(loginUser.getUserId());
        Long postID = postService.save(postDTO);
        return ResponseEntity.ok(Map.of("postID", postID)); // 동적으로 ajax success function에서 redirect 시킬 용도
    }
}
