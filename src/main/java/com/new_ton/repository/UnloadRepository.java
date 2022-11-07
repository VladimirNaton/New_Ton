package com.new_ton.repository;

import com.lider.domain.entities.UnloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnloadRepository extends JpaRepository<UnloadEntity, Integer> {
    List<UnloadEntity> findAllByIdpr(int id);
}
