package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.Date;

@Data
public class GetDataForInformationStringEditeRecipeDto {

    private String brend;

    private Date dateCreate;

    private String nameProd;

    private Double tempMin;

    private Double tempMax;

    private Integer mass;

    private Double percent;

    private String dateString;

    private String comment;


    public GetDataForInformationStringEditeRecipeDto(String brend, Date dateCreate, String nameProd, Double tempMin, Double tempMax, Integer mass, Double percent, String comment) {
        this.brend = brend;
        this.dateCreate = dateCreate;
        this.nameProd = nameProd;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.mass = mass;
        this.percent = percent;
        this.comment = comment;
    }
}
