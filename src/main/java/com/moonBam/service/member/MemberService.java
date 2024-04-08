package com.moonBam.service.member;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moonBam.dao.member.MemberDAO;
import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;



	public MemberDTO select(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateNickname(String newNickname) {
		
            dao.updateNickname(newNickname);
            System.out.println("service: "+newNickname);
		
	}
	 @Transactional
	    public void updateRestoreEmail(String userId, String newRestoreEmailId, String newRestoreEmailDomain) {
	       
	       dao.updateRestoreEmailId(newRestoreEmailId);
	       dao.updateEmailDomain( newRestoreEmailDomain);
	    }

	 public boolean isUserNicknameDuplicate(String userId, String nickname) {
			return dao.isUserNicknameDuplicate(nickname);
		}

	 public List<PostDTO> selectMyPost(String userId) {
		    List<PostDTO> list = dao.selectMyPost(userId);
		    return list;
		}

	public List<CommentDTO> selectmyComm(String userId) {
		 List<CommentDTO> list = dao.selectmyComm(userId);
		    return list;
	}


}