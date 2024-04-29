//package com.moonBam.controller.community.chat;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Controller
//public class TestController {
//
//	@RequestMapping("test1")
//	@ResponseBody
//	public void test1(HttpServletRequest req) {
//		System.out.println(req.getAttribute("test"));
//	}
//	
//	@RequestMapping("test2")
//	public String test2(HttpServletRequest req) {
//		req.setAttribute("test", "홍길동");
//		return "forward:/test1";
//	}
//}
