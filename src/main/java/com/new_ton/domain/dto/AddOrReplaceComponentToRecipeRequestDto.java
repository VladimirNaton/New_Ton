package com.new_ton.domain.dto;

import lombok.Data;

@Data
public class AddOrReplaceComponentToRecipeRequestDto {
    private Integer idComponentTable;
    private Integer idMain;
    private String nameSelectedComponent;
    private Integer code;
    private Integer idSelectedElemRecipeTable;
}
