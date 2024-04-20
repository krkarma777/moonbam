package com.moonBam.service.member;

import com.moonBam.dao.member.MemberDAO;
import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.MyCommentDTO;
import com.moonBam.dto.MyPageDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.member.MemberCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberDAO dao;



	public MemberDTO findByUserId(String UserId) {
		return dao.findByUserId(UserId);
	}

	public int insert(MemberDTO memberDTO) {
		return dao.insert(memberDTO);
	}
	public int insert(MemberCreateRequestDTO requestDTO) {
		return dao.insert(requestDTO);
	}

	// =========================



	


	public int postDel(Long postId) {
		int n = dao.postDel(postId);
		return n;
	}

	public MyPageDTO selectMyPostPaged(String curPage, String name) {
		MyPageDTO mDTO = dao.selectMyPostPaged(curPage, name);
		return mDTO;
	}

	public MyCommentDTO selectmyComm(String curPage, String name) {
		MyCommentDTO cDTO = dao.selectMyComm(name, curPage);
		return cDTO;
	}

	public void updateMember(MemberDTO loginUser) {
		dao.updateMember(loginUser);
		
	}

	public int deleteMyComment(String comId) {
		
		return dao.deleteMyComment(comId);
	}

	public int updateMyComment(Map<String, String> map) {
		
		return dao.updateMyComment(map);
	}


}