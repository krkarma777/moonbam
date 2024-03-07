package com.moonbam.dto;

import org.apache.ibatis.type.Alias;

@Alias("ReportDTO")
public class ReportDTO {
	String postId;
	String reason;
	String userId;
	String reportDate;
	public ReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportDTO(String postId, String reason, String userId, String reportDate) {
		super();
		this.postId = postId;
		this.reason = reason;
		this.userId = userId;
		this.reportDate = reportDate;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	@Override
	public String toString() {
		return "ReportDTO [postId=" + postId + ", reason=" + reason + ", userId=" + userId + ", reportDate="
				+ reportDate + "]";
	}
	
	
}
