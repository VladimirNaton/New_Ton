package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.Date;

@Data
public class CatalogDtoSelectedRow {
    private Date datecr;
    private String dataCreate;
    private String brend;
    private String nameprod;
    private Integer mass;

    public CatalogDtoSelectedRow(Date datecr, String brend, String nameprod, Integer mass) {
        this.datecr = datecr;
        this.brend = brend;
        this.nameprod = nameprod;
        this.mass = mass;
    }


}
