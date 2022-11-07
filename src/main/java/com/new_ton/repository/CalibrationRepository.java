package com.new_ton.repository;


import com.lider.domain.entities.CalibrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalibrationRepository extends JpaRepository<CalibrationEntity, Integer> {
    @Query("select distinct ce.operfio from CalibrationEntity ce where ce.operfio is not null")
    List<String> findDistinctByOperFioNoDate();
}

