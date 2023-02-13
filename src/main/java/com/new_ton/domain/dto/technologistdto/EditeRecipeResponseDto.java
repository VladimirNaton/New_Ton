package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.List;

@Data
public class EditeRecipeResponseDto {

    private Integer draw;

    private List<EditeRecipeTableDto> data;
}
