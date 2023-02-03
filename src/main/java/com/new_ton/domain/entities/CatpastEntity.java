package com.new_ton.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "catpast")
public class CatpastEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    @Column(name = "namepast")
    private String namepast;
    @Column(name = "part")
    private Integer part;
}
