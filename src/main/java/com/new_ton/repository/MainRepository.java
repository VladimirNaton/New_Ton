package com.new_ton.repository;


import com.lider.domain.entities.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainRepository extends JpaRepository<MainEntity, Integer> {
    Optional<MainEntity> findByIdpr(int id);
}

