package com.moonBam.controller.board.beforeRefactor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.moonBam.controller.board.util.AuthUtils;
import com.moonBam.controller.board.util.ErrorMessage;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/edit")
public class BoardEditController {

	AuthUtils authUtils;
	PostService postService;

	String INVALID_REQUEST = ErrorMessage.INVALID_REQUEST.getMessage();
	String ERROR_PAGE = ErrorMessage.ERROR_PAGE.getMessage();
	String ERROR = ErrorMessage.ERROR.getMessage();

	@GetMapping
	public String editForm(Model model, @RequestParam("postId") Long postId) {
		PostDTO post = postService.findById(postId);
		if (post == null) {
			model.addAttribute(ERROR, INVALID_REQUEST);
		}
		model.addAttribute("post", post);
		return "board/edit";
	}

	@PostMapping
	public String edit(Map<String, String> paramMap, Model model, HttpSession session,
			@RequestParam("postId") Long postId) {

		PostDTO post = postService.findById(postId);
		if (post == null) {
			model.addAttribute(ERROR, INVALID_REQUEST);
			return ERROR_PAGE;
		}

		if (!authUtils.isUserAuthorized(session, post)) {
			return ERROR_PAGE;
		}
		model.addAttribute("userId", paramMap.get("userId"));

		String postTitle = paramMap.get("postTitle");
		String postText = paramMap.get("postText");
		String postBoard = paramMap.get("bn");
		Long postCategory = Long.parseLong(paramMap.get("postCategory"));
		new PostService().update(postId, postTitle, postText, postCategory);
		return String.format("redirect:/Acorn/board/content?postId=%d&bn=%s", postId, postBoard);
	}

}
