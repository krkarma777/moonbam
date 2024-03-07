package com.moonbam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminRestrictedMemberDTO")
public class AdminRestrictedMemberDTO {

	String actno;
	String userid;
	String status;
	String cause;
	String action;
	String actionstart;
	String actionend;
	public AdminRestrictedMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminRestrictedMemberDTO(String actno, String userid, String status, String cause, String action,
			String actionstart, String actionend) {
		super();
		this.actno = actno;
		this.userid = userid;
		this.status = status;
		this.cause = cause;
		this.action = action;
		this.actionstart = actionstart;
		this.actionend = actionend;
	}
	@Override
	public String toString() {
		return "AdminRestrictedMemberDTO [actno=" + actno + ", userid=" + userid + ", status=" + status + ", cause="
				+ cause + ", action=" + action + ", actionstart=" + actionstart + ", actionend=" + actionend + "]";
	}
	public String getActno() {
		return actno;
	}
	public void setActno(String actno) {
		this.actno = actno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActionstart() {
		return actionstart;
	}
	public void setActionstart(String actionstart) {
		this.actionstart = actionstart;
	}
	public String getActionend() {
		return actionend;
	}
	public void setActionend(String actionend) {
		this.actionend = actionend;
	}
	
	
	
	
}
