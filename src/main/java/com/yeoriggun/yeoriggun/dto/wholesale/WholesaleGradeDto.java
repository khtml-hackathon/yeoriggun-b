package com.yeoriggun.yeoriggun.dto.wholesale;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WholesaleGradeDto {
    private String item;
    private String unit;
    private Long averagePrice;
    private String grade;
}