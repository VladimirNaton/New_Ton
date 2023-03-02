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
@Table(name = "comment_to_stage")
public class CommentToStageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_main")
    private Integer idMain;
    @Column(name = "id_stage")
    private Integer idStage;
    @Column(name = "comment")
    private String comment;
}

