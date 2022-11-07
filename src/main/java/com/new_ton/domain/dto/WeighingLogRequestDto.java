package com.new_ton.domain.dto;

import lombok.Data;

@Data
public class WeighingLogRequestDto {
    private Integer start;
    private Integer length;
    private Integer draw;
    private String orderColumn;
    private String orderType;
    private String startDate;
    private String endDate;
    private String scales;
    private String fioOper;
    private String requestFlag;
}
