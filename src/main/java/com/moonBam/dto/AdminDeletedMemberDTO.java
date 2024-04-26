package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminDeletedMemberDTO")
public class AdminDeletedMemberDTO {
	String userId;
	String userPw;
	String nickname;
	String secretCode;
	int googleConnected;
	int naverConnected;
	int kakaoConnected;
	String userSignDate;
	String role;
	String enabled;
	String cause;
	String expdate;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSecretCode() {
		return secretCode;
	}
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
	public int getGoogleConnected() {
		return googleConnected;
	}
	public void setGoogleConnected(int googleConnected) {
		this.googleConnected = googleConnected;
	}
	public int getNaverConnected() {
		return naverConnected;
	}
	public void setNaverConnected(int naverConnected) {
		this.naverConnected = naverConnected;
	}
	public int getKakaoConnected() {
		return kakaoConnected;
	}
	public void setKakaoConnected(int kakaoConnected) {
		this.kakaoConnected = kakaoConnected;
	}
	public String getUserSignDate() {
		return userSignDate;
	}
	public void setUserSignDate(String userSignDate) {
		this.userSignDate = userSignDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}
	public AdminDeletedMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminDeletedMemberDTO(String userId, String userPw, String nickname, String secretCode, int googleConnected,
			int naverConnected, int kakaoConnected, String userSignDate, String role, String enabled, String cause,
			String expdate) {
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
		this.cause = cause;
		this.expdate = expdate;
	}
	@Override
	public String toString() {
		return "AdminDeletedMemberDTO [userId=" + userId + ", userPw=" + userPw + ", nickname=" + nickname
				+ ", secretCode=" + secretCode + ", googleConnected=" + googleConnected + ", naverConnected="
				+ naverConnected + ", kakaoConnected=" + kakaoConnected + ", userSignDate=" + userSignDate + ", role="
				+ role + ", enabled=" + enabled + ", cause=" + cause + ", expdate=" + expdate + "]";
	}
	
	
	
}
