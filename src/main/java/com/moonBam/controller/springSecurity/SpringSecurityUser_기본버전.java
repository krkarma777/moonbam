//package com.moonBam.controller.springSecurity;
//
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//
//import com.moonBam.dto.MemberDTO;
//
//
//public class SpringSecurityUser extends User{
//
//	private static final long serialVersionUID = 1L;
//
//	public SpringSecurityUser(MemberDTO dto) {
//		
//		super(	dto.getUserId(), 
//				dto.getUserPw(), 
//				AuthorityUtils.createAuthorityList(dto.getRole().toString()));
//	}
//
//}