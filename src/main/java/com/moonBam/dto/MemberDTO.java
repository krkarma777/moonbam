package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO{
	private String username;
	private String password;
	private String nickname;
	private String restoreUserEmailId;
	private String restoreUserEmailDomain;
	private int googleConnected;
	private int naverConnected;
	private int kakaoConnected;
	private String userSignDate;
	private String userType;
	@Override
	public String toString() {
		return "MemberDTO [username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", restoreUserEmailId=" + restoreUserEmailId + ", restoreUserEmailDomain=" + restoreUserEmailDomain
				+ ", googleConnected=" + googleConnected + ", naverConnected=" + naverConnected + ", kakaoConnected="
				+ kakaoConnected + ", userSignDate=" + userSignDate + ", userType=" + userType + "]";
	}
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDTO(String username, String password, String nickname, String restoreUserEmailId,
			String restoreUserEmailDomain, int googleConnected, int naverConnected, int kakaoConnected,
			String userSignDate, String userType) {
		super();
		this.username = username;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRestoreUserEmailId() {
		return restoreUserEmailId;
	}
	public void setRestoreUserEmailId(String restoreUserEmailId) {
		this.restoreUserEmailId = restoreUserEmailId;
	}
	public String getRestoreUserEmailDomain() {
		return restoreUserEmailDomain;
	}
	public void setRestoreUserEmailDomain(String restoreUserEmailDomain) {
		this.restoreUserEmailDomain = restoreUserEmailDomain;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
