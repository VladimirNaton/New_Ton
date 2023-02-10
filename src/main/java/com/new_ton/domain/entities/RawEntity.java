package com.new_ton.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "raw")
public class RawEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_main")
    private Integer idMain;
    @Column(name = "n")
    private Integer n;
    @Column(name = "stage")
    private Integer stage;
    @Column(name = "code")
    private Integer code;
    @Column(name = "nameraw")
    private String nameraw;
    @Column(name = "percent")
    private Double percent;
    @Column(name = "mass")
    private Double mass;
    @Column(name = "devper")
    private Double devper;
    @Column(name = "devmass")
    private Double devmass;
    @Column(name = "factmass")
    private Double factmass;
    @Column(name = "factmassdev")
    private Double factmassdev;
    @Column(name = "tempdep")
    private Double tempdep;
    @Column(name = "wetdep")
    private Double wetdep;
    @Column(name = "prodtemp")
    private Double prodtemp;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datestart")
    private Date datestart;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datestop")
    private Date datestop;
    @Column(name = "timemade")
    private Integer timemade;
    @Column(name = "turnmix")
    private Integer turnmix;
    @Column(name = "devturn")
    private Integer devturn;
    @Column(name = "timemix")
    private Integer timemix;
    @Column(name = "factturn")
    private Integer factturn;
    @Column(name = "facttimemix")
    private Integer facttimemix;
    @Column(name = "eq")
    private Integer eq;
    @Column(name = "pastpart")
    private Integer pastpart;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pastdate")
    private Date pastdate;
    @Column(name = "filter")
    private Integer filter;
    @Column(name = "component_loaded")
    private Integer componentLoaded;
}
