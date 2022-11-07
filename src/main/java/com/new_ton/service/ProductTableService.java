package com.new_ton.service;


import com.lider.domain.dto.ProductTableRequestDto;
import com.lider.domain.dto.ProductTableResponseDto;

public interface ProductTableService {
    ProductTableResponseDto getProductTableDate(ProductTableRequestDto productTableRequestDto);
}
