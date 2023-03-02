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
    private Double mass;
    private Double tempprodmin;
    private Double tempprodmax;
    private String comment;
    private String datecrStr;
    private Integer state;
    private String dateplStr;
    private String stateStr;


    public GetDataForProductInProductionTableDto(Integer idpr, Date datecr, Date datepl, String brend, String nameprod, Double percent, Double mass, Double tempprodmin, Double tempprodmax, String comment, Integer state) {
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
        this.state = state;
    }
}
