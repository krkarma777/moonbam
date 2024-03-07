package com.moonBam.service;

import java.util.HashMap;
import java.util.List;

import com.moonBam.dao.CommentDAO;
import com.moonBam.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class CommentService {
	
	@Autowired
	CommentDAO dao;


	public int AddCommnet(CommentDTO commentDB) {

		return dao.AddCommnet(commentDB);
	}

	public List<CommentDTO> selectAll() {

		return  dao.selectAll();
	}

	public int selectOne(CommentDTO commentDB) {

		return dao.selectOne(commentDB);
	}

	public int deleteComment(String comId) {
		
		return dao.deleteComment(comId);

	}

	public int deleteUpdateComment(String comId) {
		
		return dao.deleteUpdateComment(comId);

	}
	
	public int updateComment(HashMap<String, String> map) {
		
		return dao.updateComment(map);
		
	}

	public List<CommentDTO> selectAllByPostId(Long postId) {
		
		return dao.selectAllByPostId(postId);
		
	}
	
	
	public List<CommentDTO> replyComSelectAllBycomId(String comId){
	
		return dao.replyComSelectAllBycomId(comId);
	}
	
	

}
