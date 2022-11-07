package com.new_ton.domain.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "labprot")
public class LabprotEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idpr")
    private Integer idpr;
    @Basic
    @Column(name = "indicator")
    private String indicator;
    @Basic
    @Column(name = "nd")
    private String nd;
    @Basic
    @Column(name = "allvalues")
    private String allvalues;
    @Basic
    @Column(name = "dev")
    private String dev;
    @Basic
    @Column(name = "result")
    private String result;
}
