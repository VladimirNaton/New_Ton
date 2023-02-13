package com.new_ton.domain.dto.technologistdto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

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
    private Integer turnmix;
    private Integer timemix;
    private Integer pastpart;
    private Date pastdate;
    private String strDate;

    public EditeRecipeTableDto(Integer id, Integer n, Integer stage, Integer code, String nameraw, Double percent, Double mass, Double devper, Double devmass, Integer turnmix, Integer timemix, Integer pastpart, Date pastdate) {
        this.id = id;
        this.n = n;
        this.stage = stage;
        this.code = code;
        this.nameraw = nameraw;
        this.percent = percent;
        this.mass = mass;
        this.devper = devper;
        this.devmass = devmass;
        this.turnmix = turnmix;
        this.timemix = timemix;
        this.pastpart = pastpart;
        this.pastdate = pastdate;
    }
}
