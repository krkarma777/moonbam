package com.moonBam.dto;

public class QnADTO {
    String qnaid;
    String userid;
    String cat;
    String title;
    String text;
    String postdate;
    String answer;
    String answerdate;

    
    public QnADTO(){
        super();
    }

    public String getQnaid() {
        return qnaid;
    }

    public void setQnaid(String qnaid) {
        this.qnaid = qnaid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerdate() {
        return answerdate;
    }

    public void setAnswerdate(String answerdate) {
        this.answerdate = answerdate;
    }

	@Override
	public String toString() {
		return "QnADTO [qnaid=" + qnaid + ", userid=" + userid + ", cat=" + cat + ", title=" + title + ", text=" + text
				+ ", postdate=" + postdate + ", answer=" + answer + ", answerdate=" + answerdate + "]";
	}

    
}
