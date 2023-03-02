package com.new_ton.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "catstate")
public class CatstateEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "state")
    private String state;
    @Column(name = "code")
    private Integer code;
}
