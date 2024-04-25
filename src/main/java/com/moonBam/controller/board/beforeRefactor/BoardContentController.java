package com.moonBam.controller.board.beforeRefactor;

import com.moonBam.controller.board.util.AuthUtils;
import com.moonBam.controller.board.util.ErrorMessage;
import com.moonBam.dto.board.PostPageDTO;
import com.moonBam.service.PostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class BoardContentController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	AuthUtils authUtils;
	
	@GetMapping("/board/content")
	public String contentView(@RequestParam("postId") Long postId, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		PostPageDTO post = postService.selectPagePost(postId);

		model.addAttribute("postText", post.getPostText()); // 게시글 내용
		model.addAttribute("postTitle", post.getPostTitle()); // 게시글 제목
		model.addAttribute("postDate", post.getPostDate()); // 게시글 작성일
		model.addAttribute("nickname", post.getNickname()); // 게시글 작성자의 사용자 닉네임
		model.addAttribute("viewNum", post.getViewNum()); // 조회수
		model.addAttribute("likeNum", post.getLikeNum()); // 좋아요 갯수
		model.addAttribute("commentCount", post.getCommentCount()); // 댓글 갯수

		final String VIEWED_POST_COOKIE = "viewedPost_" + postId;
		Cookie postViewCookie = WebUtils.getCookie(request, VIEWED_POST_COOKIE);

		if (postViewCookie == null) {
			// 조회수 증가 로직을 여기에 구현
			postService.updateViewNum(postId);

			// 쿠키 생성
			Cookie newCookie = new Cookie(VIEWED_POST_COOKIE, "true");
			newCookie.setMaxAge(24 * 60 * 60); // 1일 유효기간
			newCookie.setHttpOnly(true);
			newCookie.setPath("/"); // 사이트 전역에서 쿠키 접근 가능
			response.addCookie(newCookie);
		}
		return "board/content";
	}
}
