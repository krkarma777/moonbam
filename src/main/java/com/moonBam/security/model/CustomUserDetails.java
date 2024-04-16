//package com.moonBam.security_구.model;
//
//
//import com.moonBam.dto.MemberDTO;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final MemberDTO memberDTO;
//
//    public CustomUserDetails(MemberDTO memberDTO) {
//        this.memberDTO = memberDTO;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> collection = new ArrayList<>();
//
//        collection.add(new GrantedAuthority() {
//
//            @Override
//            public String getAuthority() {
//                return memberDTO.getUserType();
//            }
//        });
//
//        return collection;
//    }
//
//    @Override
//    public String getPassword() {
//
//        return memberDTO.getUserPw();
//    }
//
//    @Override
//    public String getUsername() {
//
//        return memberDTO.getUserId();
//    }
//
//    // 계정의 만료
//    @Override
//    public boolean isAccountNonExpired() {
//
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}