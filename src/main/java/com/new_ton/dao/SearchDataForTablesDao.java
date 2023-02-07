package com.new_ton.dao;

import com.new_ton.domain.dto.*;
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
}
