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

import com.moonBam.springSecurity.JWT.JWTFilter;
import com.moonBam.springSecurity.JWT.JWTUtil;
import com.moonBam.springSecurity.JWT.LoginFilter;
import com.moonBam.springSecurity.JWT.LoginSuccessHandler;
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
	
	@Value("${jwt.expiredMs}") String expiredMs;

	//AuthenticationManager Bean 등록
	@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
   @Bean
   public RoleHierarchy roleHierarchy() {						//계층형 권한 부여
	   RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
	   	hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MEMBER");		//ADMIN:	MEMBER의 모든 권한 + ADMIN 고유의 권한
	   return hierarchy;
   }
		
	@Bean  //configure는 Override의 기본값(SecurityFilterChain를 사용할 때는 메서드 명칭 변경)
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		security.httpBasic(AbstractHttpConfigurer::disable);
		
		security.csrf(AbstractHttpConfigurer::disable);
		
		//로그인 설정
		security.formLogin(AbstractHttpConfigurer::disable);
		security.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
		security.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, Long.parseLong(expiredMs)), UsernamePasswordAuthenticationFilter.class);

			//.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

		//소셜 로그인 설정
//		security.oauth2Login(Customizer.withDefaults());
		
		security.oauth2Login(oauth2 -> oauth2
				 .redirectionEndpoint(endpoint -> endpoint.baseUri("/login/oauth2/code/**"))
                 .userInfoEndpoint(endpoint -> endpoint.userService(oAuthService))
	//			.loginPage("/mainLogin")
				.successHandler(loginSuccessHandler)
				);

		//권한따라 허용되는 url 설정
		security.authorizeHttpRequests(
                (authorize) -> authorize
                .requestMatchers("/memberList", "/AdminPage/**").hasRole("ADMIN")
                .anyRequest().permitAll()
);
		
		//세션관리
		security.sessionManagement()
				.maximumSessions(1)											//	최대 동시 접속 1개
				.maxSessionsPreventsLogin(true);							//	True:	동시 접속 시 새로운 로그인 차단
		
		security.sessionManagement().sessionFixation().changeSessionId();	//로그인 시 동일한 세션에 대한 id 변경
		
		security.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));	//JWT를 통한 인증/인가를 위해서 세션을 STATELESS 상태로 설정
		
		//로그인 실패
		security.exceptionHandling().accessDeniedPage("/NotAuthentic");		//권한 없으면 가는 페이지

		//로그아웃
		security.logout((logoutConfig) -> logoutConfig
		        .logoutUrl("/logout")
		        .invalidateHttpSession(true)
		        .deleteCookies("AuthToken")
		        .logoutSuccessUrl("/")
		        .permitAll());
		
		return security.build();
	}
	
    public void configure(WebSecurity web) throws Exception {
    		web.ignoring().requestMatchers("/static/**");
    }
}

