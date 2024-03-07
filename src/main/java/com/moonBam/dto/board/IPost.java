package com.moonBam.dto.board;

import java.util.Date;

public interface IPost {
    Long getPostId();
    void setPostId(Long postId);
    String getPostBoard();
    void setPostBoard(String postBoard);
    String getUserId();
    void setUserId(String userId);
    Long getContId();
    void setContId(Long contId);
    String getPostTitle();
    void setPostTitle(String postTitle);
    Date getPostDate();
    void setPostDate(Date postDate);
    Date getPostEditDate();
    void setPostEditDate(Date postEditDate);
    String getPostText();
    void setPostText(String postText);
    String getNickname();
    void setNickname(String nickname);
}
