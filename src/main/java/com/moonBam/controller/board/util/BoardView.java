package com.moonBam.controller.board.util;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 뷰를 처리하는 유틸리티 클래스입니다.
 */
public class BoardView {

    private String viewPath;

    /**
     * View 객체를 생성합니다.
     *
     * @param viewPath 뷰 페이지의 경로
     */
    public BoardView(String viewPath) {
        this.viewPath = viewPath;
    }

    /**
     * 뷰를 렌더링하여 클라이언트에게 보여줍니다.
     */
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    /**
     * 모델 데이터를 request 속성에 추가하고 뷰를 렌더링하여 클라이언트에게 보여줍니다.
     *
     * @param model    모델 데이터 맵
     */
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        for (String key : model.keySet()) {
            request.setAttribute(key, model.get(key));
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
