package com.moonBam.service;

import com.moonBam.dao.ReviewDAO;
import com.moonBam.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ReviewService {

	@Autowired
	ReviewDAO dao;
	
	public ReviewDTO writeReview(ReviewDTO review) {
		review = dao.writeReview(review);
		return review;
	}

	public ReviewDTO SelectReviewByUser(HashMap<String, String> map) {
		ReviewDTO review = dao.SelectReviewByUser(map);
		return review;
	}

	public int UpdateScore(RateDTO dto) {
		int n = dao.UpdateScore(dto);
		return n;
	}

	public List<ReviewDTO> selectReviews(HashMap<String, String> map) {
		List<ReviewDTO> reviewList = dao.selectReviews(map);
		return reviewList;
	}

	public ContentDTO selectContent(String contId) {
		ContentDTO content = dao.selectContent(contId);
		
		return content;
	}

	public void UpdateLike(HashMap<String, String> map) {
		dao.UpdateLike(map);
	}

	public ReviewDTO selectReviewByPostId(String postId) {
		ReviewDTO review = dao.selectReviewByPostId(postId);
		return review;
	}

	public List<CommentDTO> selectComments(String postId) {
		List<CommentDTO> comments = dao.selectComments(postId);
		return comments;
	}

	public List<RateDTO> selectRates(String contId) {
		List<RateDTO> rateList = dao.selectRates(contId);

		return rateList;
	}

	public ReviewDTO selectReview(HashMap<String, String> map) {
		ReviewDTO review = dao.selectReview(map);
		return review;
	}

	public void reportReview(ReportDTO report) {
		dao.reportReview(report);
	}

	public ReviewPageDTO allReview(HashMap<String, String> map) {
		ReviewPageDTO rpDTO = dao.allReview(map);
		return rpDTO;
	}

	public void deleteReview(String postId) {
		dao.deleteReview(postId);
	}

	public ReviewDTO myReview(HashMap<String, String> map) {
		return dao.myReview(map);
	}

}
