package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.List;

@Data
public class TechnologistPageLeftTableResponseDto {
    private int draw;
    private List<CatalogDtoByLeftTable> data;
    private Long recordsTotal;
    private Long recordsFiltered;
}
