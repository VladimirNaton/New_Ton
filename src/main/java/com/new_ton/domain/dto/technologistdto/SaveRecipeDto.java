package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

@Data
public class SaveRecipeDto {
    private String comment;
    private Double tempMin;
    private Double tempMax;
    private Double commonWeight;
    private Integer idMain;
    private Double control;
}
