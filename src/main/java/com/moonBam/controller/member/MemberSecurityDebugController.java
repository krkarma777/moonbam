//package com.moonBam.controller.member;
//
//import com.moonBam.dto.member.MemberCreateRequestDTO;
//import com.moonBam.service.member.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/member")
//public class MemberSecurityDebugController {
//
//    private final MemberService memberService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @PostMapping
//    public String create(MemberCreateRequestDTO requestDTO) {
//        System.out.println("requestDTO = " + requestDTO);
//
//        String encode = bCryptPasswordEncoder.encode(requestDTO.getUserPw());
//        requestDTO.setUserPw(encode);
//        memberService.insert(requestDTO);
//        return "ok";
//    }
//}
