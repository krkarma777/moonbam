package com.moonBam.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.PostService;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PageDTO;
import com.moonBam.dto.board.PostDTO;
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
	

	 @PostMapping("/updateNickname")
	    public String updateNickname(
	            @RequestParam("userId") String userId,
	            @RequestParam("newNickname") String newNickname,
	            Model model) {
	        try {
	            // 닉네임 유효성 검증
	            if (newNickname.length() < 2) {
	                model.addAttribute("errorMessage", "닉네임은 최소 2글자 이상이어야 합니다.");
	                return "redirect:/userinfo";
	            }
	            
	            // 닉네임 중복 확인
	            boolean isDuplicateNickname = mserv.isUserNicknameDuplicate(userId, newNickname);
	            if (isDuplicateNickname) {
	                model.addAttribute("errorMessage", "이미 사용 중인 닉네임입니다.");
	                System.out.println("duplicate"+newNickname);
	                return "redirect:/userinfo";
	            }
	            
	            // 닉네임 업데이트
	            mserv.updateNickname( newNickname);
	            model.addAttribute("successMessage", "닉네임이 성공적으로 업데이트되었습니다.");
	            System.out.println("update성공"+newNickname);
	            return "redirect:/userinfo";
	        } catch (Exception e) {
	            model.addAttribute("errorMessage", "닉네임 업데이트 중 오류가 발생했습니다.");
	            System.out.println("newNickName: "+newNickname);
	            return "redirect:/userinfo";
	        }
	    }
	 @PostMapping("/updateRestoreEmailId")
	 public String updateRestoreEmail(
	         @RequestParam("userId") String userId,
	         @RequestParam("newRestoreEmailId") String newRestoreEmailId,
	         @RequestParam("newRestoreEmailDomain") String newRestoreEmailDomain,
	         Model model) {
	     try {
	         mserv.updateRestoreEmail(userId, newRestoreEmailId, newRestoreEmailDomain);
	         model.addAttribute("successMessage", "추가 이메일이 성공적으로 업데이트되었습니다.");
	     } catch (Exception e) {
	         model.addAttribute("errorMessage", "추가 이메일 업데이트 중 오류가 발생했습니다.");
	     }
	     return "redirect:/userinfo";
	 }
//	 @GetMapping("/myPost")
//	 public ModelAndView myPost(HttpSession session, @RequestParam(defaultValue = "1") int curPage, Model model) {
//	     MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
//	     if (loginUser != null) {
//	         String userId = loginUser.getUserId();
//	         
//	         // 페이지당 게시글 수 설정
//	         int perPage = 20;
//	         int offset = (curPage - 1) * perPage;
//	         
//	         // 매개변수를 담은 Map 생성
//	         Map<String, Object> map = new HashMap<>();
//	         map.put("userId", userId);
//	         map.put("offset", offset);
//	         map.put("perPage", perPage);
//	         
//	         // 해당 사용자의 게시글을 페이지네이션에 맞게 가져옵니다.
//	         PageDTO<PostDTO> pageDTO = mserv.selectMyPostPaged(map);
//	         
//	         // 모델에 게시글 목록을 추가합니다.
//	         model.addAttribute("postList", pageDTO);
//	         
//	         // 모델에 현재 페이지 번호를 추가합니다.
//	         model.addAttribute("curPage", curPage);
//	         
//	         // ModelAndView 객체를 사용하여 뷰를 반환합니다.
//	         return new ModelAndView("member/MyPage/MypageArticle");
//	     } else {
//	         // 로그인되지 않은 경우 로그인 페이지로 리다이렉트합니다.
//	         session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
//	         return new ModelAndView("redirect:/Login");
//	     }
//	 }
	 @GetMapping("/myPost")
	 public ModelAndView myPost(HttpSession session, Model model) {
	        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	        if (loginUser != null) {
	            String userId = loginUser.getUserId();
	            List<PostDTO> list = mserv.selectMyPost(userId);
	            model.addAttribute("postList", list);
	            return new ModelAndView("member/MyPage/MypageArticle");
	        } else {
	        	System.out.println("myPost의 else");
	            session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
	            return new ModelAndView("redirect:/Login");
	            
	        }
	    }
	 @GetMapping("/myComment")
	    public ModelAndView myComment(HttpSession session, Model model) {
	        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
	        if (loginUser != null) {
	            String userId = loginUser.getUserId();
	            List<CommentDTO> list = mserv.selectmyComm(userId);
	            model.addAttribute("commList", list);
	            return new ModelAndView("member/MyPage/MyPageComment");
	        } else {
	            session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
	            return new ModelAndView("redirect:/Login");
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
