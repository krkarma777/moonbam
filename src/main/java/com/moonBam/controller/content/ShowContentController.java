package com.moonBam.controller.content;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.moonBam.service.member.MemberLoginService;
import jakarta.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.CreditDTO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.RateDTO;
import com.moonBam.dto.ReviewDTO;
import com.moonBam.service.ReviewService;
import com.moonBam.service.TmdbApiService;

@Controller
public class ShowContentController {
	
	@Autowired
	ReviewService service;
	@Autowired
	MemberLoginService memberLoginService;
	@Autowired
	TmdbApiService tmdbApiService;
	
	@Autowired
	KoficAPI kofic;
	//영화 진흥위원회 키
	@Value("${kofic.key}")
	private String key;
	
	@RequestMapping("/saveMovie")
	@ResponseBody
	public String movieSave(int curPage) {
		
		kofic.saveMovie(curPage);
		
		return "success";
	}
	
	// 컨텐츠아이디 받음
	// 컨텐츠 데이터, 컨텐츠에 해당하는 리뷰들 얻어서 응답
	@RequestMapping("/content-page")
	public String contentPage(String contId, Principal principal, HttpServletRequest request) {


		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
		String likeUserId = null;
		if(loginUser!=null) {
			// 자신이 누른좋아요 정보 가져오기 위해 본인의 유저아이디 저장
			likeUserId= loginUser.getUserId();
			request.setAttribute("member", loginUser);
		}

//		//임시 컨텐츠 데이터 생성 (나중에 삭제)
//		if(contId==null) {
//			contId = "1";
//		}
		
		String nextPage = "";
		// 예외처리 : contId 부재시
		if(contId==null) {
			// 영화홈 이동
			nextPage = "main?cg=movie";
		} else {
			// DB에서 컨텐츠정보 가져오기
			ContentDTO content = service.selectContent(contId);
			request.setAttribute("content", content);

			// DB에서 컨텐츠에 해당하는 리뷰리스트 가져와서 전달
			// 최신순으로 8개 select
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("contId", contId);
			map.put("likeUserId", likeUserId);//페이지 사용중인 유저id (각 리뷰에 좋아요 눌렀는지 불러오기 위하여 전달)
			List<ReviewDTO> reviewList = service.selectReviews(map);
			request.setAttribute("reviewList", reviewList);

			// 별점 리스트 가져와서 전달 (평균별점 계산용)
			List<RateDTO> rateList = service.selectRates(contId);
			request.setAttribute("rateList", rateList);
			
			nextPage = "content/contentViewer";
		}

		return nextPage;
	}
	
	@RequestMapping("/showContent")
	public String showContent(String contId, Model model, Principal principal, HttpServletRequest request) {
		ContentDTO content = service.selectContent(contId);
		model.addAttribute("content", content);

		MemberDTO loginUser = memberLoginService.findByPrincipal(principal);
		String likeUserId = null;
		if(loginUser!=null) {
			// 자신이 누른좋아요 정보 가져오기 위해 본인의 유저아이디 저장
			likeUserId= loginUser.getUserId();
			request.setAttribute("member", loginUser);
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("contId", contId);
		map.put("likeUserId", likeUserId);

		List<ReviewDTO> reviewList = service.selectReviews(map);
		model.addAttribute("reviewList", reviewList);

		List<CreditDTO> creditList = tmdbApiService.getCredits(contId);
		int creditListSize = creditList.size();
		if(!((creditListSize%6)==0)) {
			int num = 6-(creditListSize%6);
			for(int i=0; i<num; i++) {
				creditList.add(creditListSize, null);
				creditListSize++;
			}
		}
		model.addAttribute("creditList", creditList);
		
		return "content/showContent";
	}
	
	@RequestMapping("/movieSearch")
	@ResponseBody
	public String showContent(Model model, String curPage, String repNationCd) {
		System.out.println("in ShowContentController allMovie()");
		
//		List<JSONObject> allMovieList = kofic.allMovieList(curPage);
//		model.addAttribute("allmovieList", allMovieList);
		
		return "movie/allMovie";
	}
	
}
