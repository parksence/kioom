package com.codex.kioom.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


@Controller
public class FileController {

    @Value("${files.external}")
    String rootPath;

    public static String simplifyPath(String path) {
        return path.replaceAll("/+", "/");
    }

    @GetMapping("/download")
    public void download(@RequestParam("path") String path, @RequestParam("filename") String filename, HttpServletResponse response) throws IOException {

        path = simplifyPath(rootPath + path);

        String mimeType = determineMimeType(filename);

        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

        // 파일유형 설정
        response.setContentType(mimeType);
        // 파일길이 설정
        if(fileByte != null) {
            response.setContentLength(fileByte.length);
        }
        // 데이터 형식
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(filename, "UTF-8")+"\";");
        // 내용물 인코딩방식 설정
        response.setHeader("Content-Transfer-Encoding", "binary");

        // 버퍼의 출력스트림을 출력
        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    // Mime 타입
    private String determineMimeType(String fileName) {
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".pdf")) {
            return "application/pdf";
        } else if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else {
            // 기본적으로 octet-stream으로 설정합니다.
            return "application/octet-stream";
        }
    }
}
