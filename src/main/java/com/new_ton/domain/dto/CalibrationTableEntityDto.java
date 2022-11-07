package com.new_ton.domain.dto;


import com.lider.domain.entities.CalibrationEntity;
import lombok.Data;

import java.util.List;

@Data
public class CalibrationTableEntityDto {
    List<CalibrationEntity> calibrationEntityList;
    Long totalElements;
}
