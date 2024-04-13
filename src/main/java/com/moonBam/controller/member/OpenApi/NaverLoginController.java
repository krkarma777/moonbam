package com.moonBam.controller.member.OpenApi;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.controller.member.AnonymousBoardController;
import com.moonBam.controller.member.SecurityController;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.OpenApiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class NaverLoginController {
//
//    @Value("${naver_client_id}")
//    private String client_id;
//
//    @Value("${naver_redirect_url}")
//    private String redirect_url;
//
//    @Value("${naver_client_secret}")
//    private String secret_code;
//
//    @Autowired
//    OpenApiService serv;
//
//    @Autowired
//    AnonymousBoardController dbc;
//
//    @Autowired
//    SecurityController sc;
//
//    @Autowired
//    OpenApiController oac;
//
//    // 네이버에서 토큰 받아오기
//    @GetMapping("/getNaverAuthUrl")
//    public ResponseEntity<Object> getNaverAuthUrl() throws URISyntaxException {
//    	SecureRandom random = new SecureRandom();
//    	String state = new BigInteger(130, random).toString();
//    	String reqUrl = "https://nid.naver.com/oauth2.0/authorize?client_id="+client_id+"&redirect_uri="+redirect_url+"&state="+state+"&response_type=code";
//        URI redirectUri = new URI(reqUrl);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(redirectUri);
//        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//    }
//
//    //네이버 로그인
//    @RequestMapping(value = "naverLogin")
//    public ModelAndView getNaverCode(String code, String error, String state, HttpSession session) throws IOException, URISyntaxException{
//	//  System.out.println("code: "+code);		//네이버 로그인 인증에 성공하면 반환받는 인증 코드
//	//  System.out.println("state: "+state);	//애플리케이션에서 생성한 상태 토큰
//	//  System.out.println("error: "+error); 	//네이버 로그인 인증에 실패하면 반환받는 에러 코드(성공 시에는 null값)
//
//	  	//access token 발급 받기
//		String accessToken =	getAccessToken(code, state);
//	//		System.out.println("accessToken: "+accessToken);
//		String userInfoJSON =	getUserInfo(accessToken);
//    //  	log.info("네이버 로그인 시 서버에서 받아오는 유저 정보: "+ userInfoJSON);
//
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		String resultString = objectMapper.writeValueAsString(userInfoJSON);
//        Map<String, Object> userInfoMap = objectMapper.readValue(userInfoJSON, Map.class);
//	//		System.out.println(map.get("response"));
//        String userInfoJSON2 = objectMapper.writeValueAsString(userInfoMap.get("response"));
//        Map<String, Object> userInfoMap2 = objectMapper.readValue(userInfoJSON2, Map.class);
//	//		System.out.println(userInfoMap2.get("id"));			//유저 아이디
//	//		System.out.println(userInfoMap2.get("email"));		//유저 이메일
//	//		System.out.println(userInfoMap2.get("name"));		//유저 이름
//
//
//        //아이디
//    	String id = (String) userInfoMap2.get("email");
//
//    	//이미 가입한 사람인지 확인
//        MemberDTO check  = serv.selectOneAPIMember(id);
//
//        if(check != null && check.getNaverConnected() == 0) {
//        	serv.updateAPIMemberNaverConnected(check.getUserId());
//        }
//
//        ModelAndView mav = new ModelAndView();
//
//        //미가입자일 경우, 자동 가입
//        if(check == null) {
//
//            //비밀번호
//            String pw = sc.encrypt("Naver"+dbc.getNum(16));
//
//            //닉네임
//            String nickname = oac.randomNickname();
//
//    		MemberDTO dto = new MemberDTO();
//	    		dto.setUserId(id);
//	          	dto.setUserPw(pw);
//	          	dto.setNickname(nickname);
//	          	dto.setNaverConnected(1);
//
//	  		//회원가입
//	  		serv.insertAPIMember(dto);
//
//	  		//닉네임 변경하는 화면으로 이동
//	  		MemberDTO nDTO  = serv.selectOneAPIMember(dto.getUserId());
//	  		session.setAttribute("loginUser", nDTO);
//	  		mav.addObject("dto", nDTO);
//		    mav.setViewName("member/Login/APILogin");
//		    return mav;
//
//	      //가입자일 경우, 메인으로 이동
//	      } else {
//		    session.setAttribute("loginUser", check);
//	        mav.setViewName("redirect:/");
//	        return mav;
//	      }
//    }
//
//    //네이버에서 access token으로 유저 데이터 받기
//    public String getUserInfo(String accessToken) {
//    	RestTemplate rt = new RestTemplate();
//    	HttpHeaders profileRequestHeader = new HttpHeaders();
//        profileRequestHeader.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<HttpHeaders> profileHttpEntity = new HttpEntity<>(profileRequestHeader);
//        ResponseEntity<String> profileResponse = rt.exchange(
//                "https://openapi.naver.com/v1/nid/me",
//                HttpMethod.POST,
//                profileHttpEntity,
//                String.class
//        );
//        return profileResponse.getBody();
//	}
//
//	//네이버에서 access token 발급 받기 함수
//    public String getAccessToken(String code, String state) throws JsonMappingException, JsonProcessingException {
//
//        RestTemplate rt = new RestTemplate();
//        HttpHeaders accessTokenHeaders = new HttpHeaders();
//        	accessTokenHeaders.add("Content-type", "application/x-www-form-urlencoded");
//        Map<String, String> accessTokenParams = new HashMap<>();
//	        accessTokenParams.put("grant_type", "authorization_code");
//	        accessTokenParams.put("client_id", client_id);
//	        accessTokenParams.put("client_secret", secret_code);
//	        accessTokenParams.put("code", code);    // 응답으로 받은 코드
//	        accessTokenParams.put("state", state); 	// 응답으로 받은 상태
//
//        StringBuilder paramsBuilder = new StringBuilder();
//        for (Map.Entry<String, String> entry : accessTokenParams.entrySet()) {
//            if (paramsBuilder.length() > 0) {
//                paramsBuilder.append("&");
//            }
//            paramsBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
//            paramsBuilder.append("=");
//            paramsBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
//        }
//
//        HttpEntity<String> accessTokenRequest = new HttpEntity<>(paramsBuilder.toString(), accessTokenHeaders);
//        ResponseEntity<String> accessTokenResponse
//        	= rt.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.POST, accessTokenRequest, String.class
//        );
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(accessTokenResponse.getBody());
//        String accessToken = jsonNode.get("access_token").asText();
//
//        return accessToken;
//    }
//
}    
