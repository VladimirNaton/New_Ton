package com.new_ton.service;

import com.new_ton.domain.dto.accountmanager.ReturnRecipeToTechnologistRequestDto;
import com.new_ton.domain.dto.technologistdto.*;

public interface UpdateDataService {
    boolean moveCatalogRowToMain(Integer id);

    boolean sentProductToTeller(SendProductToAccountManagerDto sendProductToAccountManagerDto);

    boolean deleteSelectedRowFromRecipeTable(Integer id);

    boolean addComponentToRecipe(AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto);

    boolean replaceSelectedRecipeElement(AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto);

    boolean updateSelectedRowOfRecipe(UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto);

    boolean saveRecipe(SaveRecipeDto saveRecipeDto);

    boolean updateDataByCatalogFromMain(Integer idMain);

    boolean returnRecipeToTechnologist(ReturnRecipeToTechnologistRequestDto returnRecipeToTechnologistRequestDto);
    boolean sendToProduction(ReturnRecipeToTechnologistRequestDto returnRecipeToTechnologistRequestDto);
}
