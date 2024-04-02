package com.moonBam.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
