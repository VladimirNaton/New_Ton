package com.new_ton.dao;

import com.new_ton.domain.dto.CalibrationTableEntityDto;
import com.new_ton.domain.dto.WeighingLogRequestDto;

import java.util.List;

public interface CalibrationTableDao {
    List<String> fioOperList();
    CalibrationTableEntityDto getDataCalibrationTable(WeighingLogRequestDto weighingLogRequestDto);
}
