package com.moonBam.config;

import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.stereotype.Component;

@Component
public class CustomHttpFirewall extends StrictHttpFirewall {
    public CustomHttpFirewall() {
        super();
        // 더블 슬래시 허용 설정
        this.setAllowUrlEncodedDoubleSlash(true);
    }
}
