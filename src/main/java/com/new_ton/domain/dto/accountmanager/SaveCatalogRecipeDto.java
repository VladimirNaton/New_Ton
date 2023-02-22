package com.new_ton.domain.dto.accountmanager;

import lombok.Data;

@Data
public class SaveCatalogRecipeDto {
    private Double tempMin;
    private Double tempMax;
    private Double commonWeight;
    private Integer idCat;
    private Double control;
}
