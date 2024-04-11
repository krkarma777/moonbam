package com.moonBam.controller.springSecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuthUser implements OAuth2User {

	private final OAuth2Response oAuth2Response;
	
	private final String role;

	private final String nickname;
	
	public OAuthUser(OAuth2Response oAuth2Response, String role, String nickname) {
		this.oAuth2Response = oAuth2Response;
		this.role = role;
		this.nickname = nickname;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> col = new ArrayList<>();
		col.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return role;
			}
		});
		return col;
	}

	@Override
	public String getName() {
		return oAuth2Response.getName();
	}
	
	public String getUsername() {
		return oAuth2Response.getEmail();
	}
	
	
	
}
