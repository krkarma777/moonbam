package com.moonBam.dao;

import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate session;

	public void insertContent(PostDTO post) {
		session.insert("insertContent", post);
	}

	public Long viewNum(Long postId) {
		return session.selectOne("viewNum", postId);
	}

	public PostPageDTO selectPagePost(Long postId) {
		return session.selectOne("selectPagePost", postId);
	}

	public int postLike(HashMap<String, String> map) {
		return session.insert("postLike", map);
	}

	public LikeDTO selectPostLike(HashMap<String, String> map) {
		LikeDTO lDto = session.selectOne("selectPostLike", map);
		return lDto;
	}

	public int updatePostLike(HashMap<String, String> map) {
		int num = session.update("updatePostLike", map);
		return num;

	}

	public MemberDTO selectMember(String userId) {
		MemberDTO member = session.selectOne("selectMember", userId);
		return member;
	}

	public int insertPostSave(PostSaveDTO dto) {

		return session.insert("insertPostSave", dto);
	}

	public List<PostSaveDTO> listPostSave(String userId) {
		return session.selectList("postSaveSelect", userId);
	}

	public void deletePostSave(Long postSaveId) {
		session.delete("deletePostSave", postSaveId);
	}

	/**
	 * 주어진 게시물 ID에 해당하는 게시물을 조회합니다.
	 * 
	 * @param postId 조회할 게시물의 ID
	 * @return 조회된 게시물 정보를 담은 PostDTO 객체
	 */
	public PostDTO select(Long postId) {
		return session.selectOne("selectPost", postId);
	}

	/**
	 * 주어진 게시물 ID에 해당하는 게시물을 삭제합니다.
	 * 
	 * @param postId 삭제할 게시물의 ID
	 */
	public void delete(Long postId) {
		session.delete("deletePostInfo", postId);
		session.delete("deletePost", postId);
	}

	/**
	 * 주어진 게시판(board)에 속하는 모든 게시물 목록을 조회합니다.
	 * 
	 * @param hashMap 조회할 게시물 목록이 속하는 게시판
	 * @return 조회된 게시물 목록을 담은 List<PostDTO> 객체
	 */
	public List<PostPageDTO> selectAll(HashMap<String, String> hashMap) {
		return session.selectList("selectAllPosts", hashMap);
	}

	public PageDTO<PostPageDTO> selectByPage(HashMap<String, Object> map) {
		List<PostPageDTO> list = session.selectList("selectAllByPage", map);

		int curPage = (int) map.get("curPage");
		int perPage = (int) map.get("perPage");

		int totalCount = session.selectOne("countPosts", map);

		PageDTO<PostPageDTO> pageDTO = new PageDTO<>();

		pageDTO.setList(list);
		pageDTO.setCurPage(curPage);
		pageDTO.setPerPage(perPage);
		pageDTO.setTotalCount(totalCount);

		return pageDTO;
	}

	/**
	 * 주어진 게시물 ID에 해당하는 게시물의 제목과 내용을 업데이트합니다.
	 * 
	 * @param postId         업데이트할 게시물의 ID
	 * @param updatedTitle   업데이트할 게시물 제목
	 * @param updatedContent 업데이트할 게시물 내용
	 */
	public void updateContent(Long postId, String updatedTitle, String updatedContent, Long postCategory) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("postId", postId);
		paramMap.put("updatedTitle", updatedTitle);
		paramMap.put("updatedContent", updatedContent);
		paramMap.put("postCategory", postCategory);

		session.update("updateContent", paramMap);
	}

	/**
	 * 주어진 게시물 ID에 해당하는 게시물의 조회수를 업데이트합니다.
	 * 
	 * @param postId 업데이트할 게시물 정보
	 */
	public int updateViewNum(Long postId) {
		return session.update("updateViewNum", postId);
	}

	public List<PostPageDTO> popularPostTwoDays(HashMap<String, String> map) {
		return session.selectList("popularPostTwoDays", map);
	}

	public Long likeNum(Long postId) {
		return session.selectOne("likeNum", postId);
	}

	public PostSaveDTO selectPostSave(String postSaveId) {
		System.out.println("postSaveId = " + postSaveId);
        return session.selectOne("selectPostSave", postSaveId);
	}

	public void update(PostUpdateRequestDTO postUpdateRequestDTO) {
		session.update("update", postUpdateRequestDTO);
	}
	
	//임시저장글 일정 시간 지나면 자동 삭제
	public void delectExpiredPostSave(String userId) {
		session.delete("delectExpiredPostSave", userId);
	}
	
	
}// end class
