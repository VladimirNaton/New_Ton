package com.new_ton.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class TechnologistPageRightTableResponseDto {
    private int draw;
    private List<MainTableDto> data;
    private Long recordsTotal;
    private Long recordsFiltered;
}
