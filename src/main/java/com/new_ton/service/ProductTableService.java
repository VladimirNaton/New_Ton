package com.new_ton.service;


import com.new_ton.domain.dto.productionpage.RequestDataTableDto;
import com.new_ton.domain.dto.productionpage.ProductResponseDto;

public interface ProductTableService {
    ProductResponseDto getProductTableDate(RequestDataTableDto requestDataTableDto);
}
