package com.new_ton.domain.dto;

import lombok.Data;

@Data
public class CatalogDtoByLeftTable {
    private int idpr;
    private String brend;
    private String nameprod;

    public CatalogDtoByLeftTable(Integer idpr, String brend, String nameprod) {
        this.idpr = idpr;
        this.brend = brend;
        this.nameprod = nameprod;
    }
}
