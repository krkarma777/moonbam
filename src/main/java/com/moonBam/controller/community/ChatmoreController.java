package com.moonBam.controller.community;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.moonBam.dto.AdminReportDTO;
import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.ReportDTO;
import com.moonBam.service.CommunityChatmoreService;


@Controller
public class ChatmoreController {
	
	@Autowired
	CommunityChatmoreService chatmoreService;
	
	@RequestMapping(value = "/Chatmore", method=RequestMethod.GET)
	public String Chatmore(int chatNum, Model m) {
		System.out.println("/Chatmore 호출");
		
		
		//1. chatNum을 이용하여 ChatMember DB에서 방에 들어가있는 user들의 Id를 얻을 수 있음
		List<String> ChatMemberIdByChatNum =  chatmoreService.ChatMemberIdByChatNum(chatNum);
		//System.out.println("ChatMemberIdByChatNum의 결과인 userId들"+ChatMemberIdByChatNum);
		
		//2. 얻어온 user들의 Id를 memberDB에서 조회하면서 LIST에 담은 정보 JSP에 보내주기
		List<MemberDTO> memberDtoList = new ArrayList<>();
		
		for (String userId : ChatMemberIdByChatNum) {
			MemberDTO memberDto =  chatmoreService.memberByChatMemberId(userId);
			memberDtoList.add(memberDto);
		}
		//System.out.println("memberDtoList "+memberDtoList);
		
		//3. leaderId DB에서 뽑아와서 그 회원의 정보를  dto째로 JSP로 넘겨주기 (chatNum이용)
		String leaderId =  chatmoreService.ChatLeaderIdByChatNum(chatNum);
		MemberDTO leadermemberDto =  chatmoreService.memberByChatMemberId(leaderId);
		
		
		m.addAttribute("leadermemberDto", leadermemberDto); ////////////리더의 dto정보
		m.addAttribute("memberDtoList", memberDtoList); ////////////대화방 참여하고 있는 멤버들
		m.addAttribute("chatNum",chatNum); //////////chatNum
	
		return "/community/chat-more"; //jsp
	}
	
	
	@RequestMapping(value = "/Chatmore/ChatmoreReport", method=RequestMethod.GET)
	public String ChatmoreReportGet(@Param(value = "userId") String userId, @Param(value = "chatNum") String chatNum, Model m) {
		//회원 신고하기 눌렀을 때 띄워지는 자식 팝업창 리턴.
		//링크에 신고할 회원의 id와 나의 채팅방 num을 쿼리스트링 방식으로 붙여보냈음
		
		m.addAttribute("userId", userId);
		m.addAttribute("chatNum", chatNum);
		
		
		return "/community/chat-more-report";
	}
	
	@RequestMapping(value = "/Chatmore/ChatmoreReport", method=RequestMethod.POST)
	public String ChatmoreReportPost(AdminReportDTO adminReportDTO,@RequestParam("targetId") String chatNum, HttpSession session) {
		
		
		// AdminReportDTO DB에 insert 날릴거임
		System.out.println("AdminReportDTO"+adminReportDTO+"     "+"targetId"+chatNum);
		
		chatmoreService.ChatmoreReportPostInsert(adminReportDTO);
		
		session.setAttribute("mesg", "신고 접수가 완료되었습니다.");
		
		return "redirect:/Chatmore";
	}
	
	

}
