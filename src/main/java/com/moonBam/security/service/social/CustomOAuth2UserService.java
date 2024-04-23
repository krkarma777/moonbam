//package com.moonBam.security_구.service.social;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import com.moonBam.security_구.model.social.GoogleOAuth2User;
//import com.moonBam.security_구.model.social.KakaoOAuth2User;
//import com.moonBam.security_구.model.social.NaverOAuth2User;
//
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    private final KakaoLoginService kakaoLoginService;
//    private final NaverLoginService naverLoginService;
//    private final GoogleLoginService googleLoginService;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User user = super.loadUser(userRequest);
//        Map<String, Object> attributes = user.getAttributes();
//
//        /* 네이버 로그인 로직 */
//        if (attributes.get("message") != null) {
//            String token = naverLoginService.login(attributes);
//            return new NaverOAuth2User(user, token);
//        }
//        /* 카카오 로그인 로직 */
//        if (attributes.get("kakao_account") != null) {
//            String token = kakaoLoginService.login(attributes);
//            return new KakaoOAuth2User(user, token);
//        }
//
//        /* 구글 로그인 로직 */
//        if (attributes.get("sub") != null) {
//            String token = googleLoginService.login(attributes);
//            return new GoogleOAuth2User(user, token);
//        }
//
//        return null;
//    }
//}