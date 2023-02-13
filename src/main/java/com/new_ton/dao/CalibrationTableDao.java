package com.new_ton.dao;

import com.new_ton.domain.dto.productionpage.CalibrationTableEntityDto;
import com.new_ton.domain.dto.productionpage.WeighingLogRequestDto;

import java.util.List;

public interface CalibrationTableDao {
    List<String> fioOperList();
    CalibrationTableEntityDto getDataCalibrationTable(WeighingLogRequestDto weighingLogRequestDto);
}
