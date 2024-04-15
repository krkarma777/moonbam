package com.moonBam.service.member;

import com.moonBam.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberService memberService;

    public MemberDTO findByPrincipal(Principal principal) {
        if (principal == null) {
            return null;
        }

        return Optional.ofNullable(memberService.findByUserId(principal.getName()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, principal.getName() + " not found"));
    }
}
