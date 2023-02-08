package com.new_ton.service;

import com.new_ton.domain.dto.AddOrReplaceComponentToRecipeRequestDto;
import com.new_ton.domain.dto.SaveRecipeDto;
import com.new_ton.domain.dto.SendProductToTellerDto;
import com.new_ton.domain.dto.UpdateSelectedRowOfRecipeDto;

public interface UpdateDataService {
    boolean moveCatalogRowToMain(Integer id);

    boolean sentProductToTeller(SendProductToTellerDto sendProductToTellerDto);

    boolean deleteSelectedRowFromRecipeTable(Integer id);

    boolean addComponentToRecipe(AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto);

    boolean replaceSelectedRecipeElement(AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto);

    boolean updateSelectedRowOfRecipe(UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto);

    boolean saveRecipe(SaveRecipeDto saveRecipeDto);

    boolean updateDataByCatalogFromMain(Integer idMain);
}
