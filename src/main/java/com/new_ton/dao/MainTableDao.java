package com.new_ton.dao;

import com.new_ton.domain.dto.RequestDataTableDto;
import com.new_ton.domain.dto.ProductTableResponseEntityDto;
import com.new_ton.domain.entities.MainEntity;

import java.util.Optional;

public interface MainTableDao {
    ProductTableResponseEntityDto getProductTableData(RequestDataTableDto requestDataTableDto);

    Optional<MainEntity> findByIdpr(int id);
}
