package com.moonBam.controller.springSecurity;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.moonBam.controller.member.OpenApi.OpenApiController;

@Service
public class OAuthService extends DefaultOAuth2UserService {

	@Autowired
	SpringSecurityDAO dao;
	
	@Autowired
	OpenApiController oac;
	
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    	
    	OAuth2User oAuth2User = super.loadUser(userRequest);
    	System.out.println("oAuth2User: " + oAuth2User.getAttributes());
    	
    	//어떤 인증 Provider인지 확인
    	String registrationId = userRequest.getClientRegistration().getRegistrationId();
    	System.out.println("registrationId: " + registrationId);
    	
    	OAuth2Response oAuth2Response = null;
        if(registrationId.equals("naver")){
        	oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else
        
        if(registrationId.equals("google")){
        	oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else

        if(registrationId.equals("kakao")){
        	oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else {
        	return null;
    	}	
    
    	String role = "ROLE_USER";
    	
    	String nickname = oac.randomNickname();
    	
    	return new OAuthUser(oAuth2Response, role, nickname);
    }
}