package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("CreditDTO")
public class CreditDTO {
	
//    "adult": false,
//    "gender": 1,
//    "id": 15556,
//    "known_for_department": "Acting",
//    "name": "Rebecca Hall",
//    "original_name": "Rebecca Hall",
//    "popularity": 28.93,
//    "profile_path": "/cVZaQrUY7F5khCBYdKDlEppHnQi.jpg",
//    "cast_id": 10,
//    "character": "Dr. Ilene Andrews",
//    "credit_id": "6307a3a8bb070d0095ae6147",
//    "order": 0
	private boolean adult;
	private int gender;
	private String id;
	private String known_for_department;
	private String name;
	private String original_name;
	private String popularity;
	private String profile_path;
	private String cast_id;
	private String character;
	private String credit_id;
	private String order;
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getKnown_for_department() {
		return known_for_department;
	}
	public void setKnown_for_department(String known_for_department) {
		this.known_for_department = known_for_department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	public String getProfile_path() {
		return profile_path;
	}
	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}
	public String getCast_id() {
		return cast_id;
	}
	public void setCast_id(String cast_id) {
		this.cast_id = cast_id;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getCredit_id() {
		return credit_id;
	}
	public void setCredit_id(String credit_id) {
		this.credit_id = credit_id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "CreditDTO [adult=" + adult + ", gender=" + gender + ", known_for_department=" + known_for_department
				+ ", name=" + name + ", original_name=" + original_name + ", popularity=" + popularity
				+ ", profile_path=" + profile_path + ", cast_id=" + cast_id + ", character=" + character
				+ ", credit_id=" + credit_id + ", order=" + order + "]";
	}
	
	
}
