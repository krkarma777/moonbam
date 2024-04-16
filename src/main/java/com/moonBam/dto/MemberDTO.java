package com.moonBam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.UUID;

@Alias("MemberDTO")
@Getter
@Setter
@ToString
public class MemberDTO{
	private String userId;
	private String userPw;
	private String nickname;
	private String secretCode = UUID.randomUUID().toString();
	private int googleConnected;
	private int naverConnected;
	private int kakaoConnected;
	private String userSignDate;
	private String role;
	private String enabled;
}
