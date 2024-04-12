package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminReportDTO")
public class AdminReportDTO {

	String reportId;
	String postId;
	String reporter;
	String userId;
	int sexual;
	int lang;
	int abusing;
	int ruleViolation;
	int etc;
	String cont;
	String action;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getSexual() {
		return sexual;
	}
	public void setSexual(int sexual) {
		this.sexual = sexual;
	}
	public int getLang() {
		return lang;
	}
	public void setLang(int lang) {
		this.lang = lang;
	}
	public int getAbusing() {
		return abusing;
	}
	public void setAbusing(int abusing) {
		this.abusing = abusing;
	}
	public int getRuleViolation() {
		return ruleViolation;
	}
	public void setRuleViolation(int ruleViolation) {
		this.ruleViolation = ruleViolation;
	}
	public int getEtc() {
		return etc;
	}
	public void setEtc(int etc) {
		this.etc = etc;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public AdminReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AdminReportDTO [reportId=" + reportId + ", postId=" + postId + ", reporter=" + reporter + ", userId="
				+ userId + ", sexual=" + sexual + ", lang=" + lang + ", abusing=" + abusing + ", ruleViolation="
				+ ruleViolation + ", etc=" + etc + ", cont=" + cont + ", action=" + action + "]";
	}
	
	
	
}
