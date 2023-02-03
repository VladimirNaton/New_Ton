package com.new_ton.repository;

import com.new_ton.domain.entities.CatrecEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatrecRepository extends JpaRepository<CatrecEntity, Integer> {
    List<CatrecEntity> findAllByIdCat(Integer id);
}
