package com.moonBam.dto.member;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RestoreRestrictedMember")
public class RestoreRestrictedMember {

    private String userId;
    private boolean isEnabled;
    private String cause;

    public RestoreRestrictedMember(String userId, boolean isEnabled, String cause) {
        this.userId = userId;
        this.isEnabled = isEnabled;
        this.cause = cause;
    }

}
