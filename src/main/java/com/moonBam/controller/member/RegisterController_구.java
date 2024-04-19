//package com.moonBam.controller.member;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.moonBam.dto.MemberDTO;
//import com.moonBam.service.member.LoginService;
//import com.moonBam.service.member.RegisterService;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//
//@Controller
//public class RegisterController_구 {
//
//	@Autowired
//	LoginService lServ;
//	
//	@Autowired
//	RegisterService serv;
//	
//	@Autowired
//	PasswordEncoder encoder;
//	
//	@Autowired
//	MailController mc;
//	
//	@Autowired
//	SecurityController sc;
//
//	//회원가입
//	@PostMapping("/RegisterData")
//	public String InsertData(HttpServletRequest request, MemberDTO dto) throws Exception {
//		
//		System.out.println("RegisterData: "+ dto);
//		
//		String result = "member/Register/registerFailure";
//		
//		
//		// 입력한 정보가 타당하지 않으면 false로 전환
//		boolean failMesg = true;
//
//		// 아이디 검증
//		boolean isDuplicateID = serv.isUserIdDuplicate(dto.getUserId());
//
//		if (dto.getUserId().length() < 4) { // 아이디 길이 규격확인
//			failMesg = false;
//			request.setAttribute("mesg", "아이디 길이가 규정에 맞지 않습니다. 확인해주세요");
//			return result;
//
//		} else if (isDuplicateID) { // 아이디 중복여부 재확인
//			failMesg = false;
//			request.setAttribute("mesg", "이미 가입된 아이디입니다. 확인해주세요");
//			return result;
//		} else { // 아이디 규격 통과
//			System.out.println("아이디 확인");
//		}
//
//		// 비밀번호 검증
//		String userPw = dto.getUserPw();	
//		String userPwConfirm = request.getParameter("userPwConfirm");
//
//		if (!(userPw.equals(userPwConfirm))) { // 비밀번호와 비밀번호 재확인 번호 일치 확인
//			System.out.println("비밀번호 일치 오류 " + userPw + " " + userPwConfirm);
//			System.out.println("회원 가입 실패");
//			failMesg = false;
//			request.setAttribute("mesg", "비밀번호가 일치하지 않습니다. 확인해주세요");
//			return result;
//
//		} else if (userPw.length() < 6) { // 비밀번호 길이 규격확인
//			System.out.println("비밀번호 길이 오류 " + userPw + " " + userPw.length());
//			System.out.println("회원 가입 실패");
//			request.setAttribute("mesg", "비밀번호 길이가 규정에 맞지 않습니다. 확인해주세요");
//			return result;
//
//		} else { // 비밀번호 규격 통과
//			System.out.println("비밀번호 확인");
//		}
//
//		// 닉네임 검증
//		String nickname = dto.getNickname();
//		boolean isDuplicateNickname = serv.isUserNicknameDuplicate(nickname);
//
//		if (nickname.length() < 2) { // 닉네임 길이 규격 확인
//			failMesg = false;
//			request.setAttribute("mesg", "닉네임 길이가 규정에 맞지 않습니다. 확인해주세요");
//			return result;
//
//		} else if (isDuplicateNickname) { // 닉네임 중복 여부 확인
//			failMesg = false;
//			request.setAttribute("mesg", "이미 가입된 닉네임입니다. 확인해주세요");
//			return result;
//
//		} else { // 닉네임 규격 통과
//			System.out.println("닉네임 확인");
//		}
//
//		// 가입일 - 가입한 당일 날짜
//		Date currentDate = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		String userSignDate = dateFormat.format(currentDate);
//		
//		//보안코드
//		String secretCode = sc.encrypt(AnonymousBoardController.getNum(8));
//
//		// 모든 규격을 통과한 경우, insert 진행
//		if (failMesg) {
//
//			dto.setUserPw(encoder.encode(userPw));
//			dto.setSecretCode(secretCode);
//			dto.setUserSignDate(userSignDate);
//			int num = serv.insertNewMember(dto);
//
//			// 성공적으로 insert된 경우, 회원가입 성공 페이지로 이동
//			if (num == 1 && failMesg == true) {
//				System.out.println("회원가입 성공");
//				result = "member/Register/registerSuccess";
//				mc.RegisterCompleteEmail(dto.getUserId(), dto.getNickname(), dto.getSecretCode());
//				
//			// 모든 데이터가 규격을 통과했음에도 insert되지 않았을 경우, 회원가입 실패 페이지로 이동
//			} else {
//				System.out.println("회원가입 실패");
//				request.setAttribute("mesg", "모종에 이유로 가입에 실패했습니다. 다시 한번 해주세요");
//			}
//		}
//		return result;
//	}
//}
