package com.moonBam.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.ChatRoomDTO;

@Controller
public class ChatRoomController {
	
//	@RequestMapping(value="/chatRoom",  method = RequestMethod.POST)
//	public ModelAndView chatRoomPost(@RequestParam String chatNum, @RequestParam String text  ) {
//		System.out.println("chatRoomPost");
//		ChatRoomDTO crDto = new ChatRoomDTO();
//		crDto.setRoomTitle("방 제목");
//		crDto.setRoomText("방 설명");
//		ModelAndView mav = new ModelAndView("community/chatRoom/chatRoom");
//		// 방 제목
//		mav.addObject("title", crDto.getRoomTitle());
//		// 방 설명
//		mav.addObject("text", crDto.getRoomText());
//		return mav;
//	}
//	
	@RequestMapping(value="/chatRoom",  method = RequestMethod.GET)
	public ModelAndView chatRoomGet(String text) {
		// session에서 id 가져오기
		// 현재 시간 
		System.out.println("chatRoomGet");
		ChatRoomDTO crDto = new ChatRoomDTO();
		crDto.setRoomTitle("방 제목");
		crDto.setRoomText("방 설명");
		ModelAndView mav = new ModelAndView("community/chatRoom/chatRoom");
		// 방 제목
		mav.addObject("title", crDto.getRoomTitle());
		// 방 설명
		mav.addObject("text", crDto.getRoomText());
		return mav;
	}
	
	@RequestMapping("/reportWindow")
	public String reportWindow() {
		System.out.println("reportWindow");
		return "community/chatRoom/report";
	}
	
	// 전달 받을 데이터 수정 필요, 신고 처리 필요함
	@RequestMapping(value="/chatReport", method = RequestMethod.POST)
	@ResponseBody
	public void chatReport() {
		System.out.println("chatReport");
	}
	
	@RequestMapping("/memberWindow")
	public String memberWindow() {
		System.out.println("memberWindow");
		return "community/chatRoom/member";
	}
	
	// 전달 받을 데이터 수정 필요, 신고 처리 필요함
		@RequestMapping(value="/chatMember", method = RequestMethod.POST)
		@ResponseBody
		public void chatMember() {
			System.out.println("chatMember");
		}
		
		// 전달 받을 데이터 수정 필요, 신고 처리 필요함
		@RequestMapping(value="/newLeader", method = RequestMethod.POST)
		@ResponseBody
		public void newLeader() {
			System.out.println("newLeader");
		}
		
		// 전달 받을 데이터 수정 필요, 신고 처리 필요함
		@RequestMapping(value="/memberRemove", method = RequestMethod.POST)
		@ResponseBody
		public void memberRemove() {
			System.out.println("memberRemove");
		}
		
}
