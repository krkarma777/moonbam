package com.moonBam.controller.board.util;

import java.security.Principal;
import java.util.Map;

import com.moonBam.service.member.MemberLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.IPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtils {

	private final MemberLoginService memberLoginService;

    public boolean isUserAuthorized(Principal principal, IPost post) {
		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
    	String userId = loginUser.getUserId();
        return post.getUserId().equals(userId);
    }
}
