package com.moonBam.dao;


import com.moonBam.dto.*;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ReviewDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
public ReviewDTO writeReview(ReviewDTO review) {
		
		// 이미 작성한 리뷰가 있는지 검사 (있으면 1 반환)
		int count = session.selectOne("check", review);
		//System.out.println(count);
		int num = 0;
		if(count>0) {
			//update 갱신
			num = session.insert("updateReview", review);
		}else {
			//insert 최초생성
			num = session.insert("writeReview", review);
			//별점데이터 생성
			session.insert("createRateData", review);
		}
		
		// 변경된 값 반환
		Long postId = review.getPostId();
		review = session.selectOne("selectReview", postId);
		return review;
	}

	public ReviewDTO SelectReviewByUser(HashMap<String, String> map) {
		ReviewDTO review = session.selectOne("selectReviewByUser", map);
		//Integer score = session.selectOne("selectScore", map);
		//HashMap<String, Object> result = new HashMap<String, Object>();
		//result.put("review", review);
		//result.put("score", score);
		
		//system.out.println(review);
		return review;
	}

	public int UpdateScore(RateDTO dto) {
		int n = session.update("insertOrUpdateRating", dto);
		return n;
	}

	public List<ReviewDTO> selectReviews(HashMap<String, String> map) {
		List<ReviewDTO> reviewList = session.selectList("selectReviews", map);
		//System.out.println("dao " + reviewList + contId);
		//System.out.println("dao, selectReviews: " + reviewList);
		return reviewList;
	}

	public ContentDTO selectContent(String contId) {
		ContentDTO content = session.selectOne("selectContent", contId);
		return content;
	}

	public void UpdateLike(HashMap<String, String> map) {
		//System.out.println("dao: "+map);
		int num = session.update("UpdateLike", map);
		//System.out.println("dao: "+num);
		
	}

	public ReviewDTO selectReviewByPostId(String postId) {
		ReviewDTO review = session.selectOne("selectReviewByPostId", postId);
		return review;
	}

	public List<CommentDTO> selectComments(String postId) {
		List<CommentDTO> comments = session.selectList("selectComments", Integer.parseInt(postId));
		return comments;
	}

	public List<RateDTO> selectRates(String contId) {
		List<RateDTO> rateList = session.selectList("selectRates", contId);
		return rateList;
	}

	public ReviewDTO selectReview(HashMap<String, String> map) {
		ReviewDTO review = session.selectOne("selectReviewMore", map);
		return review;
	}

	public void reportReview(ReportDTO report) {
		int num = session.update("reportReview", report);
	}

	public ReviewPageDTO allReview(HashMap<String, String> map) {
		ReviewPageDTO rpDTO = new ReviewPageDTO();
		int perPage = rpDTO.getPerPage();
		int offset = (Integer.parseInt(map.get("curPage"))-1)*perPage;
		
		List<ReviewDTO> reviewList = session.selectList("allReview", map, new RowBounds(offset, perPage));
		
		rpDTO.setCurPage(Integer.parseInt(map.get("curPage")));
		rpDTO.setList(reviewList);
		rpDTO.setTotalCount(totalCount(session, map));
		
		return rpDTO;
	}
	
	private int totalCount(SqlSessionTemplate session, HashMap<String, String> map) {
		return session.selectOne("reviewTotalCount", map);
	}

	public void deleteReview(String postId) {
		session.delete("deleteReview", postId);
	}

	public ReviewDTO myReview(HashMap<String, String> map) {
		return session.selectOne("myReview", map);
	}

}