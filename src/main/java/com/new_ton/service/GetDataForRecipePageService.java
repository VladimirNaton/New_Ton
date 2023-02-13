package com.new_ton.service;

import com.new_ton.domain.dto.productionpage.RecipePageDataDto;

public interface GetDataForRecipePageService {
    RecipePageDataDto getDataForRecipePage(int idProd);
}
