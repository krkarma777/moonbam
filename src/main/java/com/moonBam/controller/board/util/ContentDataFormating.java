package com.moonBam.controller.board.util;

import com.moonBam.dto.board.PostPageDTO;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ContentDataFormating {
	
	
	public String minuteHourDay(PostPageDTO post){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date now = new Date();
        // 게시물의 날짜와 현재 날짜 사이의 차이를 계산
        long timeDiff = now.getTime() - post.getPostDate().getTime();
        String displayTime;

        if(timeDiff < 24 * 60 * 60 * 1000) { // 24시간 이내인 경우
            long diffHours = timeDiff / (60 * 60 * 1000);
            if(diffHours >= 1) {
                displayTime = diffHours + " 시간 전";
            } else {
                long diffMinutes = timeDiff / (60 * 1000);
                if(diffMinutes < 1) {
                    displayTime = "방금 전";
                } else {
                    displayTime = diffMinutes + " 분 전";
                }
            }
        } else {
            // 24시간 이상 지난 게시물에 대한 날짜 처리
            displayTime = sdf.format(post.getPostDate());
        }
        return displayTime;
	}
}
