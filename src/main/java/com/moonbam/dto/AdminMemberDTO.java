package com.moonbam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminMemberDTO")
public class AdminMemberDTO {
	private String userid;
	private String userpw;
	private String username;
	private String userssn;
	private String userssn2;
	private String usergender;
	private String nickname;
	private String userphonenum1;
	private String userphonenum2;
	private String userphonenum3;
	private String usermailid;
	private String usermaildomain;
	private String usersigndate;
	private String usertype;
	
	public AdminMemberDTO() {
		super();
	}
	public AdminMemberDTO(String userid, String userpw, String username, String userssn, String userssn2, String usergender,
			String nickname, String userphonenum1, String userphonenum2, String userphonenum3, String usermailid,
			String usermaildomain, String usersigndate, String usertype) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userssn = userssn;
		this.userssn2 = userssn2;
		this.usergender = usergender;
		this.nickname = nickname;
		this.userphonenum1 = userphonenum1;
		this.userphonenum2 = userphonenum2;
		this.userphonenum3 = userphonenum3;
		this.usermailid = usermailid;
		this.usermaildomain = usermaildomain;
		this.usersigndate = usersigndate;
		this.usertype = usertype;
	}
	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", userssn=" + userssn
				+ ", userssn2=" + userssn2 + ", usergender=" + usergender + ", nickname=" + nickname
				+ ", userphonenum1=" + userphonenum1 + ", userphonenum2=" + userphonenum2 + ", userphonenum3="
				+ userphonenum3 + ", usermailid=" + usermailid + ", usermaildomain=" + usermaildomain
				+ ", usersigndate=" + usersigndate + ", usertype=" + usertype + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserssn() {
		return userssn;
	}
	public void setUserssn(String userssn) {
		this.userssn = userssn;
	}
	public String getUserssn2() {
		return userssn2;
	}
	public void setUserssn2(String userssn2) {
		this.userssn2 = userssn2;
	}
	public String getUsergender() {
		return usergender;
	}
	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserphonenum1() {
		return userphonenum1;
	}
	public void setUserphonenum1(String userphonenum1) {
		this.userphonenum1 = userphonenum1;
	}
	public String getUserphonenum2() {
		return userphonenum2;
	}
	public void setUserphonenum2(String userphonenum2) {
		this.userphonenum2 = userphonenum2;
	}
	public String getUserphonenum3() {
		return userphonenum3;
	}
	public void setUserphonenum3(String userphonenum3) {
		this.userphonenum3 = userphonenum3;
	}
	public String getUsermailid() {
		return usermailid;
	}
	public void setUsermailid(String usermailid) {
		this.usermailid = usermailid;
	}
	public String getUsermaildomain() {
		return usermaildomain;
	}
	public void setUsermaildomain(String usermaildomain) {
		this.usermaildomain = usermaildomain;
	}
	public String getUsersigndate() {
		return usersigndate;
	}
	public void setUsersigndate(String usersigndate) {
		this.usersigndate = usersigndate;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	
}
