package com.moonbam.controller.board;

import com.moonbam.dto.MemberDTO;
import com.moonbam.dto.board.PostDTO;
import com.moonbam.dto.board.PostSaveDTO;
import com.moonbam.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class BoardWriteController {
	
	@Autowired
    PostService postService;
	
	@GetMapping("/board/write")
	public String writeForm() {
        return "board/post";
	}
    
	@PostMapping("/board/write")
	public String write(Map<String, String> paramMap, Model model, HttpSession session) {
        MemberDTO member = (MemberDTO)session.getAttribute("loginUser");
        
        String userId = member.getUserId();
        model.addAttribute("userId", userId);
        
		List<PostSaveDTO> postSaveList = postService.postSaveSelect(userId);
		model.addAttribute("postSaveList", postSaveList);
		
        PostDTO post = createPostDTO(paramMap);

        Long postId = postService.insertContent(post);

        String postBoard = paramMap.get("bn");
        String redirectURL = String.format("/Acorn/board/content?postId=%d&bn=%s", postId, postBoard);
        return "redirect:" + redirectURL;
	}
	
    private PostDTO createPostDTO(Map<String, String> paramMap) {
        PostDTO post = new PostDTO();
        post.setUserId(paramMap.get("userId"));
        post.setNickname(paramMap.get("nickname"));
        post.setPostTitle(paramMap.get("postTitle"));
        post.setPostText(paramMap.get("postText"));
        post.setPostBoard(paramMap.get("bn"));
        post.setCategoryId(Long.parseLong(paramMap.get("postCategory")));
        return post;
    }
}
