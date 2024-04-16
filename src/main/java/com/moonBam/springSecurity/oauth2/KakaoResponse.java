package com.moonBam.springSecurity.oauth2;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {
    
    private Map<String, Object> attribute;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attribute = attributes;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attribute.get("properties");
        return (String) properties.get("nickname");
    }

}
