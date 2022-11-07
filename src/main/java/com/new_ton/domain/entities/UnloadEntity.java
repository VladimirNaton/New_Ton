package com.new_ton.domain.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "unload")
public class UnloadEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idpr")
    private Integer idpr;
    @Basic
    @Column(name = "numb")
    private Integer numb;
    @Basic
    @Column(name = "barrel")
    private String barrel;
    @Basic
    @Column(name = "mass")
    private Double mass;
}
