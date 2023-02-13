package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

@Data
public class RequestTechnologistPageRightTableDto {
    private Integer start;
    private Integer length;
    private Integer draw;
    private String orderColumn;
    private String orderType;
    private String brend;
    private String productName;
    private String dateCreate;
}
