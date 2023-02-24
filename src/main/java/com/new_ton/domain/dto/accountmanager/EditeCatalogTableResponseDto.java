package com.new_ton.domain.dto.accountmanager;


import lombok.Data;

import java.util.List;

@Data
public class EditeCatalogTableResponseDto {
    private Integer draw;
    private List<ComponentTableDto> data;
    private Long recordsTotal;
    private Long recordsFiltered;
}
