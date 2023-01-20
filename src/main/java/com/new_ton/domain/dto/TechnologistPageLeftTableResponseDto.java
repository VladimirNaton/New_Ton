package com.new_ton.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class TechnologistPageLeftTableResponseDto {
    private int draw;
    private List<CatalogDto> data;
    private Long recordsTotal;
    private Long recordsFiltered;
}
