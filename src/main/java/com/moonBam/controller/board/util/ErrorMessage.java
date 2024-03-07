package com.moonBam.controller.board.util;

public enum ErrorMessage {
	ERROR("errorMesg"),
    INVALID_REQUEST("잘못된 요청입니다."),
    POST_NOT_FOUND("글이 존재하지 않습니다."),
    DELETED("삭제 완료"),
	ERROR_PAGE("error"),
    MISMATCH_ERROR("사용자가 일치하지 않습니다. 권한이 없습니다.");
	
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
