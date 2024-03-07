package com.moonbam.controller.board.file;

import com.moonbam.config.ConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/uploads/*")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 요청에서 파일 이름을 추출합니다. URL 디코딩을 통해 정확한 파일 이름을 얻습니다.
        String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");

        // 설정 로더를 사용하여 업로드 경로를 가져옵니다.
        ConfigurationLoader config = new ConfigurationLoader();
        String uploadPath = config.getProperty("uploadPath");

        // 파일 경로를 구성합니다.
        File file = new File(uploadPath, filename);

        // 응답 헤더를 설정합니다. 파일의 종류(MIME 타입), 길이, 다운로드 될 때의 파일 이름을 지정합니다.
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

        // 파일 내용을 응답 스트림에 복사하여 클라이언트에게 전송합니다.
        Files.copy(file.toPath(), response.getOutputStream());
        
	}
}
