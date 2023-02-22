package com.new_ton.domain.dto.accountmanager;

import lombok.Data;


@Data
public class EditeCatalogRecipeTableDto {
    private Integer id;
    private Integer n;
    private Integer stage;
    private Integer code;
    private String nameraw;
    private Double percent;
    private Double mass;
    private Double devper;
    private Double devmass;

    public EditeCatalogRecipeTableDto(Integer id, Integer n, Integer stage, Integer code, String nameraw, Double percent, Double mass, Double devper, Double devmass) {
        this.id = id;
        this.n = n;
        this.stage = stage;
        this.code = code;
        this.nameraw = nameraw;
        this.percent = percent;
        this.mass = mass;
        this.devper = devper;
        this.devmass = devmass;
    }
}
