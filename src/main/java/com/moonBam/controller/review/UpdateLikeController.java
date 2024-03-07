	package com.moonBam.controller.review;


	import com.moonBam.dto.MemberDTO;
	import com.moonBam.service.ReviewService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;

	import javax.servlet.http.HttpSession;
	import java.util.HashMap;

	@Controller
public class UpdateLikeController {
	@Autowired
	ReviewService service;
	
	@RequestMapping(value="/like", method= RequestMethod.POST)
	@ResponseBody
	public void UpdateLike(@RequestParam HashMap<String, String> map, HttpSession session) {
		
		// 세션에서 로그인 정보 파싱
		MemberDTO login = (MemberDTO) session.getAttribute("loginUser");
		
		// 로그인 정보가 존재할 때
		if(login!=null) {
			service.UpdateLike(map);
		}
	}
}
