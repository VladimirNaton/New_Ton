package com.new_ton.domain.dto.accountmanager;

import lombok.Data;

import java.util.List;

@Data
public class AccountManagerTableDataResponseDto {
    private Integer draw;
    private List<AccountManagerTableDataDto> data;
    private Long recordsTotal;
    private Long recordsFiltered;
}
