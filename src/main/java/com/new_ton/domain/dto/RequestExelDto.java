package com.new_ton.domain.dto;

import lombok.Data;

@Data
public class RequestExelDto {
    private String orderColumn;
    private String orderType;
    private String startDate;
    private String endDate;
    private String scales;
    private String fioOper;
}
