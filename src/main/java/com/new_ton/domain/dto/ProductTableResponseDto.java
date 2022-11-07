package com.new_ton.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductTableResponseDto {
    private int draw;
    private List<ProductTableDto> data;
    private Long recordsTotal;
    private Long recordsFiltered;
}
