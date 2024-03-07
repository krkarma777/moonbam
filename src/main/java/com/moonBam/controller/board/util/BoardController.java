package com.moonBam.controller.board.util;

import java.io.IOException;
import java.util.Map;

/**
 * 게시판과 관련된 컨트롤러의 인터페이스입니다.
 */
public interface BoardController {
    /**
     * 컨트롤러의 처리 메서드입니다.
     * 
     * @param paramMap 요청 파라미터의 맵
     * @param model    뷰에서 사용할 모델 객체
     * @return 뷰의 이름 또는 리다이렉션 URL (예: "boardList" 또는 "redirect:/board/list")
     * @throws IOException 
     */
    String process(Map<String, String> paramMap, Map<String, Object> model) throws IOException;
}
