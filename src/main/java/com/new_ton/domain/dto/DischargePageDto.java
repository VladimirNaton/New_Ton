package com.new_ton.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DischargePageDto {
    private Integer idpr;
    private Integer numb;
    private String barrel;
    private Double mass;
}
