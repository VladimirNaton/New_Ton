package com.new_ton.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "calibration")
public class CalibrationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @Basic
    @Column(name = "nw")
    private Integer nw;
    @Basic
    @Column(name = "plmass")
    private Double plmass;
    @Basic
    @Column(name = "factmass")
    private Double factmass;
    @Basic
    @Column(name = "operfio")
    private String operfio;
}
