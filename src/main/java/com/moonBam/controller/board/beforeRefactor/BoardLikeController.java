package com.moonBam.controller.board.beforeRefactor;


import com.moonBam.service.PostService;
import com.moonBam.service.member.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardLikeController {

    private final MemberLoginService memberLoginService;

    private final PostService service;

    @PostMapping("/board/postLike")
    public ResponseEntity<?> postLike(@RequestParam("postId") String postId, Principal principal, Model model) throws IOException {
        Long postIdLong = Long.parseLong(postId);
        String userId = memberLoginService.findByPrincipal(principal).getUserId();

        // HashMap에 담아서 전달
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("postId", postId);
        int n = service.updatePostLike(map);
        Long likeCount = service.selectPagePost(postIdLong).getLikeNum();;
        if (n == 1) {
            return ResponseEntity.ok(Map.of("success", true, "message", "좋아요 처리 성공", "likeCount", likeCount));
        } else {
            return ResponseEntity.ok(Map.of("success", false, "message", "이미 좋아요 누른 게시글입니다."));
        }
    }
}
