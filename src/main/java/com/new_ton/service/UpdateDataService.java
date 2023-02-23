package com.new_ton.service;

import com.new_ton.domain.dto.accountmanager.AddOrReplaceComponentToCatalogRecipeDto;
import com.new_ton.domain.dto.accountmanager.ReturnRecipeToTechnologistRequestDto;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.dto.accountmanager.SaveCatalogRecipeDto;
import com.new_ton.domain.entities.CreateCateqDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    boolean updateSelectedCatalogRow(CatalogDtoSelectedRow catalogDtoSelectedRow);

    boolean deleteSelectedCatalogRow(Integer id);

    boolean addNewRecipe(CatalogDtoSelectedRow catalogDtoSelectedRow);

    boolean addComponentToCatalogRecipe(AddOrReplaceComponentToCatalogRecipeDto addOrReplaceComponentToCatalogRecipeDto);

    boolean deleteSelectedRowFromRecipeCatalogTable(Integer id);

    boolean replaceSelectedCatalogRecipeElement(AddOrReplaceComponentToCatalogRecipeDto addOrReplaceComponentToCatalogRecipeDto);

    boolean updateSelectedCatalogRowOfRecipe(UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto);

    boolean saveCatalogRecipe(SaveCatalogRecipeDto saveCatalogRecipeDto);

    boolean deleteSelectedDissolverRow(Integer id);

    boolean createNewCateqRow(CreateCateqDto cateqDto);

    boolean updateCateqRow(CreateCateqDto cateqDto);
}
