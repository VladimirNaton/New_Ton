package com.new_ton.service;


import com.new_ton.domain.dto.ProductTableRequestDto;
import com.new_ton.domain.dto.ProductTableResponseDto;

public interface ProductTableService {
    ProductTableResponseDto getProductTableDate(ProductTableRequestDto productTableRequestDto);
}
