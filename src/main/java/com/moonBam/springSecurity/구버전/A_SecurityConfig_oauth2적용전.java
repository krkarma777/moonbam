//package com.moonBam.springSecurity.구버전;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.moonBam.springSecurity.SpringSecurityService;
//import com.moonBam.springSecurity.oauth2.OAuthService;
//
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig_oauth2적용전{ // WebSecurityConfigurerAdapter는 securityFilterChain과 동시 사용 불가
//
//	@Autowired
//	private SpringSecurityService springSecurityService;
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
//	
//   @Bean
//   public RoleHierarchy roleHierarchy() {						//계층형 권한 부여
//	   RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
//	   	hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MEMBER");		//ADMIN:	MEMBER의 모든 권한 + ADMIN 고유의 권한
//	   return hierarchy;
//   }
//		
//	@Bean  //configure는 Override의 기본값(SecurityFilterChain를 사용할 때는 메서드 명칭 변경)
//	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//		
//		security.httpBasic().disable();	//사용자가 서비스에 접근할 때 사용자 이름과 비밀번호를 HTTP 요청 헤더에 인코딩하여 보내는 간단한 인증 방식
//										//보안에 취약하며, 사용자 이름과 비밀번호를 암호화하지 않기 때문에 안전하지 않음
//		
//		security.csrf().disable();		//csrf:		요청을 위조하여 사용자가 원하지 않아도 서버 측으로 특정 요청을 강제로 보내는 방식
//										//			회원 정보 변경 / 게시글 CRUD를 사용자 모르게 요청할 수 있음
//										
//		//권한따라 허용되는 url 설정
//		security.authorizeHttpRequests().antMatchers("/").permitAll();
////		security.authorizeHttpRequests().antMatchers("/memberList").hasRole("ADMIN");		//ROLE은 무조건 // ADMIN은 관례
//		
//		//로그인 설정
//		security.formLogin().loginPage("/mainLogin")
//				.loginProcessingUrl("/loginProc")			//	/loginProc으로 Mapping
//															//	자동으로 @Service가 붙은 Service 중에 loadUserByUsername가 있는 곳을 찾아가서 실행
//															//	jsp에서 Method="POST", Action="/loginProc"으로 처리
//				.usernameParameter("userId")				//	username Param을 userId로 사용 가능
//				.passwordParameter("userPw")				//	password Param을 userPw로 사용 가능
//				.successHandler(new LoginSuccessHandler())	//	로그인 성공 시, 작동(세션이랑 쿠키 작동 예정)
//				.failureHandler(new LoginfailureHandler());	//	로그인 실패 시, 작동
//
//		//세션관리
//		security.sessionManagement()
//				.maximumSessions(1)											//	최대 동시 접속 1개
//				.maxSessionsPreventsLogin(true);							//	True:	동시 접속 시 새로운 로그인 차단
//		
//		security.sessionManagement().sessionFixation().changeSessionId();	//로그인 시 동일한 세션에 대한 id 변경
//		
//		//로그인 실패
//		security.exceptionHandling().accessDeniedPage("/NotAuthentic");		//권한 없으면 가는 페이지
//
//		//로그아웃
//		security.logout().logoutUrl("/Logout").logoutSuccessUrl("/").invalidateHttpSession(true);
//		
//		security.userDetailsService(springSecurityService);
//		
//		return security.build();
//	}
//	
//    public void configure(WebSecurity web) throws Exception {
//    		web.ignoring().antMatchers("/static/**");
//    }
//}