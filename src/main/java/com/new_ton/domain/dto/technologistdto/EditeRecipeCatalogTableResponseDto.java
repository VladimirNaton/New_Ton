package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.List;

@Data
public class EditeRecipeCatalogTableResponseDto {
    private Integer draw;
    private List<EditeRecipeComponentTableDto> data;
    private Long recordsTotal;
    private Long recordsFiltered;
}
