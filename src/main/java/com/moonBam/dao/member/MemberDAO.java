package com.moonBam.dao.member;

import com.moonBam.dto.CommentDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.member.MemberCreateRequestDTO;
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


    public int updateNickname(String newNickname) {
        // TODO Auto-generated method stub
        int num = session.update("MyPageMapper.updateNickname", newNickname);
        System.out.println("dao" + num);
        return num;
    }

    public int updateRestoreEmailId(String newRestoreEmailId) {
        // TODO Auto-generated method stub
        int num = session.update("MyPageMapper.updateRestoreEmailId", newRestoreEmailId);
        return num;
    }

    public int updateEmailDomain(String newRestoreEmailDomain) {

        int num = session.update("MyPageMapper.updateRestoreEmailId", newRestoreEmailDomain);
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



}