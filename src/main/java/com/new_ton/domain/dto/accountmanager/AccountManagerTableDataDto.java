package com.new_ton.domain.dto.accountmanager;

import lombok.Data;

import java.util.Date;


@Data
public class AccountManagerTableDataDto {

    private Integer idpr;
    private Date datecr;
    private String brend;
    private String nameprod;
    private Double percent;
    private Double mass;
    private Double tempprodmin;
    private Double tempprodmax;
    private String comment;
    private String strDate;

    public AccountManagerTableDataDto(Integer idpr, Date datecr, String brend, String nameprod, Double percent, Double mass, Double tempprodmin, Double tempprodmax, String comment) {
        this.idpr = idpr;
        this.datecr = datecr;
        this.brend = brend;
        this.nameprod = nameprod;
        this.percent = percent;
        this.mass = mass;
        this.tempprodmin = tempprodmin;
        this.tempprodmax = tempprodmax;
        this.comment = comment;
    }
}
