package com.new_ton.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RecipeMainTableDto {
    private Integer stage;
    private Integer code;
    private String nameraw;
    private String percent;
    private String mass;
    private String devper;
    private String devmass;
    private String factmass;
    private String factmassdev;
    private String tempdep;
    private String wetdep;
    private String prodtemp;
    private String datestart;
    private String datestop;
    private Integer timemade;
    private Integer turnmix;
    private Integer devturn;
    private Integer timemix;
    private Integer factturn;
    private Integer facttimemix;
    private Integer eq;
    private Integer pastpart;
    private String pastdate;
}
