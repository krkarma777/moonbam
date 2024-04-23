//package com.moonBam.security_구.service.social;
//
//
//import com.moonBam.dto.MemberDTO;
//import com.moonBam.security_구.jwt.JWTUtil;
//import com.moonBam.service.member.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class NaverLoginService implements SocialOauth2Service {
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
//        // 메시지 상태 확인 후, 성공적으로 정보를 가져왔는지 검증
//        if (!"success".equals(attributes.get("message").toString())) {
//            throw new OAuth2AuthenticationException("OAuth2 공급자로부터 사용자 정보를 성공적으로 가져오지 못했습니다.");
//        }
//        // attributes에서 response를 추출하여 사용자 정보 설정
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        String username = response.get("id").toString();
//        Optional<MemberDTO> naverUserOpt = Optional.ofNullable(memberService.findByUserId(username));
//        String role = "ROLE_USER";
//        MemberDTO memberDTO = new MemberDTO();
//        if (naverUserOpt.isEmpty()) {
//            memberDTO.setUserId(response.get("id").toString()); // 사용자 고유 id를 username으로 사용
//            memberDTO.setRestoreUserEmailId(response.get("email").toString());
//            memberDTO.setRestoreUserEmailDomain(response.get("email").toString());
//            UUID randomUUID = UUID.randomUUID();
//            String shortUUID = randomUUID.toString().split("-")[0] + randomUUID.toString().split("-")[1];
//            memberDTO.setNickname("Naver_" + shortUUID);
//            memberDTO.setUserType(role); // 모든 사용자를 자영업자로 설정
//            memberDTO.setUserPw(UUID.randomUUID().toString());
//            memberService.insert(memberDTO);
//        } else {
//            role = naverUserOpt.get().getUserType();
//        }
//        return jwtUtil.createJwt(response.get("id").toString(), role, Long.parseLong(expiredMs));
//    }
//}
