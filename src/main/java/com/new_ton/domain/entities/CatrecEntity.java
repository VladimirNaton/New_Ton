package com.new_ton.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "catrec")
public class CatrecEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_cat")
    private Integer idCat;
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
    @Column(name = "turnmix")
    private Integer turnmix;
    @Column(name = "timemix")
    private Integer timemix;
    @Column(name = "filter")
    private String filter;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pastdate")
    private Date pastdate;
    @Column(name = "pastpart")
    private Integer pastpart;
}
