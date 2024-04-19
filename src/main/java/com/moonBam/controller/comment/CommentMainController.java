package com.moonBam.controller.comment;

import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.CommentService;
import com.moonBam.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

      
@Controller
public class CommentMainController {
	
	@Autowired
	CommentService service;

	@Autowired
	MemberLoginService memberLoginService;
	
          
	public CommentMainController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/commentMain")
	public String main() {
		
		
		System.out.println("/commentMain주소처리. main");
		
		
		return "/board/commentMain"; //commentMain.jsp로 이동
		
	}// main end
	
	//전체 댓글 selectALL 하는거
	@RequestMapping(value="/Acorn/selectAllByPostId", method = RequestMethod.POST) 
	@ResponseBody //AJAX 응답
	public List<CommentDTO> selectAllByPostId(Long postId) {
		System.out.println("selectAllByPostId 확인");
		
		List<CommentDTO> commentListAll =  service.selectAllByPostId(postId);
		return commentListAll;
		
	}//selectAllByPostId end
	
	//전체 답글 selectALL하는 거
	@RequestMapping(value="/Acorn/ReplyCommentSelectList", method = RequestMethod.POST)
	@ResponseBody //AJAX 응답
	public List<CommentDTO> ReplyCommentSelectList(String comId) {
		
		List<CommentDTO> commentListAll =  service.replyComSelectAllBycomId(comId);
		return commentListAll;
		
	}//ReplyCommentSelectList end
	
	
	//댓글 작성-> DB INSERT 되는 거
	@RequestMapping(value="/Acorn/CommetInsert",  method = RequestMethod.POST)
	@ResponseBody //AJAX 응답
	public void CommetInsert(CommentDTO commentDB, Principal principal) {
		MemberDTO memberDTO = memberLoginService.findByPrincipal(principal);
		commentDB.setMember(memberDTO);
		System.out.println("하이 CommetInsert");
		int num =  service.AddCommnet(commentDB);
		
	}//CommetInsert end
	
	//대댓글이 있는 부모댓글의 "삭제되었습니다." UPDATE처리와 진짜 수정의 UPDATE처리
	@RequestMapping(value="/Acorn/CommentUpdate",  method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody //AJAX 응답
	public String CommentUpdate(String comId, String How, String comText) {
		
		String mesg = "";
		
		if(How!=null) {
			
			if(("0").equals(How)) { //delteUpdate start. 삭제버튼 눌렸을 때 기능하는 것 
				// ajax로 넘어온 data 가져왔음
			
				int num =service.deleteUpdateComment(comId);
				//삭제 기능
				if(num==0) {
				
					mesg =  "댓글을 삭제할 수 없습니다.";
					
				}else {
					mesg = "댓글이 삭제되었습니다.";
				} //delteUpdate end // //댓글이 삭제되었으면 num이 0보다 클테니, 그럴경우 else의 text를 / 반대의 경우 if의 text를 ajax로 응답하고 있음
				
			}//if(("0").equals(how)) end
			
			
			if(("1").equals(How)) { //update start. 수정버튼 눌렸을 때 기능하는 것
				
				
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("comId", comId);
				map.put("comText", comText);
				
				int num =service.updateComment(map);
				
				//수정 기능
				
				if(num==0) {
					
					mesg = "댓글을 수정할 수 없습니다.";
				
				}else {
					mesg = "댓글이 수정되었습니다.";
				}//update end 
				
		
			}//if(("1").equals(how))  end
			
		
		}//if(How!=null) { end
		
		
		return mesg;
	}//CommentUpdate end
	
	//댓글삭제 DELETE처리
	@RequestMapping(value="/Acorn/CommetDelete",  method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody //ajax처리
	public String CommetDelete(String comId) {
		
		int num =service.deleteComment(comId);
		if(num==0) {
			return "댓글을 삭제할 수 없습니다.";
		}else {
			return "댓글이 삭제되었습니다.";
		}
		
	}//CommetDelete end
	
	
	//대댓글 INSERT
	@RequestMapping(value="/Acorn/ReplyCommentInsert",  method = RequestMethod.POST)
	@ResponseBody //ajax처리
	public void ReplyCommentInsert(CommentDTO commentDB) {
		
		int recordCount = service.AddCommnet(commentDB);
	
		
	}//ReplyCommentInsert end

}
