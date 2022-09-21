package com.new_ton.domain.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_role")
    private String userRole;
}
