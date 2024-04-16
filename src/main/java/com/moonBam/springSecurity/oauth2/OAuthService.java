package com.moonBam.springSecurity.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.moonBam.controller.member.AnonymousBoardController;
import com.moonBam.controller.member.MailController;
import com.moonBam.controller.member.OpenApiController;
import com.moonBam.controller.member.SecurityController;
import com.moonBam.dao.member.LoginDAO;
import com.moonBam.dao.member.OpenApiDAO;
import com.moonBam.dto.MemberDTO;
import com.moonBam.service.member.OpenApiService;

@Service
public class OAuthService extends DefaultOAuth2UserService {

    @Autowired
    OpenApiService oas;
    
    @Autowired
    OpenApiDAO oad;
    
    @Autowired
	MailController mc;
	
	@Autowired
	LoginDAO dao;
	
	@Autowired
	OpenApiController oac;
	
	@Autowired
	SecurityController sc;
	
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    	
    	//소셜에서 JSON데이터 받아오기
    	OAuth2User oAuth2User = super.loadUser(userRequest);
    	
    	//소셜에서 받아오는 JSON데이터 확인
    	System.out.println("oAuth2User: " + oAuth2User.getAttributes());

    	//등록을 대비하여 미리 선언
    	MemberDTO register = new MemberDTO();
    	
    	//어떤 인증 Provider인지 확인
    	String registrationId = userRequest.getClientRegistration().getRegistrationId();
    	
    	//어디 소셜인지 확인
    	System.out.println("registrationId: " + registrationId);
    	
    	OAuth2Response oAuth2Response = null;
    	
    	//소셜에 따라 다른 방법으로 데이터 추출
        if(registrationId.equals("naver")){
        	oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else
        
        if(registrationId.equals("google")){
        	oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else

        if(registrationId.equals("kakao")){
        	oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        
        //유저 아이디를 JSON데이터에서 추출
        String userId = oAuth2Response.getEmail();
        
        //유저 아이디가 DB에 있는 것인지 검증
        MemberDTO dto = dao.userDetail(userId);
        System.out.println("dto: "+dto);
        
        String role = "";
        
        if(dto == null) {
	    		register.setUserId(userId);
	    		register.setUserPw(sc.encrypt(AnonymousBoardController.getNum(16)));
	    		register.setNickname(oac.randomNickname());
	    		register.setSecretCode(sc.encrypt(AnonymousBoardController.getNum(8)));
	    		
	    		// 소셜에 따라 연동 체크
	    		if(registrationId.equals("naver")){
	            	register.setNaverConnected(1);
	            } else
	            
	            if(registrationId.equals("google")){
	            	register.setGoogleConnected(1);
	            } else

	            if(registrationId.equals("kakao")){
	            	register.setKakaoConnected(1);
	            }

	    		//회원가입
	    		oas.insertAPIMember(register);	
				
	    		//회원가입 메일 송신
	    		try {
					mc.RegisterCompleteEmail(register.getUserId(), register.getNickname(), register.getSecretCode());
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	
	    		//새로 회원가입한 유저의 Role 전송(기본값: MEMBER)
				role = "ROLE_MEMBER";
        }
        
        //이미 가입한 유저일 경우
        if(dto != null) {
        	
        	System.out.println("기존유저1: "+dto);
        	
        	// 소셜에 따라 추가 연동 체크
    		if(registrationId.equals("naver")){
    			oad.updateAPIMemberNaverConnected(dto.getUserId());
            } else
            
            if(registrationId.equals("google")){
            	oad.updateAPIMemberGoogleConnected(dto.getUserId());
            } else

            if(registrationId.equals("kakao")){
            	oad.updateAPIMemberKakaoConnected(dto.getUserId());
            }
    		
    		System.out.println("기존유저2: "+dto);
    		
    		//기존 유저의 Role 전송
    		role = dto.getRole();
        }
    	
    	return new OAuthUser(oAuth2Response, role);
    }
}