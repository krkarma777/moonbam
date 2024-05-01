package com.moonBam.controller.community;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.simp.stomp.StompClientSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.AdminReportDTO;
import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.ReportDTO;
import com.moonBam.service.CommunityChatmoreService;
import com.moonBam.service.CommunityEnterOutService;
import com.moonBam.service.member.MemberLoginService;


@Controller
public class ChatmoreController {
	
	@Autowired
	CommunityChatmoreService chatmoreService;
	
	@Autowired
	MemberLoginService memberLoginService;
	
	@Autowired
	CommunityEnterOutService comEnterOutService;
	
	//////////////재사용 함수/////////////////
	//1.(chatNum 으로 채팅방 정보 가져오기)
	public ChatRoomDTO chatRoomSelectBychatNum(int chatNum) {
		
		return comEnterOutService.chatRoomSelectById(chatNum);
		
		}
	////////////////////////////////////////
	
	
	@RequestMapping(value = "/Chatmore", method=RequestMethod.GET)
	public String Chatmore(int chatNum, Model m, Principal principal) {
		System.out.println("/Chatmore 호출");
		//System.out.println("Chatmore에서의 "+chatNum);
		
		
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
		//System.out.println("leaderId::::"+leaderId);
		
		MemberDTO leadermemberDto =  chatmoreService.memberByChatMemberId(leaderId);
		System.out.println("leadermemberDto 확인하기   "+leadermemberDto);
		
		
		//4. session에 저장되었던 login정보를 principal로 바꾸면서 추가한 코드
		MemberDTO memberDTO = memberLoginService.findByPrincipal(principal);
		
		
		//5. chatNum을 이용해서 가져온 chatRoomDto 가져오기
		ChatRoomDTO chatroomDTO = chatRoomSelectBychatNum(chatNum);
		
		m.addAttribute("leadermemberDto", leadermemberDto); ////////////리더의 dto정보
		m.addAttribute("memberDtoList", memberDtoList); ////////////대화방 참여하고 있는 멤버들
		m.addAttribute("chatroomDTO",chatroomDTO); //////////chatRoomDTO
		m.addAttribute("memberDTO", memberDTO); ////////////내 로그인 정보
		return "/community/chat-more"; //jsp
	}
	
	
	@RequestMapping(value = "/Chatmore/ChatmoreReport", method=RequestMethod.GET)
	public String ChatmoreReportGet(@Param(value = "userId") String userId, @Param(value = "chatNum") int chatNum, Model m, Principal principal) {
		//회원 신고하기 눌렀을 때 띄워지는 자식 팝업창 리턴.
		//링크에 신고할 회원의 id와 나의 채팅방 num을 쿼리스트링 방식으로 붙여보냈음
		
		/* String reporterId = principal.getName(); */
		
		m.addAttribute("userId", userId);
		m.addAttribute("chatNum", chatNum);
		/* m.addAttribute("reporterId", reporterId); */
		//System.out.println(userId+"4444444444444444444444444  "+chatNum);
		
		
		return "/community/chat-more-report";
	}
	
	@RequestMapping(value = "/Chatmore/ChatmoreReport", method=RequestMethod.POST)
	@ResponseBody///////////
	@CrossOrigin
	public String ChatmoreReportPost(AdminReportDTO adminReportDTO,  @RequestParam("chatNum") int chatNum,  HttpSession session, Principal principal) {
		//@RequestParam("targetId") String targetId, 내가 이걸 왜?
		
		String reporterId = principal.getName();
		adminReportDTO.setReporterId(reporterId);
		
		// AdminReportDTO DB에 insert 날릴거임
		System.out.println("AdminReportDTO"+adminReportDTO+"     "+"chatNum"+chatNum);
		
		chatmoreService.ChatmoreReportPostInsert(adminReportDTO);
		
		//session.setAttribute("mesg", "신고 접수가 완료되었습니다.");
		
		return "successToReport";
	}
	

}
