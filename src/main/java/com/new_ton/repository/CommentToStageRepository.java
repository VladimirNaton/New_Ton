package com.new_ton.repository;

import com.new_ton.domain.entities.CommentToStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentToStageRepository extends JpaRepository<CommentToStageEntity, Integer> {

    @Query("select cs from CommentToStageEntity cs where cs.idMain = ?1 and cs.idStage = ?2")
    Optional<CommentToStageEntity> findByIdMainAndIdStage(Integer idMain, Integer idStage);
}
