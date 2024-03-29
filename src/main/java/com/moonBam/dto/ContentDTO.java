package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("ContentDTO")
public class ContentDTO {
	private Long contId;
	private String contTitle;
//	private Long producerId;
	private String description;
	private String nation;
	private String releaseDate;
	private Integer average;
	private String contType;
	private String contImg;
	private String popularity;
	public Long getContId() {
		return contId;
	}
	public void setContId(Long contId) {
		this.contId = contId;
	}
	public String getContTitle() {
		return contTitle;
	}
	public void setContTitle(String contTitle) {
		this.contTitle = contTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Integer getAverage() {
		return average;
	}
	public void setAverage(Integer average) {
		this.average = average;
	}
	public String getContType() {
		return contType;
	}
	public void setContType(String contType) {
		this.contType = contType;
	}
	public String getContImg() {
		return contImg;
	}
	public void setContImg(String contImg) {
		this.contImg = contImg;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	public ContentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContentDTO(Long contId, String contTitle, String description, String nation, String releaseDate,
			Integer average, String contType, String contImg, String popularity) {
		super();
		this.contId = contId;
		this.contTitle = contTitle;
		this.description = description;
		this.nation = nation;
		this.releaseDate = releaseDate;
		this.average = average;
		this.contType = contType;
		this.contImg = contImg;
		this.popularity = popularity;
	}
	@Override
	public String toString() {
		return "ContentDTO [contId=" + contId + ", contTitle=" + contTitle + ", description=" + description
				+ ", nation=" + nation + ", releaseDate=" + releaseDate + ", average=" + average + ", contType="
				+ contType + ", contImg=" + contImg + ", popularity=" + popularity + "]";
	}
	
}
