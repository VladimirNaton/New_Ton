package com.new_ton.service;

import com.new_ton.domain.dto.accountmanager.AccountManagerTableDataResponseDto;
import com.new_ton.domain.dto.accountmanager.AccountManagerTableRequestDto;
import com.new_ton.domain.dto.technologistdto.EditeRecipeTableRequestDto;
import com.new_ton.domain.dto.technologistdto.*;

public interface SearchDataForTablesService {

    TechnologistPageLeftTableResponseDto getDataForTechnologistLeftTable(RequestTechnologistPageLeftTableDto requestTechnologistPageLeftTableDto);

    TechnologistPageRightTableResponseDto getDataForTechnologistRightTable(RequestTechnologistPageRightTableDto requestTechnologistPageRightTableDto);

    CatalogDtoSelectedRow searchDataFromSelectedCatalogRow(Integer idProd);

    EditeRecipeResponseDto searchDataForEditeRecipeTable(EditeRecipeTableRequestDto editeRecipeTableRequestDto);

    GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeRecipe(Integer idProd);

    EditeRecipeCatalogTableResponseDto getDataForEditeRecipeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeComponentTableRequestDto);

    GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(Integer id);

    GetDataForProductInProductionTableRequestDto getDataForProductInProductionTable();

    AccountManagerTableDataResponseDto getDataForAccountManagerTable(AccountManagerTableRequestDto accountManagerTableRequestDto);
}
