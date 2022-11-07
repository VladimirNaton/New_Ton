package com.new_ton.dao;

import com.new_ton.domain.entities.UnloadEntity;

import java.util.List;

public interface UploadTableDao {
    List<UnloadEntity> getUnloadEntityById(int id);
}
