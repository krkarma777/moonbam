//package com.moonBam.security_구.service.social;
//
//
//import com.moonBam.dto.MemberDTO;
//import com.moonBam.security_구.jwt.JWTUtil;
//import com.moonBam.service.member.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class GoogleLoginService implements SocialOauth2Service {
//
//    private final MemberService memberService;
//    private final JWTUtil jwtUtil;
//
//    @Value("${jwt.expiredMs}")
//    private String expiredMs;
//
//    @Override
//    public String login(Map<String, Object> attributes) {
//
//        MemberDTO memberDTO = new MemberDTO();
//        String username = attributes.get("email").toString();
//        Optional<MemberDTO> kakaoUserOpt = Optional.ofNullable(memberService.findByUserId(username));
//
//        String role = "ROLE_USER";
//        if (kakaoUserOpt.isEmpty()) {
//            memberDTO.setUserId(username);
//            memberDTO.setRestoreUserEmailId(UUID.randomUUID().toString());
//            memberDTO.setRestoreUserEmailDomain(UUID.randomUUID().toString());
//            UUID randomUUID = UUID.randomUUID();
//            String shortUUID = randomUUID.toString().split("-")[0] + randomUUID.toString().split("-")[1];
//            memberDTO.setNickname("Google_" + shortUUID);
//            memberDTO.setUserType("ROLE_USER");
//            memberDTO.setUserPw(UUID.randomUUID().toString());
//            memberService.insert(memberDTO);
//        } else {
//            role = kakaoUserOpt.get().getUserType();
//        }
//
//        // 필요한 정보를 바탕으로 JWT 생성 및 로그 출력
//        return jwtUtil.createJwt(username, role, Long.parseLong(expiredMs));
//    }
//}
