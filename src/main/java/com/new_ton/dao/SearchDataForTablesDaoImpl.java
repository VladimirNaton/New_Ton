package com.new_ton.dao;

import com.new_ton.domain.dto.*;
import com.new_ton.domain.entities.CatalogEntity;
import com.new_ton.domain.entities.CatrecEntity;
import com.new_ton.domain.entities.MainEntity;
import com.new_ton.domain.entities.RawEntity;
import com.new_ton.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Repository
public class SearchDataForTablesDaoImpl implements SearchDataForTablesDao {

    private final CatalogRepository catalogRepository;

    private final MainRepository mainRepository;

    private final RawRepository rawRepository;

    private final CatrecRepository catrecRepository;

    private final CatrawRepository catrawRepository;

    @Override
    public Page<CatalogDtoByLeftTable> getDataForTechnologistLeftTable(Pageable pageable) {
        try {
            return catalogRepository.findAllBy(CatalogDtoByLeftTable.class, pageable);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForTechnologistLeftTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<MainTableDto> getDataForTechnologistRightTable(Pageable pageable) {
        try {
            return mainRepository.findAllByState(1, MainTableDto.class, pageable);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForTechnologistRightTable : {}, {} ", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<CatalogDtoByLeftTable> getDataForTechnologistLeftTableWithSearchData(Pageable pageable, String nameprod) {
        try {
            return catalogRepository.findByNameProdWithPagination(nameprod, pageable);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForTechnologistLeftTableWithSearchData {}, {} ", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public CatalogDtoSelectedRow searchDataFromSelectedCatalogRow(Integer idProd) {
        try {
            return catalogRepository.getDataBySelectedRowCatalog(idProd);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl searchDataFromSelectedCatalogRow : {}, {} ", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public List<EditeRecipeTableDto> searchDataForEditeRecipeTable(Integer idRaw) {
        try {
            return rawRepository.findAllIdMain(idRaw);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl searchDataForEditeRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public Optional<CatalogEntity> getCatalogEntityById(Integer id) {
        try {
            return catalogRepository.findById(id);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getCatalogEntityByI : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Optional.empty();
    }

    @Override
    public List<CatrecEntity> getCatrecByCatId(Integer catId) {
        try {
            return catrecRepository.findAllByIdCat(catId);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getCatrecByCatId : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<MainEntity> getMainEntityById(Integer id) {
        try {
            return mainRepository.findById(id);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getMainEntityById : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Optional.empty();
    }

    @Override
    public GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeTable(Integer idProd) {
        try {
            return mainRepository.findDataForInformationStringEditeTable(idProd);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForInformationStringEditeTable : {}, {}", ExceptionUtils.getMessage(e.getCause()), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public Page<EditeRecipeComponentTableDto> getDataForEditeRecipeComponentTable(Integer idSearch, Pageable pageable) {
        try {
            return catrawRepository.getDataForEditeRecipeComponentTable(idSearch, pageable);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForEditeRecipeComponentTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<EditeRecipeComponentTableDto> getDataForEditeRecipeComponentTableWithSearch(Integer idSearch, String findComponent, Pageable pageable) {
        try {
            return catrawRepository.getDataForEditeRecipeComponentTableWithSearch(idSearch, findComponent, pageable);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForEditeRecipeComponentTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(Integer id) {
        try {
            return rawRepository.getDataForSelectedRowEditeRecipeTable(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForSelectedRowEditeRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public List<RawEntity> getAllByIdAndSequenceNumber(Integer idMain, Integer sequenceNumber) {
        try {
            return rawRepository.selectAllByIdAndSequenceNumber(idMain, sequenceNumber);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getAllByIdAndSequenceNumber : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Collections.emptyList();
    }
}
