package com.new_ton.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "main")
public class MainEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idpr")
    private Integer idpr;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecr")
    private Date datecr;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datepl")
    private Date datepl;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datemade")
    private Date datemade;
    @Column(name = "state")
    private Integer state;
    @Column(name = "brend")
    private String brend;
    @Column(name = "nameprod")
    private String nameprod;
    @Column(name = "sp")
    private String sp;
    @Column(name = "percent")
    private Double percent;
    @Column(name = "mass")
    private Double mass;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datestart")
    private Date datestart;
    @Column(name = "timemade")
    private Integer timemade;
    @Column(name = "tempprodmin")
    private Double tempprodmin;
    @Column(name = "tempprodmax")
    private Double tempprodmax;
    @Column(name = "operfio")
    private String operfio;
    @Column(name = "deg")
    private Integer deg;
    @Column(name = "labfio")
    private String labfio;
    @Column(name = "numbprot")
    private Integer numbprot;
    @Column(name = "numbpart")
    private Integer numbpart;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expdate")
    private Date expdate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateprot")
    private Date dateprot;
    @Column(name = "filtr")
    private String filtr;
    @Column(name = "comment")
    private String comment;
    @Column(name = "idcat")
    private Integer idCat;
}
