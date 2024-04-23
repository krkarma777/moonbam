package com.moonBam.springSecurity.oauth2;

public interface OAuth2Response {

	String getProvider();			//제공자 이름(naver / google / kakao)
	String getEmail();
	String getName();
	
}
