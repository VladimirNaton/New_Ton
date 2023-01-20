package com.new_ton.service;


import com.new_ton.domain.dto.RequestDataTableDto;
import com.new_ton.domain.dto.ProductResponseDto;

public interface ProductTableService {
    ProductResponseDto getProductTableDate(RequestDataTableDto requestDataTableDto);
}
