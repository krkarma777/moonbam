package com.moonBam.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moonBam.dao.member.LoginDAO;
import com.moonBam.dto.MemberDTO;

@Service
public class SpringSecurityService  implements UserDetailsService {
	@Autowired
	LoginDAO dao;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println(userId);
	    MemberDTO dto = dao.userDetail(userId);
	    
	    if(dto == null) {  // 사용자가 없는 경우
	        throw new UsernameNotFoundException(userId + " 사용자 없음");
	    }
	    
	    if(!dto.isEnabled()) {
	        throw new UsernameNotFoundException(userId + " 활동 정지");
	    }
	    
	    return new SpringSecurityUser(dto);
	}
}
