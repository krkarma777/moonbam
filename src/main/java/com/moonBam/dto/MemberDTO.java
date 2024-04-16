package com.moonBam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
@Getter
@Setter
@ToString
public class MemberDTO{
	private String userId;
	private String userPw;
	private String nickname;
	private String secretCode ;
	private int googleConnected;
	private int naverConnected;
	private int kakaoConnected;
	private String userSignDate;
	private String role;
	private boolean enabled;
	
	public MemberDTO() {
		super();
	}
	public MemberDTO(String userId, String userPw, String nickname, String secretCode, int googleConnected,
			int naverConnected, int kakaoConnected, String userSignDate, String role, boolean enabled) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.nickname = nickname;
		this.secretCode = secretCode;
		this.googleConnected = googleConnected;
		this.naverConnected = naverConnected;
		this.kakaoConnected = kakaoConnected;
		this.userSignDate = userSignDate;
		this.role = role;
		this.enabled = enabled;
	}
}
