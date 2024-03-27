package com.moonBam.controller.member.OpenApi;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.controller.member.DebugBoardController;
import com.moonBam.controller.member.SecurityController;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.OpenApiService;
import com.moonBam.service.member.RegisterService;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
public class GoogleLoginController {

    @Value("${google.auth.url}")
    private String googleAuthUrl;

    @Value("${google.login.url}")
    private String googleLoginUrl;

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.redirect.url}")
    private String googleRedirectUrl;

    @Value("${google.secret}")
    private String googleClientSecret;
    
    @Autowired
    OpenApiService serv;
    
    @Autowired
    RegisterService rServ;
    
    @Autowired
    DebugBoardController dbc;
    
    @Autowired
    SecurityController sc;

    // 구글 로그인창 호출
    @GetMapping(value = "Login/getGoogleAuthUrl")
    public ResponseEntity<?> getGoogleAuthUrl(HttpServletRequest request) throws Exception {

        String reqUrl = googleLoginUrl + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri=" + googleRedirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

        log.info("myLog-LoginUrl : {}",googleLoginUrl);
        log.info("myLog-ClientId : {}",googleClientId);
        log.info("myLog-RedirectUrl : {}",googleRedirectUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);	//로그인 후 /Login/google으로 리다이렉션
    }

    // 구글에서 리다이렉션
    @GetMapping(value = "Login/google")
    public ModelAndView oauth_google_check(HttpServletRequest request, @RequestParam(value = "code") String authCode, HttpServletResponse response, HttpSession session) throws Exception{
    	
    	//구글에 등록된 설정정보를 보내어 약속된 토큰을 받기 위한 객체 생성
    	GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
	        .builder()
	        .clientId(googleClientId)
	        .clientSecret(googleClientSecret)
	        .code(authCode)
	        .redirectUri(googleRedirectUrl)
	        .grantType("authorization_code")
	        .build();
        RestTemplate restTemplate = new RestTemplate();
        
        //토큰 요청
        ResponseEntity<GoogleLoginResponse> apiResponse = restTemplate.postForEntity(googleAuthUrl + "/token", googleOAuthRequest, GoogleLoginResponse.class); 	
        
        //받은 토큰을 토큰객체에 저장
        GoogleLoginResponse googleLoginResponse = apiResponse.getBody();
        log.info("responseBody {}",googleLoginResponse.toString());
        String googleToken = googleLoginResponse.getId_token();

        //받은 토큰을 구글에 보내 유저정보를 얻음
        String requestUrl = UriComponentsBuilder.fromHttpUrl(googleAuthUrl + "/tokeninfo").queryParam("id_token",googleToken).toUriString();

        //허가된 토큰의 유저정보를 결과로 받음
        String resultJson = restTemplate.getForObject(requestUrl, String.class);

        //받은 JSON데이터를 사용할 Map데이터로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String resultString = objectMapper.writeValueAsString(resultJson);
        Map<String, Object> map = objectMapper.readValue(resultJson, Map.class);
        
        //이미 가입한 사람인지 확인
        MemberDTO check  = serv.selectOneAPIMember(sc.encrypt((String) map.get("sub")));
        ModelAndView mav = new ModelAndView();
      
        //미가입자일 경우, 자동 가입
        if(check == null) {
    	  
		//MemberDTO 사용을 위한 임의의 값 입력
		String pw = sc.encrypt("Google"+dbc.getNum(16));
		String[] emailParts = ((String) map.get("email")).split("@");
		Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String userSignDate = dateFormat.format(currentDate);
		  
		MemberDTO dto = new MemberDTO();
      		dto.setUserId(sc.encrypt((String) map.get("sub")));
          	dto.setUserPw(pw);					
          	dto.setUserName(((String) map.get("name")).replace(" ", ""));
          	dto.setNickname(dbc.getNum(10)+"-G");
          	dto.setUserEmailId(emailParts[0]);			
          	dto.setUserEmailDomain(emailParts[1]);				
          	dto.setUserSignDate(userSignDate);
  			dto.setUserType("1");
  		
  		//회원가입
  		serv.insertAPIMember(dto);	
    	
  		//닉네임 변경하는 화면으로 이동
  		MemberDTO nDTO  = serv.selectOneAPIMember(dto.getUserId());
  		session.setAttribute("loginUser", nDTO);
  		mav.addObject("dto", nDTO);
	    mav.setViewName("member/Login/APILogin");
	    return mav; 
        
      //가입자일 경우, 메인으로 이동
      } else {
	    session.setAttribute("loginUser", check);
        mav.setViewName("redirect:/");
        return mav;
      }
        
    }
    
}