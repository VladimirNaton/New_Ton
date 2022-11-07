package com.new_ton.domain.dto;

import com.lider.domain.entities.MainEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductTableResponseEntityDto {
    List<MainEntity> mainEntityList;
    Long recordsTotal;
}
