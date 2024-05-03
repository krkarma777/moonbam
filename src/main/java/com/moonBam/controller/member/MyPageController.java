package com.moonBam.controller.member;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moonBam.dao.member.LoginDAO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.MyCommentDTO;
import com.moonBam.dto.MyPageDTO;
import com.moonBam.dto.MyScrapDTO;
import com.moonBam.dto.board.ScrapDTO;
import com.moonBam.service.ScrapService;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.MemberLoginService;
import com.moonBam.service.member.MemberService;
import com.moonBam.service.member.RegisterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/my-page")
public class MyPageController {

    @Autowired
    LoginService serv;

    @Autowired
    MemberService mserv;

    @Autowired
    MemberLoginService memberLoginService;

    @Autowired
    ScrapService scrapService;

    @Autowired
    RegisterService rserv;
    
    @Autowired
    LoginDAO ldao;

    @Autowired
     PasswordEncoder encoder;


    @GetMapping("/scrap")
    public String scrap(Model model,String curPage, Principal principal) {
        MemberDTO memberDTO = memberLoginService.findByPrincipal(principal);
        if (memberDTO != null) {
            String name = principal.getName();
            if (curPage == null) {
                curPage = "1";
            }
        MyScrapDTO sDTO =mserv.findAllScrap(curPage, name);
   // List<ScrapDTO> scrapDTOS = scrapService.findAll(memberDTO.getUserId());
        model.addAttribute("sDTO", sDTO);
        model.addAttribute("curPage", curPage);
        return "member/MyPage/scrapManagement";
        } else {

            return "redirect:/mainLogin";
        }
    }

    @GetMapping("/scrap/{scrapId}")
    public String scrapDelete(Principal principal, @PathVariable Long scrapId) {
        MemberDTO memberDTO = memberLoginService.findByPrincipal(principal);
        ScrapDTO scrapDTO = scrapService.findById(scrapId);
        if (Objects.equals(memberDTO.getUserId(), scrapDTO.getUserId())){
            scrapService.delete(scrapId);
        }
        return "redirect:/my-page/scrap";
    }
    @PostMapping("/scrapDel")
    public String scrapDel(Principal principal, @RequestParam("scrapId") Long scrapId,
    		 RedirectAttributes redirectAttributes) {
    	 MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
         if (loginUser != null) {
             // postId에 해당하는 게시물을 삭제합니다.
        	  int result =  mserv.scrapDel(scrapId);
        	  System.out.println(result);
              return "redirect:/my-page/scrap";
         } else {
             redirectAttributes.addFlashAttribute("mesg", "로그인이 필요한 작업입니다.");
             return "redirect:/mainLogin";
         }
    }
    
    @PostMapping("/delAllScraps")
    public String delAllScraps(Principal principal, @RequestBody List<Long> allScrapIds, RedirectAttributes redirectAttributes) {
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
        if (loginUser != null) {
            for (Long scrapId : allScrapIds) {
                System.out.println("Deleting scrap with ID: " + scrapId);
                // postId를 사용하여 해당 게시물 삭제 로직 수행
                mserv.scrapDel(scrapId);
            }
            return "redirect:/my-page/scrap"; // 모든 게시물 삭제 후에 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("mesg", "로그인이 필요한 작업입니다.");
            return "redirect:/mainLogin";
        }
    }
    
    @GetMapping
    public String myPage(Model model, Principal principal) {
        return "redirect:/my-page/post";
    }

