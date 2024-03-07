package com.moonBam.dto.board;


import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("PostCategoryDTO")
public class PostCategoryDTO {
	
	private Long categoryId;
    private String categoryName;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
	public PostCategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostCategoryDTO(Long categoryId, String categoryName, String description, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "PostCategoryDTO [categoryId=" + categoryId + ", categoryName=" + categoryName + ", description="
				+ description + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
    

}
