package com.new_ton.domain.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class MainTableDto {
    private Integer idpr;
    private Date datecr;
    private Date datepl;
    private Date datemade;
    private Integer state;
    private String brend;
    private String nameprod;
    private String sp;
    private Double percent;
    private Double mass;
    private Date datestart;
    private Integer timemade;
    private Double tempprodmin;
    private Double tempprodmax;
    private String operfio;
    private Integer deg;
    private String labfio;
    private Integer numbprot;
    private Integer numbpart;
    private Date expdate;
    private Date dateprot;
    private String filtr;
    private String comment;

    public MainTableDto(Integer idpr, String brend, String nameprod, Date datecr, Double percent, Double mass) {
        this.idpr = idpr;
        this.brend = brend;
        this.nameprod = nameprod;
        this.datecr = datecr;
        this.percent = percent;
        this.mass = mass;
    }


}
