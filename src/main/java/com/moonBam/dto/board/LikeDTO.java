package com.moonBam.dto.board;

import org.apache.ibatis.type.Alias;

@Alias("LikeDTO")
public class LikeDTO {
    private String userId;
    private Long postId;
    private char isLike; // '1'은 좋아요 상태, '0'은 좋아요 취소 상태

    public LikeDTO() {
        // 기본 생성자
    }

    public LikeDTO(String userId, Long postId, char isLike) {
        this.userId = userId;
        this.postId = postId;
        this.isLike = isLike;
    }

    // Getter와 Setter 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public char getIsLike() {
        return isLike;
    }

    public void setIsLike(char isLike) {
        this.isLike = isLike;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "LikeDTO{" +
                "userId='" + userId + '\'' +
                ", postId=" + postId +
                ", isLike=" + isLike +
                '}';
    }
}
