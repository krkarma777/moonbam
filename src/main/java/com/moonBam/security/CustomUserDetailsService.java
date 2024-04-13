package com.moonBam.security;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberService.select(username);


        return new CustomUserDetails(memberDTO);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
