package com.moonBam.springSecurity.oauth2;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {

	private final Map<String, Object> attribute;
	
	public GoogleResponse(Map<String, Object> attribute) {
		this.attribute = attribute;
	}

	@Override
	public String getProvider() {
		return "google";
	}

	@Override
	public String getEmail() {
		return attribute.get("email").toString();
	}

	@Override
	public String getName() {
		return attribute.get("name").toString();
	}

}
