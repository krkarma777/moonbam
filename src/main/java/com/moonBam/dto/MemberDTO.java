package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO{
	private String userId;
	private String userPw;
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
		return "MemberDTO [userId=" + userId + ", userPw=" + userPw + ", nickname=" + nickname + ", restoreUserEmailId="
				+ restoreUserEmailId + ", restoreUserEmailDomain=" + restoreUserEmailDomain + ", googleConnected="
				+ googleConnected + ", naverConnected=" + naverConnected + ", kakaoConnected=" + kakaoConnected
				+ ", userSignDate=" + userSignDate + ", userType=" + userType + "]";
	}
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDTO(String userId, String userPw, String nickname, String restoreUserEmailId,
			String restoreUserEmailDomain, int googleConnected, int naverConnected, int kakaoConnected,
			String userSignDate, String userType) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.nickname = nickname;
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
