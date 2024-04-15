package com.moonBam.dao.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MyCommentDTO;
import com.moonBam.dto.MyPageDTO;
import com.moonBam.dto.board.PostDTO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;


	public void updateUser(String userName, String nickname, String userPhoneNum1, String userPhoneNum2,
			String userPhoneNum3) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userName", userName);
		paramMap.put("nickname", nickname);
		paramMap.put("userPhoneNum1", userPhoneNum1);
		paramMap.put("userPhoneNum2", userPhoneNum2);
		paramMap.put("userPhoneNum3", userPhoneNum3);

		session.update("updateUser", paramMap);
		
	}



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



		public MyPageDTO selectMyPostPaged(String userid, String curPage) {
			MyPageDTO mDTO = new MyPageDTO();
		
			 int perPage = mDTO.getPerPage();
		        int offset = (Integer.parseInt(curPage)-1)*perPage;
		        System.err.println(curPage);

List<PostDTO> selectMyPostPaged = session.selectList("MyPageMapper.selectMyPostPaged", userid, new RowBounds(offset, perPage));

		        mDTO.setCurPage(Integer.parseInt(curPage));
		        mDTO.setList(selectMyPostPaged);
		        mDTO.setTotalCount(totalPostCount(userid));

		        return mDTO;
		}
	    private int totalPostCount(String userid) {
	        return session.selectOne("countMyPosts", userid);
	    }
	    
		public MyCommentDTO selectmyComm(String userId, String curPage) {
			MyCommentDTO cDTO = new MyCommentDTO();
			int perPage = cDTO.getPerPage();
	        int offset = (Integer.parseInt(curPage)-1)*perPage;
	        List<CommentDTO> selectmyComm = session.selectList("MyPageMapper.selectmyComm", userId, new RowBounds(offset, perPage));
	        cDTO.setCurPage(Integer.parseInt(curPage));
	        cDTO.setList(selectmyComm);
	        cDTO.setTotalCount(totalCommCount(userId));

	        return cDTO;
		}
		 private int totalCommCount(String userId) {
		        return session.selectOne("countMyComments", userId);
		    }
		
}