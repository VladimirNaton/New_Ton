package com.new_ton.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeightLogTableResponseDto {

    private int draw;

    private List<CalibrationTableDto> data;

    private Long recordsTotal;

    private Long recordsFiltered;
}
