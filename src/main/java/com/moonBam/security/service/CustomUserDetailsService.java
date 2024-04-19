//package com.moonBam.security_구.service;
//
//import com.moonBam.dto.MemberDTO;
//import com.moonBam.security_구.model.CustomUserDetails;
//import com.moonBam.service.member.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final MemberService memberService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MemberDTO memberDTO = memberService.findByUserId(username);
//        return new CustomUserDetails(memberDTO);
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
//        return Collections.singletonList(new SimpleGrantedAuthority(role));
//    }
//}