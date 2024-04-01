package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO{
	private String userId;
	private String userPw;
	private String nickname;
	private String userEmailId;
	private String userEmailDomain;
	private String restoreUserEmailId;
	private String restoreUserEmailDomain;
	private char googleConnected;
	private char naverConnected;
	private char kakaoConnected;
	private String userSignDate;
	private String userType;
	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", userPw=" + userPw + ", nickname=" + nickname + ", userEmailId="
				+ userEmailId + ", userEmailDomain=" + userEmailDomain + ", restoreUserEmailId=" + restoreUserEmailId
				+ ", restoreUserEmailDomain=" + restoreUserEmailDomain + ", googleConnected=" + googleConnected
				+ ", naverConnected=" + naverConnected + ", kakaoConnected=" + kakaoConnected + ", userSignDate="
				+ userSignDate + ", userType=" + userType + "]";
	}
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDTO(String userId, String userPw, String nickname, String userEmailId, String userEmailDomain,
			String restoreUserEmailId, String restoreUserEmailDomain, char googleConnected, char naverConnected,
			char kakaoConnected, String userSignDate, String userType) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.nickname = nickname;
		this.userEmailId = userEmailId;
		this.userEmailDomain = userEmailDomain;
		this.restoreUserEmailId = restoreUserEmailId;
		this.restoreUserEmailDomain = restoreUserEmailDomain;
		this.googleConnected = googleConnected;
		this.naverConnected = naverConnected;
		this.kakaoConnected = kakaoConnected;
		this.userSignDate = userSignDate;
		this.userType = userType;
	}
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
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserEmailDomain() {
		return userEmailDomain;
	}
	public void setUserEmailDomain(String userEmailDomain) {
		this.userEmailDomain = userEmailDomain;
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
	public String getGoogleConnected() {
		if(googleConnected == 'n') {
			return "아니오";
		} else {
			return "예";
		}
	}
	public void setGoogleConnected(char googleConnected) {
		this.googleConnected = googleConnected;
	}
	public String getNaverConnected() {
		if(naverConnected == 'n') {
			return "아니오";
		} else {
			return "예";
		}
	}
	public void setNaverConnected(char naverConnected) {
		this.naverConnected = naverConnected;
	}
	public String getKakaoConnected() {
		if(kakaoConnected == 'n') {
			return "아니오";
		} else {
			return "예";
		}
	}
	public void setKakaoConnected(char kakaoConnected) {
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
