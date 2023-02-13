package com.new_ton.domain.dto.productionpage;


import lombok.Data;

@Data
public class RequestDataTableDto {

    private Integer start;
    private Integer length;
    private Integer draw;
    private String orderColumn;
    private String orderType;
    private String requestFlag;
    private String typeDate;
    private String startDate;
    private String endDate;
    private String brend;
    private String productName;
    private String specification;
}
