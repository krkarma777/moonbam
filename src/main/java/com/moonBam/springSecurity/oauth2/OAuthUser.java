package com.moonBam.springSecurity.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuthUser implements OAuth2User {

	private final OAuth2Response oAuth2Response;
	
	private final String role;

	public OAuthUser(OAuth2Response oAuth2Response, String role) {
		this.oAuth2Response = oAuth2Response;
		this.role = role;
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
