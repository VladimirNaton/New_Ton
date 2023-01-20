package com.new_ton.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CatalogDto {
    private int idpr;
    private Date datecr;
    private String brend;
    private String nameprod;
    private Double percent;
    private Double mass;
    private Double tempprodmin;
    private Double tempprodmax;

    public CatalogDto(Integer idpr, String brend, String nameprod) {
        this.idpr = idpr;
        this.brend = brend;
        this.nameprod = nameprod;
    }


}
