package com.ohgiraffers.midnight.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {
    private final String uploadDir = "src/main/resources/static/files/";

    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response) throws IOException {
        String path = uploadDir + "testFile.pdf";

        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("testFile.pdf", "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadWavFile(@RequestParam("file") MultipartFile file) {
        if (!file.getContentType().equals("audio/wav")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only WAV files are allowed.");
        }

        // 파일 저장 경로 설정
        String filePath = uploadDir + file.getOriginalFilename();

        // 파일 저장
        try {
            file.transferTo(new File(filePath));
            return ResponseEntity.ok("File uploaded successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }
    }
}
