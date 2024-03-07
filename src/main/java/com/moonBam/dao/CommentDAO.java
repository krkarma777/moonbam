package com.moonBam.dao;

import com.moonBam.dto.CommentDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class CommentDAO {

	@Autowired
	SqlSessionTemplate session;
	
	public CommentDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int  AddCommnet(CommentDTO commentDB) {
		
		int recordCount = session.insert("CommentMapper", commentDB);
		return recordCount;
		
	}

	public List<CommentDTO> selectAll(){
		
		List<CommentDTO> commentDB = session.selectList("SelectAll");
		return commentDB;
		
	}
	
	public int selectOne(CommentDTO commentDB) {
		int comid = session.selectOne("selectOne", commentDB);
		return comid;
	}
	
	
	public int deleteComment(String comId){ 
		
		int num = session.delete("deleteComment", comId);
		return num;
	}
	
	public int deleteUpdateComment(String comId){ 
		
		int num = session.update("deleteUpdateComment", comId);
		return num;
	}

	
	public int updateComment(HashMap<String, String> map){
			
			int num = session.update("updateComment", map);
			return num;
		}
	
	

	public List<CommentDTO> selectAllByPostId(Long postId) {
		return session.selectList("CommentMapper.selectAllByPostId", postId);
	}



	public List<CommentDTO> replyComSelectAllBycomId(String comId) {
		return session.selectList("replyComSelectAllBycomId",comId);
	}
}
