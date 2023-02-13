package com.new_ton.domain.dto.productionpage;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDto {
    private int draw;
    private List<ProductTableDto> data;
    private Long recordsTotal;
    private Long recordsFiltered;
}
