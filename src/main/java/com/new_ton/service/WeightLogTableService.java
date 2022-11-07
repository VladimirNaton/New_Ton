package com.new_ton.service;

import com.lider.domain.dto.WeighingLogRequestDto;
import com.lider.domain.dto.WeightLogTableResponseDto;

public interface WeightLogTableService {
    WeightLogTableResponseDto getWeightLogTableData(WeighingLogRequestDto weighingLogRequestDto);
}
