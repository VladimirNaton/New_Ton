package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.Date;

@Data
public class CatalogDtoByLeftTable {
    private int idpr;
    private Date datecr;
    private String brend;
    private String nameprod;
    private Double percent;
    private Double mass;
    private Double tempprodmin;
    private Double tempprodmax;

    private String strDate;

    public CatalogDtoByLeftTable(int idpr, Date datecr, String brend, String nameprod, Double percent, Double mass, Double tempprodmin, Double tempprodmax) {
        this.idpr = idpr;
        this.datecr = datecr;
        this.brend = brend;
        this.nameprod = nameprod;
        this.percent = percent;
        this.mass = mass;
        this.tempprodmin = tempprodmin;
        this.tempprodmax = tempprodmax;
    }
}
