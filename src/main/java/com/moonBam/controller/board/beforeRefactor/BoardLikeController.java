package com.moonBam.controller.board.beforeRefactor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardLikeController {
	
	PostService service;

    @PostMapping("/board/postLike")
    public String postLike(Map<String, String> paramMap, Model model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String postId = paramMap.get("postId");
        System.out.println(postId);
        Long postIdLong = Long.parseLong(postId);
        String userId = paramMap.get("userId");

        // HashMap에 담아서 전달
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("postId", postId);

        int n = service.updatePostLike(map);
        Long likeCount = service.selectPagePost(postIdLong).getLikeNum();;
        if (n == 1) {
            model.addAttribute("success", true);
            model.addAttribute("message", "좋아요 처리 성공");
            model.addAttribute("likeCount", likeCount);
        } else {
            model.addAttribute("success", false);
            model.addAttribute("message", "이미 좋아요 누른 게시글입니다.");
        }
        // JSON 문자열로 변환
        return objectMapper.writeValueAsString(model);
    }
}
