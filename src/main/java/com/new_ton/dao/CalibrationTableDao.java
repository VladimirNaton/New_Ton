package com.new_ton.dao;

import java.util.List;

public interface CalibrationTableDao {
    List<String> fioOperList();
    CalibrationTableEntityDto getDataCalibrationTable(WeighingLogRequestDto weighingLogRequestDto);
}
