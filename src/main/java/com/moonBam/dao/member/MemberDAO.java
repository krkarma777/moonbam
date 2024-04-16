package com.moonBam.dao.member;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.MyCommentDTO;
import com.moonBam.dto.MyPageDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.member.MemberCreateRequestDTO;

@Repository
public class MemberDAO {

    @Autowired
    SqlSessionTemplate session;


	public MemberDTO findByUserId(String userId) {
		return session.selectOne("com.config.MemberMapper.findByUserId", userId);
	}

	public int insert(MemberDTO memberDTO) {
		return session.insert("com.config.MemberMapper.insert", memberDTO);
	}

    public int insert(MemberCreateRequestDTO requestDTO) {
        return session.insert("com.config.MemberMapper.insert", requestDTO);
    }

	// ========================
	

   

    public int updateNickname(String newNickname) {
        // TODO Auto-generated method stub
        int num = session.update("MyPageMapper.updateNickname", newNickname);
        System.out.println("dao" + num);
        return num;
    }

 

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


}