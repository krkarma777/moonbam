package com.moonBam.dao.member;


import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PageDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.board.PostPageDTO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;



	public int  updateNickname(String newNickname) {
		// TODO Auto-generated method stub
	int num =	session.update("MyPageMapper.updateNickname", newNickname);
	System.out.println("dao"+num);
			return num;
	}

	public int updateRestoreEmailId(String newRestoreEmailId) {
		// TODO Auto-generated method stub
	int num =	session.update("MyPageMapper.updateRestoreEmailId",newRestoreEmailId);
	return num;
	}

	public int updateEmailDomain(String newRestoreEmailDomain) {
	
	int num =	session.update("MyPageMapper.updateRestoreEmailId",newRestoreEmailDomain);
	return num;
	}

		public boolean isUserNicknameDuplicate(String nickname) {
		int num = session.selectOne("com.config.MemberMapper.isUserNicknameDuplicate", nickname);
		return num > 0;
	}

		public List<PostDTO> selectMyPost(String userId) {
			List<PostDTO> list = 
					   session.selectList("MyPageMapper.selectMyPost", userId);
			   return list;
		}

		public List<CommentDTO> selectmyComm(String userId) {
			List<CommentDTO> list = 
					   session.selectList("MyPageMapper.selectMyComm", userId);
			   return list;
		}

		public int postDel(Long postId) {
			int n = session.delete("MyPageMapper.postDel", postId);
			return n;
		}

//		public PageDTO<PostDTO> selectMyPostPaged(Map<String, Object> map) {
//			List<PostDTO> list = session.selectList("MyPageMapper.selectMyPostPaged", map);
//
//			int curPage = (int) map.get("curPage");
//			int perPage = (int) map.get("perPage");
//
//			int totalCount = session.selectOne("countPosts", map);
//
//			PageDTO<PostDTO> pageDTO = new PageDTO<>();
//
//			pageDTO.setList(list);
//			pageDTO.setCurPage(curPage);
//			pageDTO.setPerPage(perPage);
//			pageDTO.setTotalCount(totalCount);
//
//			return pageDTO;
//		}
	
}