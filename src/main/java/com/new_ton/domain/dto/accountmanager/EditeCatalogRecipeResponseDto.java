package com.new_ton.domain.dto.accountmanager;

import com.new_ton.domain.dto.technologistdto.EditeRecipeTableDto;
import lombok.Data;

import java.util.List;

@Data
public class EditeCatalogRecipeResponseDto {
    private Integer draw;

    private List<EditeCatalogRecipeTableDto> data;
}
