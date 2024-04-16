package com.moonBam.controller.member;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.MyCommentDTO;
import com.moonBam.dto.MyPageDTO;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.MemberLoginService;
import com.moonBam.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
@RequestMapping("/my-page")
public class MyPageController {

    @Autowired
    LoginService serv;

    @Autowired
    MemberService mserv;

    @Autowired
    MemberLoginService memberLoginService;

    @GetMapping
    public String myPage(Model model, Principal principal) {
        return "member/myPage/MyPageTemplate";
    }

    @GetMapping("/info")
    public ModelAndView userInfo(Principal principal) {
        // 세션에서 loginUser 정보 가져오기
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        if (loginUser == null) {
            return new ModelAndView("redirect:/Login");
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser", loginUser);

        mav.setViewName("member/myPage/nicknameChange");
        return mav;
    }

    //회원정보 수정
    @GetMapping("/update")
    public String updateInfo(Model model, Principal principal) {
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        // 만약 loginUser가 null이면 로그인되지 않은 상태이므로 로그인 페이지로 리다이렉트 또는 예외 처리할 수 있음
        if (loginUser == null) {
            // 로그인되지 않은 상태일 때 로그인 페이지로 이동하거나 다른 처리를 하도록 설정
            return ("redirect:/Login"); // 로그인 페이지 경로로 리다이렉트
        }
        model.addAttribute("loginUser", loginUser);
        return "member/myPage/infoManagement"; // 회원 정보 수정 폼의 JSP 파일 경로
    }

    @GetMapping("/post")
    public String myPost(Model model, String curPage, Principal principal) {
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
        if (loginUser != null) {
            String name = principal.getName();
            // curPage 값을 확인하고, 필요한 경우 처리합니다.
            if (curPage == null) {
                curPage = "1";
            }
            // 페이지 처리를 위한 추가 작업이 필요합니다.
            MyPageDTO mDTO = mserv.selectMyPostPaged(curPage, name); // userId도 넘겨줘야 할 것으로 보입니다.
            System.out.println("Controller: " + mDTO);
            model.addAttribute("mDTO", mDTO);
            model.addAttribute("curPage", curPage); // 현재 페이지 정보를 모델에 추가합니다.

            // ModelAndView 객체를 사용하여 뷰를 반환합니다.
            return "member/myPage/postManagement";
        } else {

            return "redirect:/Login";
        }
    }


    @GetMapping("/comment")
    public String myComment(Model model, HttpSession session, String curPage, Principal principal) {
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
        if (loginUser != null) {
            String name = principal.getName();
            // curPage 값을 확인하고, 필요한 경우 처리합니다.
            if (curPage == null) {
                curPage = "1";
            }
            MyCommentDTO cDTO = mserv.selectmyComm(curPage, name);
            model.addAttribute("cDTO", cDTO);
            model.addAttribute("curPage", curPage); // 현재 페이지 정보를 모델에 추가합니다.
            return "member/myPage/commentManagement";
        } else {
            session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
            return "redirect:/Login";
        }
    }

    @PostMapping("/postDel") // mapping을 postDel로 변경
    public String postDel(@SessionAttribute("loginUser") MemberDTO loginUser,
                          @RequestParam("postId") Long postId,
                          RedirectAttributes redirectAttributes) {
        if (loginUser != null) {
            System.out.println("postDel postId: " + postId);
            int result = mserv.postDel(postId); // MyPageService를 mserv로 변경
            System.out.println("postDel result: " + result);

            return "redirect:/myPost";
        } else {
            redirectAttributes.addFlashAttribute("mesg", "로그인이 필요한 작업입니다.");
            return "redirect:/Login";
        }
    }
}
