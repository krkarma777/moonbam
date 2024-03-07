package com.moonbam.controller.board;

import com.moonbam.controller.board.util.AuthUtils;
import com.moonbam.controller.board.util.ErrorMessage;
import com.moonbam.dto.board.PostPageDTO;
import com.moonbam.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class BoardContentController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	AuthUtils authUtils;
	
	@GetMapping("/board/content")
	public String contentView(@RequestParam("postId") Long postId, HttpSession session, Model model) {
		
		PostPageDTO post = postService.selectPagePost(postId);
		if(!authUtils.isUserAuthorized(session, post) ) {
			model.addAttribute("mismatchError", ErrorMessage.MISMATCH_ERROR.getMessage());
		}
		model.addAttribute("postText", post.getPostText()); // 게시글 내용
		model.addAttribute("postTitle", post.getPostTitle()); // 게시글 제목
		model.addAttribute("postDate", post.getPostDate()); // 게시글 작성일
		model.addAttribute("nickname", post.getNickname()); // 게시글 작성자의 사용자 닉네임
		model.addAttribute("viewNum", post.getViewNum()); // 조회수
		model.addAttribute("likeNum", post.getLikeNum()); // 좋아요 갯수
		model.addAttribute("commentCount", post.getCommentCount()); // 댓글 갯수
		
		postService.updateViewNum(postId);
		
		return "board/content";
	}
}
