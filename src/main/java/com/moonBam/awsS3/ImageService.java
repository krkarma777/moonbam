package com.moonBam.awsS3;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    private S3Config s3Config;

    @Autowired
    public ImageService(S3Config s3Config) {

        this.s3Config = s3Config;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    //로컬 환경의 파일 저장 위치(잠시 저장되었다가 삭제)
    private String localLocation = "C:\\Users\\Haru\\Desktop\\Z\\";

    //이미지를 S3에 올리는 기능
    public String imageUpload(MultipartRequest request) throws IOException {

        //ckEditor에서 올린 이미지 파일
        MultipartFile file = request.getFile("upload");

        //파일명
        String fileName = file.getOriginalFilename();

        //파일의 확장자명
        String ext = fileName.substring(fileName.indexOf("."));

        //유저 개인의 개별 식별자
        String uuidFileName = UUID.randomUUID() + ext;

        //로컬 환경의 경로 + 파일 이름
        String localPath = localLocation + uuidFileName;

        //ckEditor에서 받은 파일을 로컬에 저장
        File localFile = new File(localPath);
        file.transferTo(localFile);

        //putObject(이미지를 S3에 저장하는 method)
        //PutObjectRequest(버킷명 / 파일명 / 서버에 저장한 파일)
        //withCannedAcl(CannedAccessControlList.PublicRead): 외부에서 올린 public 파일을 외부에서 읽을 수 있음
        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, uuidFileName, localFile).withCannedAcl(CannedAccessControlList.PublicRead));
        
        //올라간 이미지의 주소를 받아옴
        String s3Url = s3Config.amazonS3Client().getUrl(bucket, uuidFileName).toString();

        //로컬 환경에 저장되었던 파일 삭제
        localFile.delete();

        return s3Url;

    }
}