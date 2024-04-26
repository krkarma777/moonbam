package com.moonBam.springSecurity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;

import com.moonBam.springSecurity.JWT.JWTFilter;
import com.moonBam.springSecurity.JWT.JWTUtil;
import com.moonBam.springSecurity.JWT.LoginFilter;
import com.moonBam.springSecurity.JWT.LoginSuccessHandler;
import com.moonBam.springSecurity.JWT.LoginfailureHandler;
import com.moonBam.springSecurity.oauth2.OAuthService;

import lombok.RequiredArgsConstructor;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig { // WebSecurityConfigurerAdapter는 securityFilterChain과 동시 사용 불가

	private final OAuthService oAuthService;
	private final AuthenticationConfiguration authenticationConfiguration;
	private final JWTUtil jwtUtil;
	private final LoginSuccessHandler loginSuccessHandler;
	private final LoginfailureHandler loginfailureHandler;
	
	//더블 슬래시(//) 에러 방지를 위한 커스텀 방화벽
	private final HttpFirewall customHttpFirewall;
	
	//기간 만료 설정
	@Value("${jwt.expiredMs}") String expiredMs;

	//AuthenticationManager
	@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	//스프링시큐리티 단방향 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

   //계층형 권한 부여
   @Bean
   public RoleHierarchy roleHierarchy() {						
	   RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
	   	hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MEMBER");		//ADMIN:	MEMBER의 모든 권한 + ADMIN 고유의 권한
	   return hierarchy;
   }
		
	@Bean  //configure는 Override의 기본값(SecurityFilterChain를 사용할 때는 메서드 명칭 변경)
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		//httpBasic 사용 중단
		security.httpBasic(AbstractHttpConfigurer::disable);
		
		//csrt 사용 중단
		security.csrf(AbstractHttpConfigurer::disable);
		
		//로그인 설정
		//로그인 기본설정 사용중단
		security
			.formLogin(formLogin -> formLogin
			.loginPage("/acorn/mainLogin"));
		//로그인 커스텀 설정 사용
		security.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
		security.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, Long.parseLong(expiredMs)), UsernamePasswordAuthenticationFilter.class);
			//.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

		//소셜 로그인 설정
		//소셜 로그인 기본설정
		// security.oauth2Login(Customizer.withDefaults());
		
		//소셜 로그인 커스텀 설정
		security.oauth2Login(oauth2 -> oauth2
				 .redirectionEndpoint(endpoint -> endpoint.baseUri("/login/oauth2/code/**"))
                 .userInfoEndpoint(endpoint -> endpoint.userService(oAuthService))
                 //기본 로그인 이동 설정 사용중단
                 //.loginPage("/mainLogin")
                 //JWT 사용을 위한 커스텀 로그인 성공/실패 설정 사용
				.successHandler(loginSuccessHandler)
				.failureHandler(loginfailureHandler)
				);

        //권한따라 허용되는 url 설정
        security.authorizeHttpRequests(
                (authorize) -> authorize
                //"/memberList", "/AdminPage/**" 주소는 DB role 컬럼의 데이터값이 ROLE_ADMIN인 사람만 사용 가능
        .requestMatchers(
            "/board/write/**", "/board/delete/**", "/board/edit/**", "/board/note/**", "/board/postLike/**"
            ).authenticated()
		.requestMatchers(
			"/memberList",
			"/chatRoom", "/chatRoom/enter", "/acorn/chatRoom/out",
			"/Chatmore", "/Chatmore/ChatmoreReport"
			).hasRole("MEMBER")
		.requestMatchers(
            "/AdminPage/**"
            ).hasRole("ADMIN")
        //그외 모든 요청은 모든 유저가 사용 가능
        .anyRequest().permitAll()
		);

        //세션관리
		//JWT를 통한 인증/인가를 위해서 세션을 STATELESS 상태로 설정
		//*****************************************************************************************************************************************
		security.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //페이지 접속할 때 해당 페이지에 대한 권한 없는 경우, 가는 페이지
        security.exceptionHandling(authenticationManager -> authenticationManager
				.accessDeniedPage("/NotAuthentic"));

        //로그아웃
        security.logout((logoutConfig) -> logoutConfig
                //jsp에서 로그아웃을 요청할 매핑주소
                .logoutUrl("/logout")
                //로그아웃 시 세션 삭제
                .invalidateHttpSession(true)
                //로그아웃 시 JWT인가가 저장된 쿠키 삭제
                .deleteCookies("AuthToken")
                //로그아웃 시 가는 페이지 설정
                .logoutSuccessUrl("/")
                .permitAll());

        return security.build();
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers("/static/**");
    }
}

