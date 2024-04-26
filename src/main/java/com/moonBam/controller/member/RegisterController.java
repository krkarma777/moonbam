package com.moonBam.controller.member;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.RegisterService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class RegisterController {

	@Autowired
	LoginService lServ;
	
	@Autowired
	RegisterService serv;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	MailController mc;
	
	@Autowired
	SecurityController sc;

	//회원가입
	@PostMapping("/RegisterData")
	public String InsertData(HttpServletRequest request, MemberDTO dto) throws Exception {
		
		System.out.println("RegisterData: "+ dto);

		String result = "member/Register/registerFailure";
		
		// 아이디 검증
		boolean isDuplicateID = serv.isUserIdDuplicate(dto.getUserId());
		if (isDuplicateID) { // 아이디 중복여부 재확인
			System.out.println("아이디 중복");
			request.setAttribute("mesg", "이미 가입된 아이디입니다. 확인해주세요");
			return result;
		}

		// 아이디 규격 통과
		System.out.println("아이디 확인");


		// 비밀번호 검증
		String userPw = dto.getUserPw();
		String userPwConfirm = request.getParameter("userPwConfirm");
		// 비밀번호와 비밀번호 재확인 번호 일치 확인
		if (!(userPw.equals(userPwConfirm))) {
			System.out.println("비밀번호 일치 오류 " + userPw + " " + userPwConfirm);
			request.setAttribute("mesg", "비밀번호가 일치하지 않습니다. 확인해주세요");
			return result;
		}


		// 닉네임 검증
		String nickname = dto.getNickname();
		boolean isDuplicateNickname = serv.isUserNicknameDuplicate(nickname);

		// 닉네임 중복 여부 확인
		if (isDuplicateNickname) {
			System.out.println("닉네임 중복");
			request.setAttribute("mesg", "이미 가입된 닉네임입니다. 확인해주세요");
			return result;
		}

		// 닉네임 규격 통과
		System.out.println("닉네임 확인");


		// 가입일 - 가입한 당일 날짜
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String userSignDate = dateFormat.format(currentDate);


		//보안코드
		String secretCode = sc.encrypt(AnonymousBoardController.getNum(8));


		// 모든 규격을 통과한 경우, insert 진행
		dto.setUserPw(encoder.encode(userPw));
		dto.setSecretCode(sc.encrypt(secretCode));
		dto.setUserSignDate(userSignDate);
		int num = serv.insertNewMember(dto);

		// 성공적으로 insert된 경우, 회원가입 성공 페이지로 이동
		if (num == 1) {
			System.out.println("회원가입 성공");
			result = "member/Register/registerSuccess";
			mc.RegisterCompleteEmail(dto.getUserId(), dto.getNickname(), secretCode);

		// 모든 데이터가 규격을 통과했음에도 insert되지 않았을 경우, 회원가입 실패 페이지로 이동
		} else {
			System.out.println("회원가입 실패");
			request.setAttribute("mesg", "모종에 이유로 가입에 실패했습니다. 다시 한번 해주세요");
		}

		return result;
	}
}
