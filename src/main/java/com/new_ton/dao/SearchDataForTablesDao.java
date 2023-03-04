package com.new_ton.dao;

import com.new_ton.domain.dto.accountmanager.*;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SearchDataForTablesDao {

    Page<CatalogDtoByLeftTable> getDataForTechnologistLeftTable(Pageable pageable);

    Page<MainTableDto> getDataForTechnologistRightTable(Pageable pageable);

    Page<CatalogDtoByLeftTable> getDataForTechnologistLeftTableWithSearchData(Pageable pageable, String nameprod);

    CatalogDtoSelectedRow searchDataFromSelectedCatalogRow(Integer idProd);

    List<EditeRecipeTableDto> searchDataForEditeRecipeTable(Integer idRaw);

    Optional<CatalogEntity> getCatalogEntityById(Integer id);

    List<CatrecEntity> getCatrecByCatId(Integer catId);

    Optional<MainEntity> getMainEntityById(Integer id);

    GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeTable(Integer idProd);

    Page<EditeRecipeComponentTableDto> getDataForEditeRecipeComponentTable(Integer idSearch, Pageable pageable);

    Page<EditeRecipeComponentTableDto> getDataForEditeRecipeComponentTableWithSearch(Integer idSearch, String findComponent, Pageable pageable);

    GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(Integer id);

    List<RawEntity> getAllByIdAndSequenceNumber(Integer idMain, Integer sequenceNumber);

    Integer selectMaxSequenceNumber(Integer idMain);

    Optional<CatrawEntity> getCatrawEntityById(Integer id);

    Optional<RawEntity> getRawEntityById(Integer id);

    List<RawEntity> getRawEntityByIdMain(Integer idMain);

    List<GetDataForProductInProductionTableDto> getDataForProductInProductionTable();

    Page<AccountManagerTableDataDto> getDataForAccountManagerTableWithoutParam(Pageable pageable);

    Page<AccountManagerTableDataDto> getDataForAccountManagerTableWithParam(Pageable pageable, String searchValue);

    List<EditeCatalogRecipeTableDto> getDataForEditeCatalogTable(Integer idCat);

    Page<CatpastEntity> getDataForEditeRecipeComponentTablePast(Pageable pageable);

    Page<CatpastEntity> getDataForEditeRecipeComponentTableWithSearchPastName(String findComponent, Pageable pageable);

    Optional<CatpastEntity> getCatpastEntityById(Integer id);

    Integer selectMaxSequenceNumberForCatalog(Integer idCat);

    List<CatrecEntity> getAllByIdAndSequenceNumberCatalog(Integer idCat, Integer sequenceNumber);

    Optional<CatrecEntity> getCatrecEntityById(Integer id);

    GetDataForSelectedRowEditeCatalogRecipeTableResponseDto getDataForSelectedRowEditeRecipeCatalogTable(Integer id);

    List<CateqDto> getDataForDissolversTable();

    Optional<CateqEntity> getDataForSelectedRowDissolversTable(Integer id);

    Page<ComponentTableDto> getDataForEditeComponentTable(Integer idSearch, Pageable pageable);

    Page<ComponentTableDto> getDataForEditeComponentTableWithSearch(Integer idSearch, String findComponent, Pageable pageable);

    ComponentTableDto getDataSelectedComponent(Integer id);

    List<GetDataForProductInProductionTableDto> getDataForTesterTable();

    String getState(Integer code);

    Optional<CommentToStageEntity> getCommentToStage(CommentToStageDto commentToStageDto);

    Optional<RawEntity> checkTakeTemplate(Integer id);

    String getUserFio(String userLogin);

    List<GetDataForProductInProductionTableDto> getDataForProductForProductionTable();

    List<GetDataForProductInProductionTableDto> getDataForTaskShiftTable();

    List<GetDataForProductInProductionTableDto> getDataForProductInProductionSupervisorTable(Integer state);

    List<MainEntity> returnRecipe();
}
