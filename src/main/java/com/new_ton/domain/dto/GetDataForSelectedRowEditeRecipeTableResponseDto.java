package com.new_ton.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetDataForSelectedRowEditeRecipeTableResponseDto {
    private Integer id;
    private Integer n;
    private Integer stage;
    private String nameraw;
    private Double percent;
    private Double mass;
    private Double devper;
    private Double devmass;
    private Integer turnmix;
    private Integer timemix;
    private Integer pastpart;
    private Date pastdate;
    private Integer filter;
    private String dateString;
    private Double factMass;
    private Double factMassDev;

    public GetDataForSelectedRowEditeRecipeTableResponseDto(int id, Integer n, Integer stage, String nameraw, Double percent, Double mass, Double devper, Double devmass, Integer turnmix, Integer timemix, Integer pastpart, Date pastdate, Integer filter, Double factMass, Double factMassDev) {
        this.id = id;
        this.n = n;
        this.stage = stage;
        this.nameraw = nameraw;
        this.percent = percent;
        this.mass = mass;
        this.devper = devper;
        this.devmass = devmass;
        this.turnmix = turnmix;
        this.timemix = timemix;
        this.pastpart = pastpart;
        this.pastdate = pastdate;
        this.filter = filter;
        this.factMass = factMass;
        this.factMassDev = factMassDev;
    }
}
