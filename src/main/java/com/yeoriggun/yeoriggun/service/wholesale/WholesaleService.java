package com.yeoriggun.yeoriggun.service.wholesale;

import com.yeoriggun.yeoriggun.dto.wholesale.WholesaleGradeDto;
import com.yeoriggun.yeoriggun.entity.wholesale.Wholesale;
import com.yeoriggun.yeoriggun.repository.wholesale.WholesaleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WholesaleService {

    private final WholesaleRepository wholesaleRepository;

    public void loadExcelFromResources() throws Exception {
        List<Wholesale> wholesaleList = new ArrayList<>();

        // 엑셀 파일 열기 (xls)
        try (InputStream is = getClass().getResourceAsStream("/data/wholesale.xls");
             Workbook workbook = new HSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter(); // 모든 셀을 문자열로 안전하게 읽기

            // 제목행(0), 헤더행(1) 건너뛰고 2행부터 읽기
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String item = formatter.formatCellValue(row.getCell(0));
                String unit = formatter.formatCellValue(row.getCell(1));
                String grade = formatter.formatCellValue(row.getCell(2));

                // averagePrice 처리 (BIGINT)
                long avgPrice = 0;
                Cell priceCell = row.getCell(3);
                if (priceCell != null) {
                    if (priceCell.getCellType() == CellType.NUMERIC) {
                        avgPrice = (long) priceCell.getNumericCellValue();
                    } else if (priceCell.getCellType() == CellType.STRING) {
                        // 쉼표 제거 후 Long 변환
                        String priceStr = priceCell.getStringCellValue().trim().replace(",", "");
                        if (!priceStr.isEmpty()) {
                            avgPrice = Long.parseLong(priceStr);
                        }
                    }
                }

                String regDate = formatter.formatCellValue(row.getCell(4));

                Wholesale wholesale = Wholesale.builder()
                        .item(item)
                        .unit(unit)
                        .averagePrice(avgPrice)
                        .regDate(regDate)
                        .grade(grade)
                        .build();

                wholesaleList.add(wholesale);
            }
        }

        // DB 저장
        wholesaleRepository.saveAll(wholesaleList);
    }

    public List<WholesaleGradeDto> getByGrade(String grade) {
        return wholesaleRepository.findByGrade(grade);
    }
}