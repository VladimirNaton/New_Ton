package com.new_ton.service;

import com.new_ton.domain.dto.accountmanager.AddOrReplaceComponentToCatalogRecipeDto;
import com.new_ton.domain.dto.accountmanager.ComponentTableDto;
import com.new_ton.domain.dto.accountmanager.ReturnRecipeToTechnologistRequestDto;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.dto.accountmanager.SaveCatalogRecipeDto;
import com.new_ton.domain.dto.testerdto.CommentDto;
import com.new_ton.domain.dto.testerdto.SaveProtocolDto;
import com.new_ton.domain.dto.testerdto.TimeDto;
import com.new_ton.domain.entities.CreateCateqDto;

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

    boolean addComponent(ComponentTableDto ComponentTableDto);

    boolean updateComponent(ComponentTableDto ComponentTableDto);

    boolean deleteComponent(Integer id);

    boolean returnToWork(Integer id);

    boolean sendToReject(Integer id);

    boolean sendPutAside(Integer id);

    boolean sendComment(CommentDto commentDto);

    Integer saveCommentToStage(CommentToStageDto commentToStageDto);

    boolean saveTimeTemplate(TimeDto timeDto);

    boolean saveProtocol(SaveProtocolDto saveProtocolDto);
}
