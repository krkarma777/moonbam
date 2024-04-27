package com.moonBam.springSecurity;

import com.moonBam.controller.board.util.ErrorMessage;
import com.moonBam.dto.member.RestoreRestrictedMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

import com.moonBam.dao.member.LoginDAO;
import com.moonBam.dto.MemberDTO;
import org.springframework.util.ErrorHandler;

import java.util.logging.ErrorManager;

@Service
public class SpringSecurityService  implements UserDetailsService {
	@Autowired
	LoginDAO dao;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
	    MemberDTO dto = dao.userDetail(userId);
		RestoreRestrictedMember restoreRestrictedMember = dao.restoreMember(userId);
		System.out.println(restoreRestrictedMember);

	    if(dto == null) {  // 사용자가 없는 경우
	        throw new UsernameNotFoundException(userId + " 사용자 없음");
	    }

		if (!restoreRestrictedMember.isEnabled() && restoreRestrictedMember.getCause().equals("myself")){
			System.out.println("자진탈퇴 회원 복구");
            try {
                throw new Exception("myself설정"+userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
		}
	    


	    if(!dto.isEnabled()) {
	        throw new UsernameNotFoundException(userId + " 활동 정지");
	    }
	    
	    return new SpringSecurityUser(dto);
	}
}
