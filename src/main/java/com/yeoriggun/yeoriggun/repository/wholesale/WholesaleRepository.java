package com.yeoriggun.yeoriggun.repository.wholesale;

import com.yeoriggun.yeoriggun.dto.wholesale.WholesaleGradeDto;
import com.yeoriggun.yeoriggun.entity.wholesale.Wholesale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WholesaleRepository extends JpaRepository<Wholesale, Long> {
    @Query("SELECT new com.yeoriggun.yeoriggun.dto.wholesale.WholesaleGradeDto(w.item, w.unit, w.averagePrice, w.grade) " +
            "FROM Wholesale w " +
            "WHERE w.grade = :grade " +
            "ORDER BY w.item")
    List<WholesaleGradeDto> findByGrade(String grade);
}