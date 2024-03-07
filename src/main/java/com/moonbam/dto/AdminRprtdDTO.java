package com.moonbam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminRprtdDTO")
public class AdminRprtdDTO {

	String reportid;
	String postid;
	String reporter;
	String userid;
	int sexual;
	int lang;
	int abusing;
	int ruleviolation;
	int etc;
	String cont;
	String action;
	public AdminRprtdDTO() {
		super();
	}
	public AdminRprtdDTO(String reportid, String postid, String reporter, String userid, int sexual, int lang,
			int abusing, int ruleviolation, int etc, String cont, String action) {
		super();
		this.reportid = reportid;
		this.postid = postid;
		this.reporter = reporter;
		this.userid = userid;
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
		return "AdminRprtdDTO [reportid=" + reportid + ", postid=" + postid + ", reporter=" + reporter + ", userid="
				+ userid + ", sexual=" + sexual + ", lang=" + lang + ", abusing=" + abusing + ", ruleviolation="
				+ ruleviolation + ", etc=" + etc + ", cont=" + cont + ", action=" + action + "]";
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	
	
	
	
	
	
	
}
