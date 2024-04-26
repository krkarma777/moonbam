package com.moonBam.dto.member;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RestoreRestrictedMember")
public class RestoreRestrictedMember {

    private String userId;
    private boolean isEnabled;
    private String state;

    public RestoreRestrictedMember(String userId, boolean isEnabled, String state) {
        this.userId = userId;
        this.isEnabled = isEnabled;
        this.state = state;
    }

}
