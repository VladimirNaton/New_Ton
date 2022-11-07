package com.new_ton.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "main")
public class MainEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idpr")
    private int idpr;
    @Basic
    @Column(name = "datecr")
    private Timestamp datecr;
    @Basic
    @Column(name = "datepl")
    private Timestamp datepl;
    @Basic
    @Column(name = "datemade")
    private Timestamp datemade;
    @Basic
    @Column(name = "state")
    private Integer state;
    @Basic
    @Column(name = "brend")
    private String brend;
    @Basic
    @Column(name = "nameprod")
    private String nameprod;
    @Basic
    @Column(name = "sp")
    private String sp;
    @Basic
    @Column(name = "percent")
    private Double percent;
    @Basic
    @Column(name = "mass")
    private Double mass;
    @Basic
    @Column(name = "datestart")
    private Timestamp datestart;
    @Basic
    @Column(name = "timemade")
    private Integer timemade;
    @Basic
    @Column(name = "tempprodmin")
    private Double tempprodmin;
    @Basic
    @Column(name = "tempprodmax")
    private Double tempprodmax;
    @Basic
    @Column(name = "operfio")
    private String operfio;
    @Basic
    @Column(name = "deg")
    private Integer deg;
    @Basic
    @Column(name = "labfio")
    private String labfio;
    @Basic
    @Column(name = "numbprot")
    private Integer numbprot;
    @Basic
    @Column(name = "numbpart")
    private Integer numbpart;
    @Basic
    @Column(name = "expdate")
    private Timestamp expdate;
    @Basic
    @Column(name = "dateprot")
    private Timestamp dateprot;
    @Basic
    @Column(name = "filtr")
    private String filtr;
    @Basic
    @Column(name = "comment")
    private String comment;
}
