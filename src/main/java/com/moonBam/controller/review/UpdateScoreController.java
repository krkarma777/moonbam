package com.moonBam.controller.review;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.RateDTO;
import com.moonBam.service.ReviewService;
import com.moonBam.service.member.MemberLoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UpdateScoreController {

    @Autowired
    ReviewService service;

    @Autowired
    MemberLoginService memberLoginService;

    // 비동기 별점 업데이트 서블릿
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @ResponseBody
    public void UpdateScore(RateDTO rate, Principal principal) {
        memberLoginService.findByPrincipal(principal);
        service.UpdateScore(rate);
    }
}
