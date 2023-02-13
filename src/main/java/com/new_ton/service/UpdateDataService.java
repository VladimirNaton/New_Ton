package com.new_ton.service;

import com.new_ton.domain.dto.technologistdto.AddOrReplaceComponentToRecipeRequestDto;
import com.new_ton.domain.dto.technologistdto.SaveRecipeDto;
import com.new_ton.domain.dto.technologistdto.SendProductToAccountManagerDto;
import com.new_ton.domain.dto.technologistdto.UpdateSelectedRowOfRecipeDto;

public interface UpdateDataService {
    boolean moveCatalogRowToMain(Integer id);

    boolean sentProductToTeller(SendProductToAccountManagerDto sendProductToAccountManagerDto);

    boolean deleteSelectedRowFromRecipeTable(Integer id);

    boolean addComponentToRecipe(AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto);

    boolean replaceSelectedRecipeElement(AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto);

    boolean updateSelectedRowOfRecipe(UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto);

    boolean saveRecipe(SaveRecipeDto saveRecipeDto);

    boolean updateDataByCatalogFromMain(Integer idMain);
}
