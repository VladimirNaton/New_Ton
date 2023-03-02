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
@Table(name = "user_fio")
public class UserFioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "user_fio")
    private String userFio;
}
