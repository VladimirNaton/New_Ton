package com.new_ton.repository;


import com.new_ton.domain.entities.LabprotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabprotRepository extends JpaRepository<LabprotEntity, Integer> {
    List<LabprotEntity> findAllByIdpr(int id);

    void deleteAllByIdpr(Integer idProd);
}
