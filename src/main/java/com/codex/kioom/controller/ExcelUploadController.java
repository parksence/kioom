package com.codex.kioom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ExcelUploadController {

    @PostMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                // 엑셀 파일 처리 (예 : Apache POI 라이브러리 사용)
                // 저장된 데이터를 DB에 저장
                // Your logic here
                return "엑셀 업로드가 완료되었습니다.";
            } catch (Exception e) {
                return "엑셀 업로드에 실패하였습니다.";
            }
        } else {
            return "파일이 없습니다.";
        }
    }

}
