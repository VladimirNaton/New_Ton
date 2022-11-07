package com.new_ton.domain.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "print")
public class PrintEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "code")
    private Integer code;
    @Basic
    @Column(name = "idpr")
    private Integer idpr;
}
