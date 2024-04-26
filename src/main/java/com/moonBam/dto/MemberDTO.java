package com.moonBam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Alias("MemberDTO")
@Getter
@Setter
@ToString
public class MemberDTO{
	
	@NotBlank
	@Email
	@Size(min = 4, max = 49, message="아이디는 최소 4글자 이상, 50글자 미만")
	private String userId;

	@NotBlank
	@Size(min = 6, max = 20, message="비밀번호는 최소 6글자 이상, 20글자 미만")
	private String userPw;

	@NotBlank
	@Size(min = 2, max = 49, message="닉네임은 최소 2글자 이상, 50글자 미만")
	private String nickname;

	@NotBlank
	private String secretCode ;
	
	private int googleConnected;
	
	private int naverConnected;
	
	private int kakaoConnected;
	
	@NotBlank
	private String userSignDate;
	
	@NotBlank
	private String role;

	@NotBlank
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
