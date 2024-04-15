package com.moonBam.service.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moonBam.dao.member.MemberDAO;
import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.MyPageDTO;
import com.moonBam.dto.board.PostDTO;


@Service
public class MemberService {

	@Autowired
	MemberDAO dao;

	public void update(String userName, String nickname, String userPhoneNum1, String userPhoneNum2,
			String userPhoneNum3) {
		dao.updateUser(userName,  nickname,  userPhoneNum1,  userPhoneNum2,
				 userPhoneNum3);
		
	}

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

	public int postDel(Long postId) {
		int n = dao.postDel(postId);
		return n;
	}

	public MyPageDTO selectMyPostPaged(String curPage, String userId) {
//	    // userId를 HashMap에 추가
//	    map.put("userId", userId);
	    // 수정된 코드로 MyPageDTO를 가져옴
	    MyPageDTO mDTO = dao.selectMyPostPaged( userId, curPage);
	    System.out.println("Service: "+mDTO);
	    return mDTO;
	}


//	public PageDTO<PostDTO> selectMyPostPaged(Map<String, Object> map) {
//		return dao.selectMyPostPaged(map);
//	}
}