package com.moonBam.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Alias("AdminDeletedPostDTO")
public class AdminDeletedPostDTO {
    String deletedate;
    Long postid;
    @NotBlank
    String postboard;
    String userid;
    Long contid = 0L;
    @NotBlank
	@Size(max = 40)
    String posttitle;
    Date postdate;
    Date posteditdate;
    @NotBlank
    String posttext;
    @NotNull
    Long categoryid;
    String cause;
    String expiredate;
    String nickname;
	public AdminDeletedPostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AdminDeletedPostDTO [deletedate=" + deletedate + ", postid=" + postid + ", postboard=" + postboard
				+ ", userid=" + userid + ", contid=" + contid + ", posttitle=" + posttitle + ", postdate=" + postdate
				+ ", posteditdate=" + posteditdate + ", posttext=" + posttext + ", categoryid=" + categoryid
				+ ", cause=" + cause + ", expiredate=" + expiredate + ", nickname=" + nickname + "]";
	}
	public String getDeletedate() {
		return deletedate;
	}
	public void setDeletedate(String deletedate) {
		this.deletedate = deletedate;
	}
	public Long getPostid() {
		return postid;
	}
	public void setPostid(Long postid) {
		this.postid = postid;
	}
	public String getPostboard() {
		return postboard;
	}
	public void setPostboard(String postboard) {
		this.postboard = postboard;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Long getContid() {
		return contid;
	}
	public void setContid(Long contid) {
		this.contid = contid;
	}
	public String getPosttitle() {
		return posttitle;
	}
	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public Date getPosteditdate() {
		return posteditdate;
	}
	public void setPosteditdate(Date posteditdate) {
		this.posteditdate = posteditdate;
	}
	public String getPosttext() {
		return posttext;
	}
	public void setPosttext(String posttext) {
		this.posttext = posttext;
	}
	public Long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
	
    
    
}
