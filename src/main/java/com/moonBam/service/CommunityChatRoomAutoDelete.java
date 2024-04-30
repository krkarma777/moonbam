package com.moonBam.service;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.moonBam.dto.ChatRoomDTO;

@Component
public class CommunityChatRoomAutoDelete {
	
	////////////////방 자동 폭파되는 기능 바로 실행 . //////////
	
	
	@Autowired
	SqlSessionTemplate session;
	
	
	//@Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
	@Scheduled(cron = "0 */1 * * * *") ///////////테스트 1분마다 실행////////////// 테스트 완료
	@Transactional
    public void chatRoomAutoDelete() {
        // 데이터베이스에서 특정 레코드 삭제 로직 구현
		//System.out.println("1분 실행 테스트 chatRoomAutoDelete함수 작동");
    	
    	///////현재 날짜,시간 뽑기/////////
		LocalDate now = LocalDate.now();
    	
		
		//현재 chatRoom Table에 있는 레코드들 다 가져오기
		List<ChatRoomDTO> cdtoList = session.selectList("ChatMapper.chatRoomSelectAll");
        //System.out.println(cdtoList);
		
		
		//가져온 cdto들의 모임 날짜 다 뽑아보기
		LocalDate mdate = null;
		//날짜 차이 저장할 변수 days
		long days = 0;
		//days가 3일 이상 차이는 게 있을 때 false로 바뀔 변수
		boolean deletedFin = false;
		//days가 3일 이상 차이나는 게 없으면 false로 바뀔 변수
		boolean nothingDel = true;
		//방번호 저장할 변수
		int chatNum = 0;
		//삭제된 방 번호 누적할 변수
		int delChatRoomNum = 0;
		//삭제된 chatmember 레코드 누적할 변수
		int delChatMemberNum = 0;
		
		
		if(cdtoList != null) { //채팅방 룸들이 있다면 ! 
		
			for (ChatRoomDTO chatRoomDTO : cdtoList) {
				
				chatNum = chatRoomDTO.getChatNum();
				mdate = chatRoomDTO.getmDate();
				//System.out.println(mdate);
				
				//뽑아온 모임 날짜와 현재 날짜를 비교하며 days의 값이 3이 될경우 방 삭제 진행 (현재 인원이 있냐 없냐 필요없음, 걍 강제삭제임)
				// 방 삭제 진행 후 chatMember에서도 해당 방관련 레코드 삭제해야함(tx 묶음)
				days = DAYS.between(mdate, now);
				System.out.println("날짜 차이 " + days);
				
					if(days>=3) { //혹시 몰라서 3 이상으로 범위 챙겼음
						try {
							
							deletedFin = true;
							nothingDel = false;
							delChatRoomNum += session.delete("ChatMapper.deleteChatRoom", chatNum);
							delChatMemberNum +=  session.delete("CommunityChatEnterOutMapper.chatMemberDeleteByChatNum", chatNum);
							
							
						}catch(Exception e){
							
							System.out.println("모임 날짜로부터 3일 경과한 커뮤니티 룸에 대한 삭제처리에 오류가 생겼습니다.");
							
						}
					
						
					}//if종료
					
					
			}//for종료
			
				if(nothingDel) { //아무것도 안 거쳐서 flag가 여전히 true일 때만 실행 됨 //만약 거쳐서 true -> false가 되면 
					
					//days가 3인 게 없는거야
					System.out.println("모임 날짜로부터 3일이 경과한 커뮤니티 룸이 없습니다.");
					
				}
			
				if(deletedFin) { //위에 3일 차이나는 거 삭제하는 것이 실행되면 false -> true로 바뀌어서 실행이 됨
					//////////for문 다 돌고 나서 실행될 메세지/////	
					System.out.println("모임 날짜로부터 3일이 경과해 자동 삭제된 커뮤니티 룸의 수는 총 : " + delChatRoomNum + " 입니다.");
					System.out.println("모임 날짜로부터 3일이 경과해 방에서 자동으로 나가게 된 멤버의 수는 총 : "+ delChatMemberNum + " 입니다.");
				}
			
				
				

		}
		
		
	

	}
	
}
