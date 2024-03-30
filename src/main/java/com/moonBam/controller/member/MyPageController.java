package com.moonBam.controller.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.PostService;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.MemberService;


@Controller
public class MyPageController {

	@Autowired
	LoginService serv;
	
	@Autowired
	MemberService mserv;

	// 멤버 리스트 찾기
	 @GetMapping("/userinfo")
	    public ModelAndView userInfo(HttpSession session) {
	        // 세션에서 loginUser 정보 가져오기
	        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	        
	        // 만약 loginUser가 null이면 로그인되지 않은 상태이므로 로그인 페이지로 리다이렉트 또는 예외 처리할 수 있음
	        if (loginUser == null) {
	            // 로그인되지 않은 상태일 때 로그인 페이지로 이동하거나 다른 처리를 하도록 설정
	            return new ModelAndView("redirect:/Login"); // 로그인 페이지 경로로 리다이렉트
	        }
	        
	        // 로그인된 사용자라면 해당 사용자의 정보를 JSP에 전달
	        ModelAndView mav = new ModelAndView();
	        mav.addObject("loginUser", loginUser);
	        mav.setViewName("member/MyPage/MyPage"); // userinfo는 JSP 파일의 경로
	        return mav;
	    }
	
//회원정보 수정
	 @GetMapping("/memberUpdate")
	 public String updateInfo(Model model, HttpSession session) {
		   MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	        
	        // 만약 loginUser가 null이면 로그인되지 않은 상태이므로 로그인 페이지로 리다이렉트 또는 예외 처리할 수 있음
	        if (loginUser == null) {
	        	 // 로그인되지 않은 상태일 때 로그인 페이지로 이동하거나 다른 처리를 하도록 설정
	            return ("redirect:/Login"); // 로그인 페이지 경로로 리다이렉트
	        }
	        model.addAttribute("loginUser", loginUser);
	        return "member/MyPage/UpdateForm"; // 회원 정보 수정 폼의 JSP 파일 경로
	    }

	    @PostMapping("/update")
	    public String updateUserInfo(Map<String, String> paramMap, Model model
	                                 ,HttpSession session, @RequestParam("userId") String userId) {
	        MemberDTO user = mserv.select(userId);
	        
	        if (user == null) {
	            return "redirect:/login";
	        }
	        model.addAttribute("userId", paramMap.get("userId"));
	        // 사용자 정보 업데이트
	        String userName= paramMap.get("userName");
	        String nickname= paramMap.get("nickname");
	        String userPhoneNum1= paramMap.get("userPhoneNum1");
	        String userPhoneNum2= paramMap.get("userPhoneNum2");
	        String userPhoneNum3= paramMap.get("userPhoneNum3");

	        new MemberService().update(userName, nickname, userPhoneNum1, userPhoneNum2, userPhoneNum3);
	       

	        // 마이페이지로 리다이렉트
	        return "redirect:/member/userinfo";
	    }
	}
