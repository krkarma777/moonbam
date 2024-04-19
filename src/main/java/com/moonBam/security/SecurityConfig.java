//package com.moonBam.security_구;
//
//
//import com.moonBam.security.handler.CustomAuthenticationSuccessHandler;
//import com.moonBam.security_구.jwt.JWTFilter;
//import com.moonBam.security_구.jwt.JWTUtil;
//import com.moonBam.security_구.jwt.LoginFilter;
//import com.moonBam.security_구.service.social.CustomOAuth2UserService;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final AuthenticationConfiguration authenticationConfiguration;
//    private final JWTUtil jwtUtil;
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//    @Value("${jwt.expiredMs}") String expiredMs;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
////        http
////                .authorizeHttpRequests(
////                        (authorize) -> authorize
////                                .requestMatchers("/AdminPage/**").hasRole("ADMIN")
////                                .anyRequest().permitAll()
////                );
////
////        http
////                .oauth2Login(oauth2 -> oauth2
////                        .redirectionEndpoint(endpoint -> endpoint.baseUri("/login/oauth2/code/**"))
////                        .userInfoEndpoint(endpoint -> endpoint.userService(customOAuth2UserService))
////                        .successHandler(customAuthenticationSuccessHandler)
////                );
//
////        http
////                .logout((logoutConfig) ->
////                        logoutConfig
////                                .logoutUrl("/logout")
////                                .invalidateHttpSession(true)
////                                .deleteCookies("AuthToken")
////                                .logoutSuccessUrl("/")
////                                .permitAll());
////        //csrf disable
//        http
//                .csrf(AbstractHttpConfigurer::disable);
//
//        //Form 로그인 방식 disable
//        http
//                .formLogin(AbstractHttpConfigurer::disable);
//
//        //http basic 인증 방식 disable
//        http
//                .httpBasic(AbstractHttpConfigurer::disable);
//
//        http
//                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
//
//        http
//                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, Long.parseLong(expiredMs)), UsernamePasswordAuthenticationFilter.class);
//
//        //세션 설정
//        http
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
//
//}