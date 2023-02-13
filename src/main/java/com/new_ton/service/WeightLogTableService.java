package com.new_ton.service;

import com.new_ton.domain.dto.productionpage.WeighingLogRequestDto;
import com.new_ton.domain.dto.productionpage.WeightLogTableResponseDto;

public interface WeightLogTableService {
    WeightLogTableResponseDto getWeightLogTableData(WeighingLogRequestDto weighingLogRequestDto);
}
