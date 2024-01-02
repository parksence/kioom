package com.codex.kioom.service;

import com.codex.kioom.dto.ExcelUserDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    // 데이터베이스에 데이터 저장을 위한 Repository 주입
    public List<ExcelUserDTO> processExcel(MultipartFile file) throws IOException {
        List<ExcelUserDTO> data = new ArrayList();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 가져오기

        Iterator<Row> rows = sheet.iterator();
        rows.next(); // 헤더 행 제거

        int rowNumber = 1; // 현재 행 번호를 추적하기 위한 변수

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            ExcelUserDTO excelUserDTO = new ExcelUserDTO();

            try {
                // 열의 데이터 유효성 검사
                if(currentRow.getLastCellNum() < 8) {
                    throw new IllegalArgumentException("엑셀 양식이 잘못되었습니다.\n열의 수가 맞지 않습니다.");
                }

                // 각 열의 데이터를 읽어와 excelUserDTO 설정
                excelUserDTO.setH_ID(currentRow.getCell(0).getStringCellValue());
                excelUserDTO.setEMAIL(currentRow.getCell(1).getStringCellValue());
                excelUserDTO.setH_NAME(currentRow.getCell(2).getStringCellValue());
                excelUserDTO.setH_LOCATION(currentRow.getCell(3).getStringCellValue());
                excelUserDTO.setH_MANAGER(currentRow.getCell(4).getStringCellValue());
                excelUserDTO.setH_PHONE(currentRow.getCell(5).getStringCellValue());
                excelUserDTO.setH_TEL(currentRow.getCell(6).getStringCellValue());
                excelUserDTO.setH_FAX(currentRow.getCell(7).getStringCellValue());

                // 데이터베이스에 저장하기 위해 리스트에 추가
                data.add(excelUserDTO);
            } catch (Exception e) {
                System.out.println("행 " + rowNumber + "에서 양식에 맞지 않는 데이터를 발견 : " + e.getMessage());
            }

            // 다음 행으로 이동하기 전에 행 번호 증가
            rowNumber++;
        }

        workbook.close();
        return data;
    }
}
