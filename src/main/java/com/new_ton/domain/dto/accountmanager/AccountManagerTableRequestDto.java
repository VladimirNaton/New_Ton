package com.new_ton.domain.dto.accountmanager;

import lombok.Data;

@Data
public class AccountManagerTableRequestDto {
    private Integer start;
    private Integer length;
    private Integer draw;
    private String searchValue;
}
