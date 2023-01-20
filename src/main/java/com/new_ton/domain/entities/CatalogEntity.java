package com.new_ton.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "catalog")
public class CatalogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idpr")
    private int idpr;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecr")
    private Date datecr;

    @Column(name = "brend")
    private String brend;

    @Column(name = "nameprod")
    private String nameprod;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "mass")
    private Double mass;

    @Column(name = "tempprodmin")
    private Double tempprodmin;

    @Column(name = "tempprodmax")
    private Double tempprodmax;
}
