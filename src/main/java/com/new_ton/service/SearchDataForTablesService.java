package com.new_ton.service;

import com.new_ton.domain.dto.*;
import com.new_ton.domain.dto.EditeRecipeTableRequestDto;

public interface SearchDataForTablesService {

    TechnologistPageLeftTableResponseDto getDataForTechnologistLeftTable(RequestTechnologistPageLeftTableDto requestTechnologistPageLeftTableDto);

    TechnologistPageRightTableResponseDto getDataForTechnologistRightTable(RequestTechnologistPageRightTableDto requestTechnologistPageRightTableDto);

    CatalogDtoSelectedRow searchDataFromSelectedCatalogRow(Integer idProd);

    EditeRecipeResponseDto searchDataForEditeRecipeTable(EditeRecipeTableRequestDto editeRecipeTableRequestDto);

    GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeRecipe(Integer idProd);

    EditeRecipeCatalogTableResponseDto getDataForEditeRecipeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeComponentTableRequestDto);

    GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(Integer id);
}
