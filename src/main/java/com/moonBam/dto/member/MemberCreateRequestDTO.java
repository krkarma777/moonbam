package com.moonBam.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberCreateRequestDTO {
    private String userId;
    private String userPw;
    private String nickname;
    private String restoreUserEmailId;
    private String restoreUserEmailDomain;
    private String userType = "ROLE_USER";
}
