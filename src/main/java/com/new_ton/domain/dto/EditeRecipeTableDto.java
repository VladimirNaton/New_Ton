package com.new_ton.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EditeRecipeTableDto {
    private Integer id;
    private Integer n;
    private Integer stage;
    private Integer code;
    private String nameraw;
    private Double percent;
    private Double mass;
    private Double devper;
    private Double devmass;

}
