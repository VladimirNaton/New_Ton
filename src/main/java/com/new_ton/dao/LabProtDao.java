package com.new_ton.dao;

import java.util.List;

public interface LabProtDao {
    List<LabprotEntity> findAllByIdpr(int id);
}
