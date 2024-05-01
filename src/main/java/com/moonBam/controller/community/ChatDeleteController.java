package com.moonBam.controller.community;


import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.ChatMemberDTO;
import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.service.CommunityChatRoomAutoDelete;
import com.moonBam.service.CommunityChatmoreService;
import com.moonBam.service.CommunityEnterOutService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ChatDeleteController {
	
	
	@Autowired
	CommunityEnterOutService comEnterOutService;
	
	@Autowired
	CommunityChatmoreService comChatMoreService;
	
	
	////////////////////////작동테스트용/////////////
	@Autowired 
	CommunityChatRoomAutoDelete comChatRoomAutoDelService;
	 
	
	
		
	////////////[재사용할 함수fn]
	
	//1.(chatNum 으로 채팅방 정보 가져오기)
	public ChatRoomDTO chatRoomSelectBychatNum(int chatNum) {
	
		return comEnterOutService.chatRoomSelectById(chatNum);
	
	}
	
	//2. chatNum과 userId를 map에 넣어서 chatmember select하기
	public ChatMemberDTO chatmemberSelectFn(Map<String, Object> chatMemberselectMap ) {
		
		return comEnterOutService.chatMemberEnterSelect(chatMemberselectMap);
		
	}
	
	
	//////////방장이 방삭제하기 했을 때/////////////
	@RequestMapping(value="/chatRoom/remove", method=RequestMethod.POST)
	@ResponseBody
	public String requestMethodName(@RequestParam("chatNum") int chatNum,HttpSession session, Principal principal) {
		String userId = principal.getName();
		//userId는 내 id , chatNum은 chatNum
		//1. chatNum으로 chatRoom select 했을 때 현재 인원 수가 1명만 있어야함 => 아닐시 삭제 실패. 방장에게 회원 강퇴 및 나가기 유도하라는 메세지 띄우기
		//2. 처리 다 되면 chatRoom 정보 삭제하기
		
		//chatRoom 채팅방 정보 가져오기
		ChatRoomDTO cdto = chatRoomSelectBychatNum(chatNum);
		
		//chatNum으로 chatMember 검색했을 때 나오는 list size가 1이어야하고 그 1개의 userId가 나의 ID와 동일해야함
		List<String> userIds = comChatMoreService.ChatMemberIdByChatNum(chatNum);
		
		//String returnWhere = "redirect:/?cg=community"; //////////////방 삭제 성공
		String returnWhere = "successToDelete"; //////////////방 삭제 성공
		
		
		if((userIds.size() == 1) && (userIds.get(0).equals(userId)) && (cdto.getCurrentNow() == 1)) { //방삭제를 위한 조건이 다 맞을경우
			
			//chatRoom에서 해당 채팅방 정보 delete + chatMember에서 멤버현황 삭제(tx)처리
			
			try {
				
				//남아있던 chatMember 레코드(본인) 1개 삭제
				int num = comEnterOutService.chatMemberDeleteByChatNum(chatNum);
				
				if(num==0) { //2곳에서 delete 진행했는데 0이라면 삭제가 안 되었다는 것임 
					//session.setAttribute("mesg", "방을 삭제에 실패하였습니다. 다시 시도해주세요.");
					returnWhere = "failToDelete";
				}
				
				//session.setAttribute("mesg", "방을 삭제하였습니다."); ////////////////////방 삭제 성공
				
			}catch(Exception e){
				
				//session.setAttribute("mesg", "방을 삭제에 실패하였습니다. 다시 시도해주세요.");
				returnWhere = "failToDelete";
				
			}
			
			
			
		}else { //방삭제를 위한 조건이 다 안 맞아서 삭제할 수 없는 경우. 높은 확률로 인원이 남아있어서
			
			//session.setAttribute("mesg", "방을 삭제할 수 없습니다. 방장 제외 인원이 남아있다면 강퇴 및 나가기 안내 후 다시 시도해주세요.");
			returnWhere = "failToDeleteToCurrentNow";
			
		}
		
		return returnWhere;
		
		
	}
	



	
	

}