    @GetMapping("/info")
    public ModelAndView userInfo(Principal principal) {
        // 세션에서 loginUser 정보 가져오기
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        if (loginUser == null) {
            return new ModelAndView("redirect:/mainLogin");
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser", loginUser);

        mav.setViewName("member/MyPage/nicknameChange");
        return mav;
    }

    //회원정보 수정
    @GetMapping("/update")
    public String updateInfo(Model model, Principal principal) {
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        // 만약 loginUser가 null이면 로그인되지 않은 상태이므로 로그인 페이지로 리다이렉트 또는 예외 처리할 수 있음
        if (loginUser == null) {
            // 로그인되지 않은 상태일 때 로그인 페이지로 이동하거나 다른 처리를 하도록 설정
            return ("redirect:/mainLogin"); // 로그인 페이지 경로로 리다이렉트
        }
        model.addAttribute("loginUser", loginUser);
        return "member/MyPage/infoManagement"; // 회원 정보 수정 폼의 JSP 파일 경로
    }

    //회원정보 수정
    @PostMapping("/updateNickname")
    public String updateNickname(@RequestParam("nickname") String nickname, Principal principal, HttpServletRequest request) {
        // 세션에서 loginUser 정보 가져오기
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);

        // 만약 loginUser가 null이면 로그인되지 않은 상태이므로 로그인 페이지로 리다이렉트 또는 예외 처리할 수 있음
        if (loginUser == null) {
            // 로그인되지 않은 상태일 때 로그인 페이지로 이동하거나 다른 처리를 하도록 설정
            return "redirect:/mainLogin"; // 로그인 페이지 경로로 리다이렉트
        }

        // 닉네임 검증
        boolean isDuplicateNickname = rserv.isUserNicknameDuplicate(nickname);

        if (nickname.length() < 2) { // 닉네임 길이 규격 확인
            request.setAttribute("mesg", "닉네임 길이가 규정에 맞지 않습니다. 확인해주세요");
            return "result"; // 적절한 뷰 페이지로 이동

        } else if (isDuplicateNickname) { // 닉네임 중복 여부 확인
            request.setAttribute("mesg", "이미 가입된 닉네임입니다. 확인해주세요");
            return "result"; // 적절한 뷰 페이지로 이동

        } else { // 닉네임 규격 통과
            // 로그인된 사용자의 닉네임 업데이트
            loginUser.setNickname(nickname);
            // 업데이트된 정보를 DB에 저장
            mserv.updateMember(loginUser);

            return "redirect:/my-page/info"; // 닉네임 업데이트가 성공했으므로 MyPage로 리다이렉트
        }
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
            return "member/MyPage/postManagement";
        } else {

            return "redirect:/mainLogin";
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
            return "member/MyPage/commentManagement";
        } else {
            session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
            return "redirect:/mainLogin";
        }
    }

    @PostMapping(value="/postDel", produces = "text/plain;charset=UTF-8")
    public String postDel(
            @RequestParam("postId") Long postId,
            RedirectAttributes redirectAttributes) {
        // 댓글 갯수 확인
//        int commentCount = mserv.checkCommentsExist(postId);
        
        // 댓글 처리에 따른 게시글 처리
//        if (commentCount > 0) {
            // 댓글이 있는 경우 게시글을 업데이트
            Map<String, String> map = new HashMap<>();
            map.put("postId", String.valueOf(postId));
            map.put("postText", "삭제된 글입니다.");
            int updatedPostCount = mserv.updatedMyPost(map);
            redirectAttributes.addFlashAttribute("message", updatedPostCount > 0 ? "게시글을 삭제했습니다." : "게시글 삭제에 실패했습니다.");
//        } else {
//            // 댓글이 없는 경우 게시글을 삭제
//            int deletedPostCount = mserv.postDel(postId);
//            redirectAttributes.addFlashAttribute("message", deletedPostCount > 0 ? "게시글을 삭제했습니다." : "게시글 삭제에 실패했습니다.");
//        }
        
        return "redirect:/my-page/post";
    }


 //전체 글 삭제   
    @PostMapping("/delAllPosts")
    public String deleteAllPosts(Principal principal, @RequestBody List<Long> allPostIds, RedirectAttributes redirectAttributes) {
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
        if (loginUser != null) {
            for (Long postId : allPostIds) {
                System.out.println("Deleting post with ID: " + postId);
                // postId를 사용하여 해당 게시물 삭제 로직 수행
                mserv.postDel(postId);
            }
            return "redirect:/my-page/post"; // 모든 게시물 삭제 후에 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("mesg", "로그인이 필요한 작업입니다.");
            return "redirect:/mainLogin";
        }
    }

    
