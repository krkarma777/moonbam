package com.moonBam.service.adminpage;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.AdminDeletedPostDAO;
import com.moonBam.dao.PostDAO;
import com.moonBam.dto.AdminDeletedPostDTO;
import com.moonBam.dto.board.PostDTO;

@Service
public class AdminDeletedPostService {

    @Autowired
    AdminDeletedPostDAO dao;
    
    @Autowired
    PostDAO pdao;

    public List<AdminDeletedPostDTO> getDeletedPostList(HashMap<String, String> map){
    	System.out.println("3. 서비스 레이어에서 검색 조건 담긴 map 수신");
    	System.out.println("4. DAO로 전달");
        List<AdminDeletedPostDTO> list = dao.getDeletedPostList(map);
        return list;
    }

	public void cleanDeletedPost() {
		dao.cleanDeletedPost("");
		
	}

	public int restoreDeletedPost(String postid) {

		System.out.println("삭제된 글 가져오기");
		AdminDeletedPostDTO dpDTO = dao.getDeletedPost(postid);
		
		System.out.println(dpDTO);
		
		PostDTO pdto = new PostDTO();
		
		pdto.setPostId(dpDTO.getPostid());
		pdto.setPostBoard(dpDTO.getPostboard());
		pdto.setUserId(dpDTO.getUserid());
		pdto.setContId(dpDTO.getContid());
		pdto.setPostTitle(dpDTO.getPosttitle());
		pdto.setPostDate(dpDTO.getPostdate());
		pdto.setPostEditDate(dpDTO.getPosteditdate());
		pdto.setPostText(dpDTO.getPosttext());
		pdto.setNickname(dpDTO.getNickname());
		pdto.setCategoryId(dpDTO.getCategoryid());
		
		System.out.println("PostDAO전달 전의 postDTO");
		System.out.println(pdto);
		
		System.out.println("post테이블 재저장");
		pdao.insertContent(pdto);
		
		System.out.println("기존 deletedPost에서 삭제");
		dao.cleanDeletedPost(postid);
		
		int n = 1;
		
		return n;
	}
}
