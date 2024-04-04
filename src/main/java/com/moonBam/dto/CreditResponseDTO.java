package com.moonBam.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("CreditResponseDTO")
public class CreditResponseDTO {
	
	private Long id;
	private List<CreditDTO> cast;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CreditDTO> getCast() {
		return cast;
	}
	public void setCast(List<CreditDTO> cast) {
		this.cast = cast;
	}
	@Override
	public String toString() {
		return "CreditResponseDTO [id=" + id + ", cast=" + cast + "]";
	}
	
	
}
