package com.new_ton.dao;

import com.new_ton.domain.dto.accountmanager.*;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.entities.*;
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
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Repository
public class SearchDataForTablesDaoImpl implements SearchDataForTablesDao {

    private final CatalogRepository catalogRepository;
    private final MainRepository mainRepository;
    private final RawRepository rawRepository;
    private final CatrecRepository catrecRepository;
    private final CatrawRepository catrawRepository;
    private final CatpastRepository catpastRepository;
    private final CateqRepository cateqRepository;
    private final CatstateRepository catstateRepository;
    private final CommentToStageRepository commentToStageRepository;
    private final UserFioRepository userFioRepository;

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

    @Override
    public Integer selectMaxSequenceNumber(Integer idMain) {
        try {
            return rawRepository.getMaxSequenceNumber(idMain);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl selectMaxSequenceNumber : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public Optional<CatrawEntity> getCatrawEntityById(Integer id) {
        try {
            return catrawRepository.findById(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getCatRawEntityById : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<RawEntity> getRawEntityById(Integer id) {
        try {
            return rawRepository.findById(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getRawEntityById : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Optional.empty();
    }

    @Override
    public List<RawEntity> getRawEntityByIdMain(Integer idMain) {
        try {
            return rawRepository.findAllByIdMain(idMain);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getRawEntityByIdMain : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Collections.emptyList();
    }

    @Override
    public List<GetDataForProductInProductionTableDto> getDataForProductInProductionTable() {
        try {
            return mainRepository.getDataForProductInProductionTable();
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForProductInProductionTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public Page<AccountManagerTableDataDto> getDataForAccountManagerTableWithoutParam(Pageable pageable) {
        try {
            return mainRepository.findAllByState(2, AccountManagerTableDataDto.class, pageable);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForAccountManagerTableWithoutParam : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<AccountManagerTableDataDto> getDataForAccountManagerTableWithParam(Pageable pageable, String searchValue) {
        try {
            return mainRepository.findAllByStateAndNameprodContainingIgnoreCase(2, searchValue, AccountManagerTableDataDto.class, pageable);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForAccountManagerTableWithParam : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public List<EditeCatalogRecipeTableDto> getDataForEditeCatalogTable(Integer idCat) {
        try {
            List<EditeCatalogRecipeTableDto> catalogRecipeTableDtos = catrecRepository.findAllByIdCat(idCat)
                    .stream()
                    .map(elem -> {
                        EditeCatalogRecipeTableDto editeCatalogRecipeTableDto = new EditeCatalogRecipeTableDto(elem.getId(), elem.getN(), elem.getStage(), elem.getCode(), elem.getNameraw(), elem.getPercent(), elem.getMass(), elem.getDevper(), elem.getDevmass());
                        return editeCatalogRecipeTableDto;
                    }).collect(Collectors.toList());
            return catalogRecipeTableDtos;
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForEditeCatalogTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Collections.emptyList();
    }

    @Override
    public Page<CatpastEntity> getDataForEditeRecipeComponentTablePast(Pageable pageable) {
        try {
            return catpastRepository.findAllBy(CatpastEntity.class, pageable);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForEditeRecipeComponentTablePast : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<CatpastEntity> getDataForEditeRecipeComponentTableWithSearchPastName(String findComponent, Pageable pageable) {
        try {
            return catpastRepository.findAllByNamepastIgnoreCase(findComponent, pageable);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForEditeRecipeComponentTableWithSearchPastName : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Optional<CatpastEntity> getCatpastEntityById(Integer id) {
        try {
            return catpastRepository.findById(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getCatpastEntityById : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Optional.empty();
    }

    @Override
    public Integer selectMaxSequenceNumberForCatalog(Integer idCat) {
        try {
            return catrecRepository.getMaxSequenceNumber(idCat);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl selectMaxSequenceNumberForCatalog : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public List<CatrecEntity> getAllByIdAndSequenceNumberCatalog(Integer idCat, Integer sequenceNumber) {
        try {
            return catrecRepository.selectAllByIdAndSequenceNumber(idCat, sequenceNumber);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getAllByIdAndSequenceNumberCatalog : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<CatrecEntity> getCatrecEntityById(Integer id) {
        try {
            return catrecRepository.findById(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getCatrecEntityById : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Optional.empty();
    }

    @Override
    public GetDataForSelectedRowEditeCatalogRecipeTableResponseDto getDataForSelectedRowEditeRecipeCatalogTable(Integer id) {
        try {
            return catrecRepository.getDataForSelectedRowEditeCatalogRecipeTable(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForSelectedRowEditeRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public List<CateqDto> getDataForDissolversTable() {
        try {
            return cateqRepository.findAllBy();
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForDissolversTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public Optional<CateqEntity> getDataForSelectedRowDissolversTable(Integer id) {
        try {
            return cateqRepository.findById(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForSelectedRowDissolversTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public Page<ComponentTableDto> getDataForEditeComponentTable(Integer idSearch, Pageable pageable) {
        try {
            return catrawRepository.getDataForEditeComponentTable(idSearch, pageable);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForEditeComponentTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<ComponentTableDto> getDataForEditeComponentTableWithSearch(Integer idSearch, String findComponent, Pageable pageable) {
        try {
            return catrawRepository.getDataForEditeComponentTableWithSearch(idSearch, findComponent, pageable);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForEditeComponentTableWithSearch : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public ComponentTableDto getDataSelectedComponent(Integer id) {
        try {
            return catrawRepository.findComponentById(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataSelectedComponent : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public List<GetDataForProductInProductionTableDto> getDataForTesterTable() {
        try {
            return mainRepository.getDataForTesterTable();
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForTesterTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public String getState(Integer code) {
        try {
            return catstateRepository.getState(code);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getState : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public Optional<CommentToStageEntity> getCommentToStage(CommentToStageDto commentToStageDto) {
        try {
            return commentToStageRepository.findByIdMainAndIdStage(commentToStageDto.getIdMain(), commentToStageDto.getIdStage());
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getCommentToStage : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public Optional<RawEntity> checkTakeTemplate(Integer id) {
        try {
            return rawRepository.findSendTemplate(id);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl checkTakeTemplate : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Optional.empty();
    }

    @Override
    public String getUserFio(String userLogin) {
        try {
            return userFioRepository.getUserFio(userLogin);
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getUserFio : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public List<GetDataForProductInProductionTableDto> getDataForProductForProductionTable() {
        try {
            return mainRepository.getDataForProductForProductionTable();
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForProductInProductionTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public List<GetDataForProductInProductionTableDto> getDataForTaskShiftTable() {
        try {
            return mainRepository.getDataForTaskShiftTable();
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForProductInProductionTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public List<GetDataForProductInProductionTableDto> getDataForProductInProductionSupervisorTable(Integer state) {
        try {
            if (state == 0) {
                return mainRepository.getDataForProductInProductionSupervisorTable(5, 10);
            } else {
                return mainRepository.getDataForProductInProductionSupervisorTable(state, state);
            }
        } catch (Exception e) {
            log.error("Error searchDataForTablesDaoImpl getDataForProductInProductionSupervisorTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }
}
