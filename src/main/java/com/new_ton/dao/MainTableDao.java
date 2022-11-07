package com.new_ton.dao;

import java.util.Optional;

public interface MainTableDao {
    ProductTableResponseEntityDto getProductTableData(ProductTableRequestDto productTableRequestDto);

    Optional<MainEntity> findByIdpr(int id);
}
