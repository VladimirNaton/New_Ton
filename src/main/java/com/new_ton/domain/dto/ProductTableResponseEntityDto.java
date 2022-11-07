package com.new_ton.domain.dto;

import com.new_ton.domain.entities.MainEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductTableResponseEntityDto {
    List<MainEntity> mainEntityList;
    Long recordsTotal;
}
