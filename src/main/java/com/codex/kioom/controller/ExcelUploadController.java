package com.codex.kioom.controller;

import com.codex.kioom.config.security.exception.DuplicateUserIdException;
import com.codex.kioom.dto.ExcelUserDTO;
import com.codex.kioom.service.ExcelService;
import com.codex.kioom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExcelUploadController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/uploadExcel", method = { RequestMethod.GET, RequestMethod.POST })
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                // ExcelService를 통해 엑셀 파일을 처리하고 DB에 저장
                List<ExcelUserDTO> data = excelService.processExcel(file);
                System.out.println("data.toString() = " + data.toString());

                // 기존에 생성된 h_id 확인
                List<String> userList = userService.selectUserId();

                // 중복된 아이디 담기
                List<String> duplicateIds = new ArrayList();

                for (ExcelUserDTO userData : data) {
                    String userId = userData.getH_ID();
                    if (userList.contains(userId)) {
                        duplicateIds.add(userId);
                    }
                }

                // 중복된 아이디 목록 출력 또는 처리
                if (!duplicateIds.isEmpty()) {
                    throw new DuplicateUserIdException("중복된 아이디가 존재합니다. " + duplicateIds.toString());
                } else {
                    System.out.println("중복된 아이디 없음");
                }

                // 업로드 데이터 담기
                Map<String, Object> param = new HashMap();

                for (int j = 0; j < data.size(); j++) {
                    param.put("h_id", data.get(j).getH_ID().toString());
                    param.put("email", data.get(j).getEMAIL().toString());
                    // 초기 비밀번호 1234로 지정
                    param.put("h_pw", passwordEncoder.encode("1234"));
                    param.put("h_name", data.get(j).getH_NAME().toString());
                    param.put("h_location", data.get(j).getH_LOCATION().toString());
                    param.put("h_manager", data.get(j).getH_MANAGER().toString());
                    param.put("h_phone", data.get(j).getH_PHONE().toString());
                    param.put("h_tel", data.get(j).getH_TEL().toString());
                    param.put("h_fax", data.get(j).getH_FAX().toString());

                    // 병원 등록
                    userService.insertExcelUser(param);
                }

                return "엑셀 업로드가 완료되었습니다.";
            } catch (IOException e) {
                return "엑셀 업로드에 실패하였습니다.";
            }
        } else {
            return "파일이 없습니다.";
        }
    }

}
