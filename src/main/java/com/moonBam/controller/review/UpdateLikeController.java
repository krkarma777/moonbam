package com.moonBam.controller.review;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.ReviewService;
import com.moonBam.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;

@Controller
public class UpdateLikeController {

    @Autowired
    ReviewService service;

    @Autowired
    MemberLoginService memberLoginService;

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    @ResponseBody
    public void UpdateLike(@RequestParam HashMap<String, String> map, Principal principal) {

        // 세션에서 로그인 정보 파싱
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        service.UpdateLike(map);
    }
}
