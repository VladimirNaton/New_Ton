package com.new_ton.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetDataForProductInProductionTableRequestDto {
    private List<GetDataForProductInProductionTableDto> data;
    private Integer draw = 1;

}
