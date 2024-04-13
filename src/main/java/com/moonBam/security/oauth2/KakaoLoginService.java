package com.moonBam.security.oauth2;


import com.moonBam.dto.MemberDTO;
import com.moonBam.security.jwt.JWTUtil;
import com.moonBam.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoLoginService implements SocialOauth2Service{

    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @Value("${jwt.expiredMs}") private String expiredMs;

    @Override
    public String login(Map<String, Object> attributes) {

        MemberDTO memberDTO = new MemberDTO();
        String username = attributes.get("id").toString();
        Optional<MemberDTO> kakaoUserOpt = Optional.ofNullable(memberService.findByUserId(username));

        String role = "자영업자";
        if (kakaoUserOpt.isEmpty()) {
            memberDTO.setUserId(username);
            memberDTO.setRestoreUserEmailId(UUID.randomUUID().toString());
            memberDTO.setRestoreUserEmailDomain(UUID.randomUUID().toString());
            memberDTO.setNickname("Kakao_");
            memberDTO.setUserType("ROLE_USER");
            memberDTO.setUserPw(UUID.randomUUID().toString());
            memberService.insert(memberDTO);
        } else {
            role = memberDTO.getUserType();
        }

        // 필요한 정보를 바탕으로 JWT 생성 및 로그 출력
        return jwtUtil.createJwt(username, role, Long.parseLong(expiredMs));
    }
}
