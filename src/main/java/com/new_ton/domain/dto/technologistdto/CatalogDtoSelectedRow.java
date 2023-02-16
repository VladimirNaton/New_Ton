package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.Date;

@Data
public class CatalogDtoSelectedRow {
    private Integer id;
    private Date datecr;
    private String dataCreate;
    private String brend;
    private String nameprod;
    private Double mass;
    private Double percent;
    private Double tempprodmin;
    private Double tempprodmax;


    public CatalogDtoSelectedRow(Date datecr, String brend, String nameprod, Double mass, Double percent, Double tempprodmin, Double tempprodmax) {
        this.datecr = datecr;
        this.brend = brend;
        this.nameprod = nameprod;
        this.mass = mass;
        this.percent = percent;
        this.tempprodmin = tempprodmin;
        this.tempprodmax = tempprodmax;
    }
}
