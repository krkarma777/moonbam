package com.moonBam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.AdminDeletedPostDTO;
import com.moonBam.dto.board.PostDTO;

@Repository
public class AdminDeletedPostDAO {

    @Autowired
    SqlSessionTemplate session;

    public List<AdminDeletedPostDTO> getDeletedPostList(HashMap<String, String> map) {
    	
    	System.out.println("5. dao가 검색조건 담긴 map 수신, mapper에 전달");
        List<AdminDeletedPostDTO> list = session.selectList("AdminDeletedPostMapper.getList", map);
        System.out.println("6. DB에서 가져온 list dao가 수신");
        System.out.println(list);
        return list;
    }

    //신고된 게시물들이 삭제되어서 삭제된 게시물 DB에 저장
	public int insertDeletedPostsfromReport(List<PostDTO> rList) {
		System.out.println("3.1. 신고된 게시물을 삭제된 게시물 DTO 리스트로 변환");
		int count = 1;
		
		List<AdminDeletedPostDTO> list = new ArrayList<>();
		for(PostDTO post : rList) {
			AdminDeletedPostDTO ddto = new AdminDeletedPostDTO();
			
			ddto.setDeletedate("today");
			
			ddto.setPostid(post.getPostId());
			
			ddto.setPostboard(post.getPostBoard());
			
			ddto.setUserid(post.getUserId());
			
			ddto.setContid(post.getContId());
			if(post.getContId()==null) {
				System.out.println("get contid if문 " + post.getContId());
				ddto.setContid(0L);
				}
			
			ddto.setPosttitle(post.getPostTitle());
			
			ddto.setPostdate(post.getPostDate());
			
			ddto.setPosteditdate(post.getPostEditDate());
			
			if(post.getPostEditDate()==null) {
				ddto.setPosteditdate(post.getPostDate());
				}
			
			ddto.setPosttext(post.getPostText());
			
			ddto.setCategoryid(post.getCategoryId());
			
			ddto.setCause("규정위반");
			
			ddto.setExpiredate("today+30");
			
//			list.add(count, ddto);
			System.out.println(ddto);
			session.insert("AdminDeletedPostMapper.insertDeletedPostsfromReport", ddto);
			System.out.println(count + "개의 레코드가 삭제된 게시글 DB에 삽입됨");
			count +=1;
			
		}
		System.out.println(list);
		
		return count;
	}

	public void cleanDeletedPost(String postid) {
		session.delete("AdminDeletedPostMapper.cleanDeletedPost", postid);
	}

	public AdminDeletedPostDTO getDeletedPost(String postId) {
		System.out.println("in AdminDeletedPostDAO.getDeletedPost");
		System.out.println(postId+"를 매퍼에 전달");
		AdminDeletedPostDTO deletedPostDTO = session.selectOne("AdminDeletedPostMapper.getDeletedPost", postId);
		System.out.println("deletedPostDTO");
		return deletedPostDTO;
	}

	public void deleteDeletedPost(String postid) {
		System.out.println("in AdminDeletedPostDAO.deleteDeletedPost");
		session.delete("AdminDeletedPostMapper.deleteDeletedPost", postid);
	}

}
