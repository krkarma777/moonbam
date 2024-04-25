//package com.moonBam.controller.board.beforeRefactor;
//
//
//import com.moonBam.dto.MemberDTO;
//import com.moonBam.dto.board.MessageDTO;
//import com.moonBam.service.MessageService;
//import com.moonBam.service.member.MemberLoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.security.Principal;
//import java.util.HashMap;
//import java.util.List;
//
//@Controller
//public class MessageController {
//	
//	@Autowired
//	MessageService memberService;
//
//	@Autowired
//	MemberLoginService memberLoginService;
//	
//	
//	@GetMapping("/board/note")
//	public String messageForm(Principal principal, Model model) {
//
//		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
//        String senderId = loginUser.getUserId();
//
//    	List<MessageDTO> sList = memberService.selectSendedMessage(senderId);
//		model.addAttribute("sendedMessage",sList);
//		
//		List<MessageDTO> rList = memberService.selectReceivedMessage(senderId);
//		model.addAttribute("receivedMessage", rList);
//
//		return "board/message";
//	}
//
//	// post
//	@PostMapping("/board/note")
//	public String messageForm(@RequestParam("receiverId") String receiverId,
//							  @RequestParam("messageContent") String messageContent,
//							  Principal principal) {
//
//		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
//		String senderId = loginUser.getUserId();
//
//		HashMap<String, String> map = new HashMap<>();
//		map.put("senderId", senderId);
//		map.put("receiverId", receiverId);
//		map.put("messageContent", messageContent);
//
//
//		memberService.insert(map);
//
//		return "redirect:note";
//	}
//}
