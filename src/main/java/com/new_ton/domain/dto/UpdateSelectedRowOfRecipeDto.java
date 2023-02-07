package com.new_ton.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateSelectedRowOfRecipeDto {
    private Integer sequenceNumber;
    private Integer stage;
    private Double percent;
    private Double mass;
    private Integer mixing;
    private Integer mixingTime;
    private Integer filter;
    private Double infelicityPercent;
    private Double infelicityMass;
    private Integer part;
    private Date pastDate;
    private Integer selectedComponentId;
}
