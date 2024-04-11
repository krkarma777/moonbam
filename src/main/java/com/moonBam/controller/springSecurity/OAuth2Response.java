package com.moonBam.controller.springSecurity;

public interface OAuth2Response {

	String getProvider();			//제공자 이름(naver / google / kakao)
	String getProviderId();			
	String getEmail();
	String getName();
	
}
