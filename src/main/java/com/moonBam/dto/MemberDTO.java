package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Alias("MemberDTO")
public class MemberDTO{
	private String userId;
	private String password;
	private String nickname;
	private String restoreUserEmailId;
	private String restoreUserEmailDomain;
	private int googleConnected;
	private int naverConnected;
	private int kakaoConnected;
	private String userSignDate;
	private String userType;
	
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberDTO(String userId, String password, String nickname, String restoreUserEmailId,
					 String restoreUserEmailDomain, int googleConnected, int naverConnected, int kakaoConnected,
					 String userSignDate, String userType) {
		super();
		this.userId = userId;
		this.password = password;
		this.nickname = nickname;
		this.restoreUserEmailId = restoreUserEmailId;
		this.restoreUserEmailDomain = restoreUserEmailDomain;
		this.googleConnected = googleConnected;
		this.naverConnected = naverConnected;
		this.kakaoConnected = kakaoConnected;
		this.userSignDate = userSignDate;
		this.userType = userType;
	}
}
