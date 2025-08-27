package com.yeoriggun.yeoriggun.controller.wholesale;


import com.yeoriggun.yeoriggun.dto.wholesale.WholesaleGradeDto;
import com.yeoriggun.yeoriggun.service.wholesale.WholesaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wholesale")
@RequiredArgsConstructor
@Tag(name = "도매가", description = "도매가 업데이트")
public class WholesaleController {

    private final WholesaleService wholesaleService;

    @Operation(summary = "도매가 업데이트", description = "xls파일에서 가져옵니다.")
    @PostMapping("/save")
    public String importData() {
        try {
            wholesaleService.loadExcelFromResources();
            return "성공";
        } catch (Exception e) {
            e.printStackTrace();
            return "실패: " + e.getMessage();
        }
    }

    @Operation(summary = "등급별 도매가", description = "특,상,중,하 별 도매가 리스트를 가져옵니다.")
    @GetMapping("/category/grade")
    public List<WholesaleGradeDto> getWholesaleByGrade(@RequestParam(defaultValue = "상") String grade)  {
        return wholesaleService.getByGrade(grade);
    }
}