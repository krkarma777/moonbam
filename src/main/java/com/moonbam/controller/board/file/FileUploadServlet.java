package com.moonbam.controller.board.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moonbam.config.ConfigurationLoader;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
@MultipartConfig // 이 어노테이션은 서블릿이 멀티파트(파일 업로드) 요청을 처리한다는 것을 나타냅니다.
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// 요청이 멀티파트 형식인지 확인합니다. 아니면 오류 메시지를 반환합니다.
		if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter writer = response.getWriter();
			writer.println("오류: 폼은 enctype이 multipart/form-data이어야 합니다.");
			writer.flush();
			return;
		}
		
		// 설정 로더를 통해 업로드 경로 및 기타 설정을 로드합니다.
        ConfigurationLoader config = new ConfigurationLoader();
        String uploadPath = config.getProperty("uploadPath");
        int MEMORY_THRESHOLD = config.getIntProperty("MEMORY_THRESHOLD_MB") * 1024 * 1024;
        int MAX_FILE_SIZE = config.getIntProperty("MAX_FILE_SIZE_MB") * 1024 * 1024;
        int MAX_REQUEST_SIZE = config.getIntProperty("MAX_REQUEST_SIZE_MB") * 1024 * 1024;
        String UPLOAD_DIRECTORY = config.getProperty("UPLOAD_DIRECTORY");

        // 파일 업로드를 위한 설정을 구성합니다.
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD); // 메모리 임계값 설정
        factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); // 임시 저장 디렉토리 설정

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE); // 최대 파일 크기 설정
        upload.setSizeMax(MAX_REQUEST_SIZE); // 최대 요청 크기 설정

        // 업로드된 파일이 저장될 서버 경로 설정
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 요청에서 파일 아이템을 파싱합니다.
            List<FileItem> formItems = upload.parseRequest(request);

            String uploadedFileUrl = ""; // 업로드된 파일의 URL

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) { // 파일 필드인 경우 처리
                        String fileName = new File(item.getName()).getName();
                        String ext = fileName.substring(fileName.lastIndexOf('.')); // 파일 확장자 추출
                        String newFileName = UUID.randomUUID().toString() + ext; // 고유한 파일 이름 생성

                        // 파일 저장 경로 설정
                        String filePath = uploadPath + File.separator + newFileName;
                        File storeFile = new File(filePath);

                        item.write(storeFile); // 디스크에 파일 저장

                        // 업로드된 파일의 URL 설정
                        uploadedFileUrl = request.getContextPath() + "/" + UPLOAD_DIRECTORY + "/" + newFileName;
                    }
                }
            }

            // 클라이언트에게 JSON 형식으로 업로드된 파일 URL을 반환합니다.
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("{\"fileUrl\":\"" + uploadedFileUrl + "\"}");
            out.flush();
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "오류 발생: " + ex.getMessage());
        }
    }
}
