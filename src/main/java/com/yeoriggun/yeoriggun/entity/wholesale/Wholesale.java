package com.yeoriggun.yeoriggun.entity.wholesale;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wholesale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wholesale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wholesaleId;

    private String item;
    private String unit;
    private Long averagePrice;
    private String regDate;
    private Long ratio;
    private String grade;
}