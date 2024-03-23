package com.moonBam.controller.board.file;

import com.moonBam.service.ImageStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final ImageStorageService imageStorageService;

    @PostMapping("/upload-image")
    public ResponseEntity<Map<String, String>> handleFileUpload(@RequestParam("image") MultipartFile file) {
        String imageUrl = imageStorageService.store(file);
        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
    }

    @GetMapping("/list-images")
    public ResponseEntity<List<String>> listUploadedFiles() {
        List<String> files = imageStorageService.listAllFiles();
        return ResponseEntity.ok(files);
    }

    @PostMapping("/delete-image")
    public ResponseEntity<?> deleteImage(@RequestParam("filename") String filename) {
        imageStorageService.delete(filename);
        return ResponseEntity.ok("File deleted successfully");
    }
}
