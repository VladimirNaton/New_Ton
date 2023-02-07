package com.new_ton.domain.dto;


import lombok.Data;

import java.util.List;

@Data
public class RecipePageDataDto {
    private SumWeightDto sumWeightDto;
    private List<RecipeMainTableDto> recipeMainTableDtoList;
    private Long collSpanStage1;
    private Long collSpanStage2;
    private Long collSpanStage3;
    private RecipeMainTableDto code8Stage1;
    private RecipeMainTableDto code8Stage2;
    private Integer allTimeMade;
    private String nameProd;
    private Integer mainMass;
    private Integer deg;
    private String dateMade;
    private String currentDate;
}
