package com.moonBam.controller.community.oh;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomDTO {
    private Long id;
    private String roomTitle;
    private String roomDescription;
    private Integer currentCount;
    private Integer maxAmount;
    private String location;
    private LocalDateTime creationDate;
    private String category;
    private String leaderId;
}
