package com.new_ton.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@Entity
@Table(name = "cateq")
public class CateqEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "eq")
    private String eq;
    @Column(name = "code")
    private Integer code;
}
