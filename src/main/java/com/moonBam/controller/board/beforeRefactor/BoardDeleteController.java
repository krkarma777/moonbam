package com.moonBam.controller.board.beforeRefactor;


import com.moonBam.controller.board.util.AuthUtils;
import com.moonBam.controller.board.util.ErrorMessage;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardDeleteController {
	
	private final PostService postService;
    private final AuthUtils authUtils;
	
    String INVALID_REQUEST = ErrorMessage.INVALID_REQUEST.getMessage();
    String POST_NOT_FOUND = ErrorMessage.POST_NOT_FOUND.getMessage();
    String ERROR = ErrorMessage.ERROR.getMessage();
    String ERROR_PAGE = ErrorMessage.ERROR_PAGE.getMessage();

    @GetMapping("/board/delete")
    public String process(@RequestParam Map<String, String> paramMap, Model model, Principal principal) {

        String postIdParam = paramMap.get("postId");
        String boardName = paramMap.get("bn");
        String redirectURL = "/board/" + boardName;

        try {
            Long postId = parsePostId(postIdParam);
            PostDTO post = getPostOrHandleError(postService, postId, model, redirectURL);

            if (post == null) return "redirect:" + redirectURL;

            if (!authUtils.isUserAuthorized(principal, post)) return "redirect:" + redirectURL;

            postService.delete(postId);
        } catch (NumberFormatException e) {
            model.addAttribute(ERROR, INVALID_REQUEST);
        }

        return "redirect:" + redirectURL;
    }

    // postId 파싱 메서드
    private Long parsePostId(String postIdParam) throws NumberFormatException {
        return Long.parseLong(postIdParam);
    }

    // postId로 게시글 조회 및 에러 처리 메서드
    private PostDTO getPostOrHandleError(PostService service, Long postId, Model model, String redirectURL) {
        PostDTO post = service.findById(postId);
        if (post == null) {
            // 게시글이 존재하지 않을 경우 모델에 에러 메시지 추가
            model.addAttribute(ERROR, POST_NOT_FOUND);
        }
        return post;
    }
}
