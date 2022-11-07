package com.new_ton.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "raw")
public class RawEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idpr")
    private Integer idpr;
    @Basic
    @Column(name = "n")
    private Integer n;
    @Basic
    @Column(name = "stage")
    private Integer stage;
    @Basic
    @Column(name = "code")
    private Integer code;
    @Basic
    @Column(name = "nameraw")
    private String nameraw;
    @Basic
    @Column(name = "percent")
    private Double percent;
    @Basic
    @Column(name = "mass")
    private Double mass;
    @Basic
    @Column(name = "devper")
    private Double devper;
    @Basic
    @Column(name = "devmass")
    private Double devmass;
    @Basic
    @Column(name = "factmass")
    private Double factmass;
    @Basic
    @Column(name = "factmassdev")
    private Double factmassdev;
    @Basic
    @Column(name = "tempdep")
    private Double tempdep;
    @Basic
    @Column(name = "wetdep")
    private Double wetdep;
    @Basic
    @Column(name = "prodtemp")
    private Double prodtemp;
    @Basic
    @Column(name = "datestart")
    private Timestamp datestart;
    @Basic
    @Column(name = "datestop")
    private Timestamp datestop;
    @Basic
    @Column(name = "timemade")
    private Integer timemade;
    @Basic
    @Column(name = "turnmix")
    private Integer turnmix;
    @Basic
    @Column(name = "devturn")
    private Integer devturn;
    @Basic
    @Column(name = "timemix")
    private Integer timemix;
    @Basic
    @Column(name = "factturn")
    private Integer factturn;
    @Basic
    @Column(name = "facttimemix")
    private Integer facttimemix;
    @Basic
    @Column(name = "eq")
    private Integer eq;
    @Basic
    @Column(name = "pastpart")
    private Integer pastpart;
    @Basic
    @Column(name = "pastdate")
    private Timestamp pastdate;
}
