package com.moonBam.awsS3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    //AWS S3에 이미지 업로드
    @PostMapping("/image/upload")
    @ResponseBody
    public Map<String, Object> imageUpload(MultipartRequest request) throws Exception {

        Map<String, Object> responseData = new HashMap<>();

        try {
            //S3에 저장한 파일 url 가져오기
            String s3Url = imageService.imageUpload(request);

            System.out.println("이미지 업로드 성공");
            responseData.put("uploaded", true);
            responseData.put("url", s3Url);

            return responseData;
        
        //파일이 저장되지 않았을 경우에 작동    
        } catch (IOException e) {

            System.out.println("이미지 업로드 실패");
            responseData.put("uploaded", false);

            return responseData;
        }
    }
}