package com.moonBam.security.service.social;

import java.util.Map;

public interface SocialOauth2Service {

    String login(Map<String, Object> attributes);
}
