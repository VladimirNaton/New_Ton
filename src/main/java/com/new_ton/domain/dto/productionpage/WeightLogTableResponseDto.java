package com.new_ton.domain.dto.productionpage;

import com.new_ton.domain.dto.productionpage.CalibrationTableDto;
import lombok.Data;

import java.util.List;

@Data
public class WeightLogTableResponseDto {

    private int draw;

    private List<CalibrationTableDto> data;

    private Long recordsTotal;

    private Long recordsFiltered;
}
