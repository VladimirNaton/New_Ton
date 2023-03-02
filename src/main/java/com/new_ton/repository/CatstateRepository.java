package com.new_ton.repository;

import com.new_ton.domain.entities.CatstateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CatstateRepository extends JpaRepository<CatstateEntity, Integer> {

    @Query("select cs.state from CatstateEntity cs where cs.code = ?1")
    String getState(Integer code);
}
