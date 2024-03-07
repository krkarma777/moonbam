package com.moonbam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminBoardDTO")
public class AdminBoardDTO {

	private String postid;
	private String postboard;
	private String userid;
	private String contid;
	private String posttitle;
	private String postdate;
	private String posteditdate;
	private String posttext;
	
	public AdminBoardDTO() {
		super();
	}

	public AdminBoardDTO(String postid, String postboard, String userid, String contid, String posttitle, String postdate,
			String posteditdate, String posttext) {
		super();
		this.postid = postid;
		this.postboard = postboard;
		this.userid = userid;
		this.contid = contid;
		this.posttitle = posttitle;
		this.postdate = postdate;
		this.posteditdate = posteditdate;
		this.posttext = posttext;
	}

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
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

	public String getContid() {
		return contid;
	}

	public void setContid(String contid) {
		this.contid = contid;
	}

	public String getPosttitle() {
		return posttitle;
	}

	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

	public String getPosteditdate() {
		return posteditdate;
	}

	public void setPosteditdate(String posteditdate) {
		this.posteditdate = posteditdate;
	}

	public String getPosttext() {
		return posttext;
	}

	public void setPosttext(String posttext) {
		this.posttext = posttext;
	}

	@Override
	public String toString() {
		return "PostDTO [postid=" + postid + ", postboard=" + postboard + ", userid=" + userid + ", contid=" + contid
				+ ", posttitle=" + posttitle + ", postdate=" + postdate + ", posteditdate=" + posteditdate
				+ ", posttext=" + posttext + "]";
	}
	
	
	
	
	
	
	
}
