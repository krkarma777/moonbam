package com.moonBam.controller.member.OpenApi;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.controller.member.AnonymousBoardController;
import com.moonBam.controller.member.SecurityController;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.OpenApiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class KakaoLoginController {

    @Value("${client_id}")
    private String client_id;

    @Value("${redirect_url}")
    private String redirect_url;
    
    @Value("${secret_code}")
    private String secret_code;
    
    @Autowired
    OpenApiService serv;
    
    @Autowired
    AnonymousBoardController dbc;
    
    @Autowired
    SecurityController sc;
    
    @Autowired
    OpenApiController oac;

    // 카카오에서 토큰 받아오기
    @GetMapping("/getKakaoAuthUrl")
    public ResponseEntity<Object> getKakaoAuthUrl() throws URISyntaxException {
    	String reqUrl = "https://kauth.kakao.com/oauth/authorize?client_id="+client_id+"&redirect_uri="+redirect_url+"&response_type=code";
        URI redirectUri = new URI(reqUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
    
    // 카카오에서 리다이렉션
    @RequestMapping(value = "kakaoLogin")
    public ModelAndView getKakaoCode(String code, HttpSession session) throws IOException{
    	//1. 받은 인가 코드 받기
    	//System.out.println(code);
    	
    	//2. 코드를 통해 토큰 받기
    	String accessToken = getAccessToken(code);
    	
    	//3. 사용자 정보 받기
    	Map<String, Object> map = getUserInfo(accessToken);
    	ObjectMapper objectMapper = new ObjectMapper();
    	//log.info("카카오 로그인 시 서버에서 받아오는 유저 정보: "+ map);

    	//아이디
        String jsonString2 = objectMapper.writeValueAsString(map.get("kakao_account"));
        JsonNode jsonNode2 = objectMapper.readTree(jsonString2);
        String id = jsonNode2.get("email").asText();
    	
    	//이미 가입한 사람인지 확인
        MemberDTO check  = serv.selectOneAPIMember(id);
        
        //기가입자이지만, 카카오 연동이 안된 사람을 연동
        if(check != null && check.getKakaoConnected() == 0) {
        	serv.updateAPIMemberKakaoConnected(check.getUserId());
        }
        
        ModelAndView mav = new ModelAndView();
      
        //미가입자일 경우, 자동 가입
        if(check == null) {
        	
            //비밀번호
            String pw = sc.encrypt("Kakao"+dbc.getNum(16));

            //닉네임
            String nickname = oac.randomNickname();
            		
    		MemberDTO dto = new MemberDTO();
	    		dto.setUserId(id);
	          	dto.setUserPw(pw);					
	          	dto.setNickname(nickname);
	          	dto.setKakaoConnected(1);
        	
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
    
    
    // 카카오에서 액세스 토큰 받아오기    
    public String getAccessToken(String code) throws IOException {
        String accessToken = "";
        String refreshToken = "";
        String reqUrl = "https://kauth.kakao.com/oauth/token";

            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true); 

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(client_id);
            sb.append("&redirect_uri=").append(redirect_url);
            sb.append("&code=").append(code);
            sb.append("&client_secret=").append(secret_code);		

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            log.info("[KakaoApi.getAccessToken] responseCode = {}", responseCode);

            System.out.println(responseCode);
            
            BufferedReader br;
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line = "";
            StringBuilder responseSb = new StringBuilder();
            while((line = br.readLine()) != null){
                responseSb.append(line);
            }
            //System.out.println("responseSb: "+responseSb);			//카카오에서 받아오는 JSON데이터
            String resultJson = responseSb.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            String resultString = objectMapper.writeValueAsString(resultJson);
            Map<String, Object> map = objectMapper.readValue(resultJson, Map.class);
            String result = (String) map.get("access_token");   
            
            br.close();
            bw.close();
            
            return result;
    }
    
    // 카카오에서 액세스 토큰에서 유저 데이터 받아오기
    public Map<String, Object> getUserInfo(String accessToken) throws IOException {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = conn.getResponseCode();
            log.info("[KakaoApi.getUserInfo] responseCode : {}",  responseCode);

            BufferedReader br;
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line = "";
            StringBuilder responseSb = new StringBuilder();
            while((line = br.readLine()) != null){
                responseSb.append(line);
            }
            String resultJson = responseSb.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            String resultString = objectMapper.writeValueAsString(resultJson);
            Map<String, Object> map = objectMapper.readValue(resultJson, Map.class);
            
            br.close();

          return map;
    }
    
    
}