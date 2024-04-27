package com.moonBam.controller.member;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.moonBam.dto.member.RestoreRestrictedMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moonBam.dao.member.LoginDAO;
import com.moonBam.dto.AnonymousBoardDTO;
import com.moonBam.dto.AnonymousCommentDTO;
import com.moonBam.dto.AnonymousReplyDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.AnonymousBoardService;
import com.moonBam.service.member.AnonymousCommentService;
import com.moonBam.service.member.AnonymousReplyService;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.RegisterService;
import com.moonBam.springSecurity.SpringSecurityUser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class AjaxController {

	@Autowired
	LoginService lServ;

	@Autowired
	RegisterService rServ;
	
	@Autowired
	AnonymousBoardService dServ;
	
	@Autowired
	AnonymousCommentService anonymousCommentService;
	
	@Autowired
	AnonymousReplyService anonymousReplyService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	LoginDAO dao;
	
	
	//스프링시큐리티
	@GetMapping("/userinfomation")
    public MemberDTO getUserJWTData() {
        // 현재 사용자의 인증 객체를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // 인증 객체로부터 현재 사용자의 이름을 가져옴
        String username = authentication.getName();
        MemberDTO memberData = dao.userDetail(username);
        MemberDTO dto = new MemberDTO();
        	dto.setUserId(username);
        	dto.setNickname(memberData.getNickname());
        	dto.setRole(memberData.getRole());
        	dto.setEnabled(memberData.isEnabled());

        // 사용자의 이름을 반환
        return dto;
    }
	
	//***************************************************************************************************************
	//***************************************************로 그 인*******************************************************
	//***************************************************************************************************************
	
	//메인에서 로그인 여부 확인 에이젝스
	@PostMapping("AjaxCheckIDPW")
	public String AjaxCheckIDPW(String userId, String userPw) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {

		//	로그인 에이젝스 실행 시 입력한 아이디와 비밀번호 출력
		//	System.out.println(userId);
		//	System.out.println(userPw);
			
		//	아이디로만 확인해봤을 때 데이터가 있는지 확인(비밀번호는 매번 바뀌기 때문에 사용 불가)
			MemberDTO dto= dao.userDetail(userId);

		//	아이디가 없을 경우에는 바로 Ajax 종료
			if(dto==null) {
				System.out.println("가입되지 않은 아이디");
				return "loginFail";
			}
		
		//	아이디가 있을 경우	
		//	DB에 입력된 비밀번호 출력
		//	System.out.println("dto에 저장된 암호: "+dto.getUserPw());


		//	입력한 비밀번호와 DB의 비밀번호가 match되는지 확인(인코딩되지 않은 입력 그대로의 비밀번호, DB의 비밀번호)
			boolean canLogin = false;
			try {
				canLogin = encoder.matches(userPw, dto.getUserPw());
			} catch (Exception e) {
				System.out.println("소셜 회원가입자");
				return "socialLogin";
			}

		//	False면 Ajax로 인한 메세지 출력	
			if (!canLogin) {
				System.out.println("비밀번호 오류");
				return "loginFail";
			}

		//	활동 정지 상태지만, 자진 탈퇴한 경우
		RestoreRestrictedMember restoreRestrictedMember = lServ.restoreMember(userId);
		System.out.println(restoreRestrictedMember);
		if (!restoreRestrictedMember.isEnabled() && restoreRestrictedMember.getCause().equals("myself")){
			System.out.println("자진탈퇴 회원 복구");
			return "loginSuccess";
		}

		//	False면 활동 정지 상태
		if (!dto.isEnabled()) {
			System.out.println("활동 정지 상태");
			return "suspendedId";
		}

		//	True면 Submit 정상 진행
		System.out.println("로그인 진행");
		return "loginSuccess";
	}
	
	//메인에서 이메일 중복 확인 에이젝스
	@PostMapping("AjaxCheckEmail")
	public String AjaxCheckEmail(String userId) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		
		MemberDTO dto = rServ.findDTOByUserId(userId);
		//System.out.println("AjaxCheckEmail: "+dto);

		String mesg = "RegisterSuccess";
		
		if (dto != null) {
			mesg = "RegisterFail";                

			if (dto.getGoogleConnected() == 1 || dto.getKakaoConnected() == 1 || dto.getNaverConnected() == 1) {
				mesg = "socialRegister";
			}
		
		}
		
		return mesg;
	}
	
	//***************************************************************************************************************
	//***************************************************회원 가입*******************************************************
	//***************************************************************************************************************
	
	//회원가입 자식창에서 닉네임 중복 에이젝스
	@PostMapping("/AjaxNicknameDuplicate")
	public String AjaxNicknameDuplicate(String nickname) {
		boolean isDuplicate = rServ.isUserNicknameDuplicate(nickname);
		String mesg = "notDuplicate";
		if (isDuplicate) {
			mesg = "duplicate"; 
        } 
		return mesg;
	}
		
	//***************************************************************************************************************
	//***************************************************게 시 판*******************************************************
	//***************************************************************************************************************
		
	
	//익명 유저가 게시판에 추천을 했는지 여부를 쿠키로 저장
	@PostMapping("/increaseDBoardRecommendNum")
	public int increaseDBoardRecommendNum(String userKey, int boardNum, String recommendVal, HttpServletRequest request, HttpServletResponse response) {
		
		AnonymousBoardDTO dto = dServ.viewDBoardContent(boardNum);
		int recommendNum = dto.getRecommendNum();
		
		//페이지에서 불러온 현재 접속한 유저Key | 페이지 번호 | 좋아요 상태
		//System.out.println("userKey: " + userKey);
		//System.out.println("boardNum: "+ boardNum);
		//System.out.println("recommendVal: "+recommendVal);
		
		String LikeCookieKey = "K"+userKey+"N"+boardNum+"Like";
		//System.out.println(LikeCookieKey);
		
		if(recommendVal.equals("like")) {
			dServ.increaseDBoardRecommendNum(boardNum);
			dto = dServ.viewDBoardContent(boardNum);
			recommendNum = dto.getRecommendNum();
			
			Cookie key= new Cookie(LikeCookieKey, recommendVal);
			key.setMaxAge(60*60*24);
			response.addCookie(key);
			//System.out.println("userKey에 따른 페이지 좋아요 Cookie 생성");
		}
		
		return recommendNum;
	}
	
	//익명 유저가 게시판에 비추천을 했는지 여부를 쿠키로 저장
	@PostMapping("/decreaseDBoardRecommendNum")
	public int decreaseDBoardRecommendNum(String userKey, int boardNum, String disrecommendVal, HttpServletRequest request, HttpServletResponse response) {
		
		AnonymousBoardDTO dto = dServ.viewDBoardContent(boardNum);
		int disRecommendNum = dto.getDisRecommendNum();
		
		//페이지에서 불러온 현재 접속한 유저Key | 페이지 번호 | 좋아요 상태
		//System.out.println("userKey: " + userKey);
		//System.out.println("boardNum: "+ boardNum);
		//System.out.println("disrecommendVal: "+disrecommendVal);
		
		String disLikeCookieKey = "K"+userKey+"N"+boardNum+"disLike";
		//System.out.println(disLikeCookieKey);
		
		if(disrecommendVal.equals("dislike")) {
			dServ.decreaseDBoardRecommendNum(boardNum);
			dto = dServ.viewDBoardContent(boardNum);
			disRecommendNum = dto.getDisRecommendNum();
			
			Cookie key= new Cookie(disLikeCookieKey, disrecommendVal);
			key.setMaxAge(60*60*24);
			response.addCookie(key);
			//	System.out.println("userKey에 따른 페이지 싫어요 Cookie 생성");
		}
		
		return disRecommendNum;
	}
	
	
	//글 수정 / 글 삭제 시 비밀번호 확인
	@PostMapping("/checkPostPW")
	public String checkPostPW(int boardNum, String password) {
		AnonymousBoardDTO dto = dServ.viewDBoardContent(boardNum);
		if(dto.getPassword().equals(password)) {
			return "yes";
		} else {
			return "no";
		}
	}
	
	
	
	//***************************************************************************************************************
	//****************************************************댓 글*******************************************************
	//***************************************************************************************************************
	
	//해당 게시판의 댓글 출력
	@PostMapping("/allComments")
    public List<AnonymousCommentDTO> getComments(int boardNum) throws ParseException {
		List<AnonymousCommentDTO> comments = anonymousCommentService.getAllComments(boardNum);
		
		//리스트의 날짜 형식 변경
		for (AnonymousCommentDTO anonymousCommentDTO : comments) {
			anonymousCommentDTO.setCommentEdittedDate(chooseDateForm(anonymousCommentDTO.getCommentEdittedDate()));
		}
		
		return comments;
    }

	//댓글 추가
	@PostMapping("/addComment")
    public AnonymousCommentDTO addComment(AnonymousCommentDTO dto) {
		
		Date nowDate = new Date();
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String now = format.format(nowDate);
		    dto.setCommentPostDate(now);
		    dto.setCommentEdittedDate(now);
		    
		anonymousCommentService.addComment(dto);
		
        return dto;
    }
	
	//댓글 삭제
	@DeleteMapping("/deleteComment")
    public boolean deleteComment(int anonymousCommentNum, String commentPassword) {
		
		Map<String, Object > map = new HashMap<>();
			map.put("anonymousCommentNum", anonymousCommentNum);
			map.put("commentPassword", commentPassword);
		
		int num = anonymousCommentService.deleteComment(map);
		
		if (num == 0) {
			return false;
		} else {
			return true;
		}
    }
	
	
	//해당 게시판의 대댓글 출력
	@PostMapping("/allReplies")
    public List<AnonymousReplyDTO> getAllReplys(int anonymousCommentNum) throws ParseException {
		
		List<AnonymousReplyDTO> replies = anonymousReplyService.getAllReplys(anonymousCommentNum);
		
		//리스트의 날짜 형식 변경
		for (AnonymousReplyDTO anonymousReplyDTO : replies) {
			anonymousReplyDTO.setReplyEdittedDate(chooseDateForm(anonymousReplyDTO.getReplyEdittedDate()));
		}
		
		return replies;
    }
	
	
	//대댓글 추가
	@PostMapping("/addReply")
    public AnonymousReplyDTO addReply(AnonymousReplyDTO dto) {
		
		Date nowDate = new Date();
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String now = format.format(nowDate);
		    dto.setReplyPostDate(now);
		    dto.setReplyEdittedDate(now);
		    
		anonymousReplyService.addReply(dto);
		
        return dto;
    }
	
	//대댓글 삭제
	@DeleteMapping("/deleteReply")
    public boolean deleteReply(int anonymousReplyNum, String replyPassword) {
		
		Map<String, Object > map = new HashMap<>();
			map.put("anonymousReplyNum", anonymousReplyNum);
			map.put("replyPassword", replyPassword);
		
		int num = anonymousReplyService.deleteReply(map);
		
		if (num == 0) {
			return false;
		} else {
			return true;
		}
    }
	
	//글을 게시한 날짜와 오늘 날짜를 비교하는 함수
	public String chooseDateForm(String date) throws ParseException {
		
		String str = date;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date edittedDate = format.parse(str);									//등록, 수정된 날짜
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd");		//연월일 Format
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH시 mm분");			//시분 Format
		
		String todayForm = dateFormat.format(new Date());						//현재 시간의 연월일
		String edittedDateForm = dateFormat.format(edittedDate);				//등록, 수정된 날짜의 연월일
		String edittedDateTime = timeFormat.format(edittedDate);				//등록, 수정된 날짜의 시분초
		
		if(todayForm.equals(edittedDateForm)) {									//오늘이 글을 쓴 날짜일 경우
			return edittedDateTime;												//jsp에 시분초 전송
		} else {
			return edittedDateForm;												//jsp에 연월일 전송
		}
	}
	
}
