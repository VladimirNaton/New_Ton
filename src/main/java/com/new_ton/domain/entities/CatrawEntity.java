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
@Table(name = "catraw")
public class CatrawEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "code_1c")
    private Integer code1C;
    @Column(name = "nameraw")
    private String nameraw;
    @Column(name = "code")
    private Integer code;
    @Column(name = "part")
    private Integer part;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
}
