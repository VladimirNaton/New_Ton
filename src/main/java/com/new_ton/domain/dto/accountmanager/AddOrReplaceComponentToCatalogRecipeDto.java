package com.new_ton.domain.dto.accountmanager;

import lombok.Data;

@Data
public class AddOrReplaceComponentToCatalogRecipeDto {
    private Integer idComponentTable;
    private Integer idCat;
    private String nameSelectedComponent;
    private Integer code;
    private Integer idSelectedElemRecipeTable;
    private boolean outPast;
}
