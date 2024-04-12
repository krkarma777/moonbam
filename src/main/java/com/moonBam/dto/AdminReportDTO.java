package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminReportDTO")
public class AdminReportDTO {

	//String reportId;
	String targetId;
	String reporter;
	String userId;
	int sexual = 0;
	int lang = 0;
	int abusing = 0;
	int ruleviolation = 0;
	int etc = 0;
	String cont;
	String action;
	
	
	
	
	public AdminReportDTO() {
		super();
	}

//	public String getReportId() {
//		return reportId;
//	}
//
//	public void setReportId(String reportId) {
//		this.reportId = reportId;
//	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
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

	public int getRuleviolation() {
		return ruleviolation;
	}

	public void setRuleviolation(int ruleviolation) {
		this.ruleviolation = ruleviolation;
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

	public AdminReportDTO(//String reportId, 
			String targetId, String reporter, String userId, int sexual, int lang,
			int abusing, int ruleviolation, int etc, String cont, String action) {
		super();
		//this.reportId = reportId;
		this.targetId = targetId;
		this.reporter = reporter;
		this.userId = userId;
		this.sexual = sexual;
		this.lang = lang;
		this.abusing = abusing;
		this.ruleviolation = ruleviolation;
		this.etc = etc;
		this.cont = cont;
		this.action = action;
	}

	@Override
	public String toString() {
		return "AdminReportDTO [reportId="  + ", targetId=" + targetId + ", reporter=" + reporter
				+ ", userId=" + userId + ", sexual=" + sexual + ", lang=" + lang + ", abusing=" + abusing
				+ ", ruleviolation=" + ruleviolation + ", etc=" + etc + ", cont=" + cont + ", action=" + action + "]";
	}
}
