package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminReportDTO")
public class AdminReportDTO {

	//String reportId;
	String targetId;
	String reporterId;
	String userId;
	String sexual = "F";
	String lang = "F";
	String abusing = "F";
	String ruleviolation = "F";
	String etc = "F";
	String cont;
	String action;
	
	@Override
	public String toString() {
		return "AdminReportDTO [targetId=" + targetId + ", reporterId=" + reporterId + ", userId=" + userId + ", sexual="
				+ sexual + ", lang=" + lang + ", abusing=" + abusing + ", ruleviolation=" + ruleviolation + ", etc="
				+ etc + ", cont=" + cont + ", action=" + action + "]";
	}

	public AdminReportDTO(String targetId, String reporterId, String userId, String sexual, String lang, String abusing,
			String ruleviolation, String etc, String cont, String action) {
		super();
		this.targetId = targetId;
		this.reporterId = reporterId;
		this.userId = userId;
		this.sexual = sexual;
		this.lang = lang;
		this.abusing = abusing;
		this.ruleviolation = ruleviolation;
		this.etc = etc;
		this.cont = cont;
		this.action = action;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getReporterId() {
		return reporterId;
	}

	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSexual() {
		return sexual;
	}

	public void setSexual(String sexual) {
		this.sexual = sexual;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getAbusing() {
		return abusing;
	}

	public void setAbusing(String abusing) {
		this.abusing = abusing;
	}

	public String getRuleviolation() {
		return ruleviolation;
	}

	public void setRuleviolation(String ruleviolation) {
		this.ruleviolation = ruleviolation;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
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
	
	
	
}
