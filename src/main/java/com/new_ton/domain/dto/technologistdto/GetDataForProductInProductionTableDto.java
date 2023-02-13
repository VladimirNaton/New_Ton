package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.Date;

@Data
public class GetDataForProductInProductionTableDto {
    private Integer idpr;
    private Date datecr;
    private Date datepl;
    private String brend;
    private String nameprod;
    private Double percent;
    private Integer mass;
    private Double tempprodmin;
    private Double tempprodmax;
    private String comment;
    private String datecrStr;
    private String dateplStr;


    public GetDataForProductInProductionTableDto(Integer idpr, Date datecr, Date datepl, String brend, String nameprod, Double percent, Integer mass, Double tempprodmin, Double tempprodmax, String comment) {
        this.idpr = idpr;
        this.datecr = datecr;
        this.datepl = datepl;
        this.brend = brend;
        this.nameprod = nameprod;
        this.percent = percent;
        this.mass = mass;
        this.tempprodmin = tempprodmin;
        this.tempprodmax = tempprodmax;
        this.comment = comment;
    }
}
