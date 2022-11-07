package com.new_ton.service;

import com.new_ton.domain.dto.WeighingLogRequestDto;
import com.new_ton.domain.dto.WeightLogTableResponseDto;

public interface WeightLogTableService {
    WeightLogTableResponseDto getWeightLogTableData(WeighingLogRequestDto weighingLogRequestDto);
}