//댓글 삭제

    @RequestMapping(value="/commDel", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String commentUpdate(@RequestParam String comId, @RequestParam(required = false) String aboveComId) {
        String message = "";

        // If aboveComId is provided, delete the comment
        if (aboveComId != null && !aboveComId.isEmpty()) {
            // Delete the comment
            int num = mserv.deleteMyComment(comId);
            if (num == 1) {
                message = "댓글이 삭제되었습니다.";
            } else {
                message = "댓글을 삭제할 수 없습니다.";
            }
        } else {
            // If aboveComId is not provided, update the comment to indicate it's deleted
            Map<String, String> map = new HashMap<>();
            map.put("comId", comId);
            map.put("comText", "삭제된 댓글입니다.");
            int num = mserv.updateMyComment(map);
            if (num == 1) {
                message = "댓글이 삭제되었습니다.";
            } else {
                message = "댓글을 삭제할 수 없습니다.";
            }
        }

        return message;
    }    
  
  //전체 댓글 삭제   
    @RequestMapping(value="/delAllComments", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String deleteAllComments(@RequestBody List<String> allCommentIds, @RequestParam(required = false) String aboveComId) {
        StringBuilder message = new StringBuilder();

        // 모든 댓글 ID에 대해 처리
        for (String comId : allCommentIds) {
            if (comId != null && !comId.isEmpty()) {
                // 위의 댓글 ID에 따라 처리 분기
                if (aboveComId == null) {
                    // aboveComId 가 null이면 업데이트 수행
                    Map<String, String> map = new HashMap<>();
                    map.put("comId", comId);
                    map.put("comText", "삭제된 댓글입니다.");
                    int num = mserv.updateMyComment(map);
                    if (num == 1) {
                        message.append("댓글 ").append(comId).append(" 업데이트 성공\n");
                    } else {
                        message.append("댓글 ").append(comId).append(" 업데이트 실패\n");
                    }
                } else {
                    // aboveComId 가 null이 아니면 바로 삭제
                    int num = mserv.deleteMyComment(comId);
                    if (num == 1) {
                        message.append("댓글 ").append(comId).append(" 삭제 성공\n");
                    } else {
                        message.append("댓글 ").append(comId).append(" 삭제 실패\n");
                    }
                }
            }
        }

        return message.toString();
    }

    
    
    //회원탈퇴
    
    @PostMapping("/withdraw")
    public String withdrawPage(Principal principal, HttpSession session, Model model) {
        MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
        
        //비로그인 상태일 때 진행
        if(loginUser == null) {
        	session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
            return "redirect:/mainLogin";
        }
        
        if(loginUser.getKakaoConnected()==1 || loginUser.getGoogleConnected()==1 || loginUser.getNaverConnected()==1) {
            return "member/MyPage/withdraw";
        }
            model.addAttribute("loginUser", loginUser);
            return "member/MyPage/withdraw";
        
    }

    @PostMapping("/confirm")
    public String confirmWithdraw(@RequestParam("password") String password,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  HttpSession session,
                                  Model mode, @RequestParam("userId") String userId) {
        //userPw != confirmPassword 일 경우 에러페이지로 넘어감
    	if (!password.equals(confirmPassword)) {
        	
        	return "member/Find_Info/emailErrorPage";
        }
        //아이디를 쓰는 DTO
        MemberDTO dto = ldao.userDetail(userId);
        
    	//	입력한 비밀번호와 DB의 비밀번호가 match되는지 확인(인코딩되지 않은 입력 그대로의 비밀번호, DB의 비밀번호)
		boolean canLogin = false;
		try {
			canLogin = encoder.matches(password, dto.getUserPw());
		} catch (Exception e) {
			return "member/Find_Info/emailErrorPage";
		}
		  if (!canLogin) {
	  	        System.out.println("기존 비밀번호와 일치하지 않습니다.");
	  	        return "member/Find_Info/emailErrorPage";
	  	    }
		
		//delete 후 deletememberDB로 insert
	//	String result = mserv.deleteUser(userId);
		   Map<String, String> map = new HashMap<>();
           map.put("userId", userId);
           map.put("enabled", "False");
          mserv.enabled(map);

          //AdminRestrictedMember db에 insert
          mserv.insertAdminRestrictedMember(userId);
        
        return "redirect:/logout";

    }

//끝    
  //비밀번호 변경
    
    @PostMapping("/updatePwd")
    public String updatePwd(Principal principal, HttpSession session, Model model) {
       
    	MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
        
        //비로그인 상태일 때 진행
        if(loginUser == null) {
        	session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
            return "redirect:/mainLogin";
        }
        
        if(loginUser.getKakaoConnected()==1 || loginUser.getGoogleConnected()==1 || loginUser.getNaverConnected()==1) {
        	return "member/Find_Info/emailErrorPage";
        }
            model.addAttribute("loginUser", loginUser);
            return "member/MyPage/updatePwd";       
    }
    
    //비밀번호 업데이트
    @GetMapping("/UpdatePassword")
    public String UpdatePassword(String userId, String userPw,
    		@RequestParam("password") String password,
         @RequestParam("userPwConfirm") String userPwConfirm) {

    	System.out.println("push Test ");
    	System.out.println("userId: "+userId);
    	System.out.println("password: "+password);
    	System.out.println("userPw: "+userPw);
    	System.out.println("userPwConfirm: "+userPwConfirm);
    	
    	//새로운 비밀번호, 재확인 일치하는지 확인
    	if (!userPw.equals(userPwConfirm)) {
    		System.out.println("새로운 비밀번호, 재확인 일치하는지 확인");
        	return "member/Find_Info/emailErrorPage";
        }
    	// 기존 비밀번호 일치하는지 확인
    	  MemberDTO dto = ldao.userDetail(userId);
    
  		boolean canLogin = false;
  		try {
  			System.out.println("기존 비밀번호 일치하는지 확인");
  			canLogin = encoder.matches(password, dto.getUserPw());
  		} catch (Exception e) {
  			return "member/Find_Info/emailErrorPage";
  		}

  	  // 기존 비밀번호와 일치하지 않는 경우
  	    if (!canLogin) {
  	        System.out.println("기존 비밀번호와 일치하지 않습니다.");
  	        return "member/Find_Info/emailErrorPage";
  	    }
        //비밀번호 암호화
        String realPassword = encoder.encode(userPw);

        //비밀번호 변경을 위한 Map 생성
        Map<String, String> map = new HashMap<>();
            map.put("userId", userId);
            map.put("userPw", realPassword);

        //비밀번호 업데이트
        serv.updatePassword(map);

        //비밀번호 변경 후 jsp 이동
        return "redirect:/my-page/info";
    }

    
}
