package com.new_ton.domain.dto;

import lombok.Data;

@Data
public class EditeRecipeComponentTableRequestDto {
    private Integer start;
    private Integer length;
    private Integer draw;
    private Integer codeSearch;
    private String findComponent;
}
