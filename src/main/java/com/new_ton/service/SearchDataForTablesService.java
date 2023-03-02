package com.new_ton.service;

import com.new_ton.domain.dto.accountmanager.*;
import com.new_ton.domain.dto.technologistdto.EditeRecipeTableRequestDto;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.dto.accountmanager.DrawDto;
import com.new_ton.domain.dto.testerdto.CheckTakeTemplateDto;
import com.new_ton.domain.dto.testerdto.ProtocolDto;

import java.util.Date;

public interface SearchDataForTablesService {

    TechnologistPageLeftTableResponseDto getDataForTechnologistLeftTable(RequestTechnologistPageLeftTableDto requestTechnologistPageLeftTableDto);

    TechnologistPageRightTableResponseDto getDataForTechnologistRightTable(RequestTechnologistPageRightTableDto requestTechnologistPageRightTableDto);

    CatalogDtoSelectedRow searchDataFromSelectedCatalogRow(Integer idProd);

    EditeRecipeResponseDto searchDataForEditeRecipeTable(EditeRecipeTableRequestDto editeRecipeTableRequestDto);

    GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeRecipe(Integer idProd);

    EditeRecipeCatalogTableResponseDto getDataForEditeRecipeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeComponentTableRequestDto);

    GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(Integer id);

    GetDataForProductInProductionTableRequestDto getDataForProductInProductionTable(DrawDto drawDto);

    AccountManagerTableDataResponseDto getDataForAccountManagerTable(AccountManagerTableRequestDto accountManagerTableRequestDto);

    EditeCatalogRecipeResponseDto getDataForEditeCatalogRecipe(EditeRecipeCatalogTableRequestDto editeRecipeCatalogTableRequestDto);

    GetDataForSelectedRowEditeCatalogRecipeTableResponseDto getDataForSelectedRowEditeRecipeCatalogTable(Integer id);

    EditeDissolversResponceDto getDataForDissolversTable(EditeDissolversTableRequestDto editeDissolversTableRequestDto);

    CateqDto getDataForSelectedRowDissolversTable(Integer id);

    EditeCatalogTableResponseDto searchDataForEditeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeTableRequestDto);

    ComponentTableDto getDataSelectedComponent(Integer id);

    GetDataForProductInProductionTableRequestDto getDataForTesterTable(DrawDto drawDto);

    CommentToStageDto getCommentToStage(CommentToStageDto commentToStageDto);

    CheckTakeTemplateDto checkTakeTemplate(Integer id);

    ProtocolDto getDataForProtocol(Integer id);
}
