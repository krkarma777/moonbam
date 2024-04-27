package com.moonBam.dao.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.MyCommentDTO;
import com.moonBam.dto.MyPageDTO;
import com.moonBam.dto.MyScrapDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.member.MemberCreateRequestDTO;

@Repository
public class MemberDAO {

    @Autowired
    SqlSessionTemplate session;


	public MemberDTO findByUserId(String userId) {
		return session.selectOne("com.config.MemberMapper.userDetail", userId);
	}

	public int insert(MemberDTO memberDTO) {
		return session.insert("com.config.MemberMapper.insertNewMember", memberDTO);
	}

    public int insert(MemberCreateRequestDTO requestDTO) {
        return session.insert("com.config.MemberMapper.insertNewMember", requestDTO);
    }

	// ========================
	

    public int postDel(Long postId) {
        int n = session.delete("MyPageMapper.postDel", postId);
        return n;
    }

	public MyPageDTO selectMyPostPaged(String curPage, String name) {
		MyPageDTO mDTO = new MyPageDTO();
		
		int perPage = mDTO.getPerPage();
		int offset = (Integer.parseInt(curPage)-1)*perPage;
		List<PostDTO> selectMyPostPaged = session.selectList("MyPageMapper.selectMyPostPaged", name, new RowBounds(offset, perPage));
		mDTO.setCurPage(Integer.parseInt(curPage));
		mDTO.setList(selectMyPostPaged);
		mDTO.setTotalCount(totalPostCount(name));
		return mDTO;
	}
	private int  totalPostCount(String name) {
		return session.selectOne("countMyPosts",name);
	}

	public MyCommentDTO selectMyComm(String name, String curPage) {
		MyCommentDTO cDTO = new MyCommentDTO();
		int perPage = cDTO.getPerPage();
		int offset = (Integer.parseInt(curPage)-1)*perPage;
		List<CommentDTO> selectMyComm = session.selectList("MyPageMapper.selectMyComm", name, new RowBounds(offset, perPage));
		cDTO.setCurPage(Integer.parseInt(curPage));
		cDTO.setList(selectMyComm);
		cDTO.setTotalCount(totalCommCount(name));
		return cDTO;
	}
private int totalCommCount(String name) {
	return session.selectOne("countMyComments", name);
}

public void updateMember(MemberDTO loginUser) {
	session.update("updateNickname",loginUser);
	
}

public int deleteMyComment(String comId) {
	int num = session.delete("deleteMyComment",comId);
	return num;
}

public int updateMyComment(Map<String, String> map) {
	int num = session.update("updateMyComment", map);
	return num;
}

public void deleteUser(String userId, String password) {
	session.delete("deleteUser", password);
	
}

public MyScrapDTO findAllScrap(String curPage, String name) {
	MyScrapDTO sDTO = new MyScrapDTO();
	
	int perPage = sDTO.getPerPage();
	int offset = (Integer.parseInt(curPage)-1)*perPage;
	List<PostDTO> selectMyPostPaged = session.selectList("ScrapMapper.findAll",name, new RowBounds(offset, perPage));
	sDTO.setCurPage(Integer.parseInt(curPage));
	sDTO.setList(selectMyPostPaged);
	sDTO.setTotalCount(totalPostScrap(name));
	return sDTO;
}
private int  totalPostScrap(String name) {
	return session.selectOne("countMyScraps",name);
}

public int updateMyPost(Map<String, String> map) {
	int num = session.update("updateMyPost", map);
	return num;
}

public int checkCommentsExist(Long postId) {
	int num = session.selectOne("checkCommentsExist", postId);
    return num;
}

public int scrapDel(Long scrapId) {
	int num = session.delete("ScrapMapper.delete",scrapId);
	return num;
}

public int enabled(Map<String, String> map) {
	int num = session.update("enabled", map);
	return num;
}

public int insertAdminRestrictedMember(String userId) {
	int num = session.insert("insertAdminRestrictedMember", userId);
	return num;
}
}