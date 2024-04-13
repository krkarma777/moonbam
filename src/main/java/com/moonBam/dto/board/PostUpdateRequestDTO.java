package com.moonBam.dto.board;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class PostUpdateRequestDTO {

    private Long postId;

    @NotBlank
    @Size(max = 40)
    private String postTitle;

    private Date postEditDate = new Date();

    @NotBlank
    private String postText;

    @NotNull
    private Long categoryId;
}
