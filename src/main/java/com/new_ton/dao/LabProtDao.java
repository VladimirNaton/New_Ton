package com.new_ton.dao;

import com.new_ton.domain.entities.LabprotEntity;

import java.util.List;

public interface LabProtDao {
    List<LabprotEntity> findAllByIdpr(int id);
}
