package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("AdminDeletedPostDTO")
public class AdminDeletedPostDTO {
    String deletedate;
    String postid;
    String postboard;
    String userid;
    String contid;
    String posttitle;
    String postdate;
    String posteditdate;
    String posttext;
    String categoryid;
    String cause;
    String expiredate;
    
    public String getDeletedate() {
        return deletedate;
    }
    public void setDeletedate(String deletedate) {
        this.deletedate = deletedate;
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
    public String getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(String categoryid) {
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
}
