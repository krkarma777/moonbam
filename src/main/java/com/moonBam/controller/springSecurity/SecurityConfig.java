package com.moonBam.controller.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer.UserInfoEndpointConfig;
import org.springframework.security.config.web.servlet.session.SessionFixationDsl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails.UserInfoEndpoint;
import org.springframework.security.web.SecurityFilterChain;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig { // WebSecurityConfigurerAdapter는 securityFilterChain과 동시 사용 불가

	@Autowired
	private SpringSecurityService springSecurityService;
	
	private final OAuthService oAuthService;
	
	public SecurityConfig(OAuthService oAuthService) {
		this.oAuthService = oAuthService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//   @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
   
   @Bean
   public RoleHierarchy roleHierarchy() {						//계층형 권한 부여
	   RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
	   	hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MEMBER");		//ADMIN:	MEMBER의 모든 권한 + ADMIN 고유의 권한
	   return hierarchy;
   }
		
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		security.httpBasic().disable();
		
										//configure는 Override의 기본값
		
		security.csrf().disable();		//csrf:	요청을 위조하여 사용자가 원하지 않아도 서버 측으로 특정 요청을 강제로 보내는 방식
										//		회원 정보 변경 / 게시글 CRUD를 사용자 모르게 요청
										//spring.mustache.servlet.expose-request-attributes=true
										//POST요청:	Form에서 <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
										//AJAX요청:	HTML <head> 아래에 <meta name="_csrf_header" content="{{_csrf.headerName}}"/>
		
		//권한따라 허용되는 url 설정
		security.authorizeHttpRequests().antMatchers("/").permitAll();
//		security.authorizeHttpRequests().antMatchers("/memberList").hasRole("ADMIN");		//ROLE은 무조건 // ADMIN은 관례
//		security.authorizeHttpRequests().anyRequest().permitAll();
		
		//로그인 설정
		security.formLogin().loginPage("/Login")
				.loginProcessingUrl("/loginProc")			//	/loginProc으로 Mapping
															//	자동으로 @Service가 붙은 Service 중에 loadUserByUsername가 있는 곳을 찾아가서 실행
															//	jsp에서 Method="POST", Action="/loginProc"으로 처리
				.usernameParameter("userId")				//	username Param을 userId로 사용 가능
				.passwordParameter("userPw")				//	password Param을 userPw로 사용 가능
//				.successHandler								//	로그인 성공 시, 작동(세션이랑 쿠키 작동 예정)
//              .failureHandler								//	로그인 실패 시, 작동
				.defaultSuccessUrl("/", true);				//	로그인 성공 시, 이동하는 URL

		
//		security.oauth2Login((oauth2) -> oauth2				//	Customizer.withDefaults()면 기본값
//					.userInfoEndpoint((UserInfoEndpointConfig) -> UserInfoEndpointConfig
//					.userService(oAuthService)));

		security.oauth2Login(oauth2 -> oauth2
				.loginPage("/")
				.userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig.userService(oAuthService)));

		
		
		
				//세션관리
		security.sessionManagement()						//**************작동 안 됨???????????????????????????/
				.maximumSessions(1)							//	최대 동시 접속 1개ㅑ
				.maxSessionsPreventsLogin(true);			//	True:	동시 접속 시 새로운 로그인 차단

		security.sessionManagement().sessionFixation().changeSessionId();	//로그인 시 동일한 세션에 대한 id 변경
		
		//로그인 실패
		security.exceptionHandling().accessDeniedPage("/NotAuthentic");	//권한 없으면 가는 페이지

		//로그아웃
		security.logout().logoutUrl("/Logout").logoutSuccessUrl("/").invalidateHttpSession(true);
		//쿠키(토큰)/세션 삭제도 이곳에서 진행?
		
		
		
		security.userDetailsService(springSecurityService);
		
		return security.build();
	}
	
    public void configure(WebSecurity web) throws Exception {
    		web.ignoring().antMatchers("/static/**");
    }
}

